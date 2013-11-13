/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POE;

import GUI.MyProps;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author john
 */
public class poeThread {
    private static final Logger LOGGER = Logger.getLogger("MyInfo");
    private String threadID;
    private Date datePosted;
    private String user;
    private Date dateLast;
    private String HTML;
    private ArrayList<POEDBItem> items;

    public poeThread(String _threadID, Date _datePosted, String _user, Date _dateLast, String _HTML) {
        this.threadID = _threadID;
        this.datePosted = _datePosted;
        this.user = _user;
        this.dateLast = _dateLast;
        this.HTML = _HTML;
        try {//pause 1 second between each thread download
            Thread.sleep(1000 * 1);
            //Logger.getLogger("MyInfo").info("Just Fine");
        } catch (InterruptedException ex) {
            Logger.getLogger(poeThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(_threadID);
        parsePOEItems(_HTML);
        parseInnerHTML();
        new DBHandler().addThread(this);
    }

    /**
     *
     * @param index
     * @param value
     * @return
     */
    private boolean setBuyout(int index, String value) {
        this.items.get(index).setBo(parseBO(value));
        return true;
    }

    /**
     *
     * @return
     */
    private boolean isIndexValidAll() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemListID() != i) {
                System.out.println("Error: non matching indexes: " + i);
                return false;
            }
        }
        return true;
    }

    /**
     * Needs work...
     *
     * @param fe
     */
    public void processSpoiler(Element fe) {
        try {
            //grab the spoiler tag, setting it to lower case for string comparison
            //check inner span for buyouts
            String ihtml = fe.select("span").html().toLowerCase();
            //LOGGER.info("Processing element: "+fe.toString());
            //does it have a buyout specified?
            if (ihtml.indexOf("b/o") > 0) {
                //next element (spoiler content) has all the children this price applies to
                Element ne = fe.nextElementSibling();
                //iterate through all the children of the spoiler content
                for (Element element : ne.children()) {
                    //the item fragment id's will always have an "id=item-fragment-XXX"
                    if (!element.attr("id").isEmpty()) {
                        //get the item id to update the item list
                        int ItemID = Integer.parseInt(element.attr("id").replace("item-fragment-", ""));
                        //update the appropriate item with the b/o     
                        setBuyout(ItemID, ihtml);
                        //this.items.get(Integer.parseInt(ss)).setBo(parseProperBO(ihtml));
                    }
                }
            }
            //process the nested spoilers if they exist
            if (fe.children().hasClass("spoilertitle")) {
                for (Element inner : fe.children()) {
                    processSpoiler(inner);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e.getStackTrace());
        }
//            if (ihtml.toLowerCase().indexOf("c/o") > 0) {
//                //next element (spoiler content) has all the children this price applies to
//                Element ne = fe.nextElementSibling();
//                for (Element element : ne.children()) {
//                    if (element.attr("id").isEmpty()) {
//                        // make sure we are only adding items and not user generated html
//                        System.out.println("Empty id attr for thread: " + this.threadID);
//                    } else {
//                        //get the item id to update the item list
//                        String ss = element.attr("id").replace("item-fragment-", "");
//                        //update the appropriate item with the b/o                   
//                        this.items.get(Integer.parseInt(ss)).setCo(ihtml);
//                    }
//                }
//            }

    }

    /**
     * routine to update Buyouts based on spoiler tags and values next to item
     * information !!!!!!!!!!!!!!! TODO !!!!!!!!!!!!!!!!! Add c/o
     *
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     */
    public final void parseInnerHTML() {
        Document doc = Jsoup.parse(this.HTML);
        //update Buyouts for spoiler sections
        for (Element fe : doc.select("div.spoilertitle")) {
            processSpoiler(fe);
        }
        //Update Buyouts individually priced
        //Create a string array from all the itemfragments
        String[] splits = this.HTML.split("<div class=\"itemFragment");
        for (String s : splits) {
            //if there is buyout info specified in later <div>'s... they won't be valid
            String[] temp = s.split("</div>");
            //print out in console if "b/o" was found in the first div rather than second
            if (temp[0].indexOf("b/o") > 0) {
                System.out.println("b/o found in first index??? span maybe?");
            }
            //index 1 has the b/o info... make sure there is an index there first
            //sometimes there is nothing afterwards
            if (temp.length > 1) {
                if (temp[1].indexOf("b/o") > 0) {
                    //itemID is found from "id="item-fragment-XXX"... where XXX is the itemID
                    int start = temp[0].indexOf("item-fragment-") + 14;
                    int stop = temp[0].indexOf("\"", start);
                    int ItemID = Integer.parseInt(temp[0].substring(start, stop));
                    setBuyout(ItemID, temp[1]);
                    //this.items.get(Integer.parseInt(itemID)).setBo(parseBO(temp));
                }
            }
        }
    }

    /**
     * parses out a valid float value and currency in text format
     * !!!!!!!!!!!!!!! TODO !!!!!!!!!!!!!!!!! ! Add ability for multiple
     * currencies to be added ! i.e. 1 ex 3 chaos 1.5 !
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * @param raw
     * @return will return "malformed" if no valid currency is found
     */
    private String parseBO(String raw) {
        //remove html breaks in case user formatted it funky
        String bo = raw.replaceAll("<br />", "");
        //parse out the text "b/o" and just use what follows
        bo = bo.split("b/o")[1].toLowerCase();
        //make sure its a valid currency
        for (String currency : MyProps.getDefault().getCurrencies()) {
            if (bo.indexOf(currency) > 0) {
                try {
                    //valid combination of b/o and a currency found... parse numeric value            
                    float f = Float.parseFloat(bo.substring(0, bo.indexOf(currency)));
                    return Float.toString(f) + " - " + currency;
                    //recursion needed to add stuff like 1 exalt 3 cha

                } catch (Exception e) {
                    LOGGER.log(Level.INFO, "Malformed: " + bo);
                    //Exception thrown if there something besides a numeric value between b/o and the currency
                    System.out.println("Malformed b/o:" + bo);
                }
            }
        }
        return "malformed";
    }

    public ArrayList<POEDBItem> getItems() {
        return this.items;
    }

    public String getThreadID() {
        return this.threadID;
    }

    public Date getDatePosted() {
        return this.datePosted;
    }

    public String getUser() {
        return this.user;
    }

    public Date getDateLast() {
        return this.dateLast;
    }

    public String getHTML() {
        return this.HTML;
    }

    /**
     * Adds items from the thread
     *
     * @param html html from the shop
     */
    private void parsePOEItems(String html) {
        this.items = new ArrayList<POEDBItem>();
        try {
            //parse out the html to find the JSON function in the last <script> tag
            String temp = Jsoup.parse(html).select("script").last().toString();
            //eliminate the function call leaving just the JSON data
            temp = temp.split("new R\\(")[1].split("\\)\\)")[0];

            int index = 0;
            String value = "";

            //cycle through the 3 values of top level JSON array
            JsonNode rootnode = new ObjectMapper().readTree(temp);
            for (JsonNode js : rootnode) {
                for (JsonNode js2 : js) {
                    // first item is numeric
                    if (js2.isInt()) {
                        index = js2.asInt();
                    } //second item is not an array but is a container
                    else if (!js2.isArray() && js2.isContainerNode()) {
                        value = js2.toString();
                    }
                    //third item is always blank array for some reason
                }
                ObjectMapper m = new ObjectMapper();
                m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                POEDBItem newitem = new ObjectMapper().readValue(value, POEDBItem.class);
                newitem.setItemListID(index);

                items.add(newitem);
            }

        } catch (Exception ex) {
            //ex.printStackTrace();
            String msg =Jsoup.parse(html).select("script").last().toString();
            LOGGER.log(Level.INFO, msg);
        }
    }
}
