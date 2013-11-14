package POE;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author john
 */
public class Parser {

    /**
     * Instead of processing all the overhead of making classes, just returns
     * the metadata from the thread to process later
     *
     * @param url
     * @return
     */
    public static List<Object[]> getThreads2(String url) {
        //ArrayList<poeThread> threads = new ArrayList<poeThread>();
        ArrayList<Object[]> threads = new ArrayList<Object[]>();
        Document doc;
        try {
            // need http protocol
            doc = Jsoup.connect(url).get();

            //each thread is in a table row
            Elements rows = doc.select("tr");

            for (Element e : rows) {
                try {
                    //the link to the forum thread... 
                    //found as the last link (href) under div w/ class "title"
                    String threadID = e.select("div.title > a").last().attr("href");
                    threadID = threadID.replaceFirst("/forum/view-thread/", "");

                    //gets the date from the last post.. 
                    //found as inner text in last span that has class "post_date
                    String dt = e.select("span.post_date").last().text();
                    //the date is preceeded by "on " so it must be stripped out
                    dt = dt.replaceFirst("on ", "");
                    //convert to a valid date
                    Date dateLast = new SimpleDateFormat("MMMM d, yyyy h:mm a").parse(dt);

                    //gets the date original post was done
                    //found as the first date in the span with class "post_date"
                    dt = e.select("span.post_date").first().text();
                    //truncate the non-date portion
                    dt = dt.replaceFirst("on ", "");
                    //convert to valid date
                    Date datePosted = new SimpleDateFormat("MMMM d, yyyy h:mm a").parse(dt);

                    //get the link to poster's account
                    //found as the link under span with class "post_by_account
                    String user = e.select("span.post_by_account > a").first().attr("href");
                    //trim down the link to the user name
                    user = user.replaceFirst("/account/view-profile/", "");

                    //download the html content from the link to the post
                    //Connection conn = Jsoup.connect("http://www.pathofexile.com/forum/view-thread/" + threadID);
                    //Response r = conn.execute();

                    threads.add(new Object[]{threadID, datePosted, user, dateLast});
                    //System.out.println(threadID);
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    System.out.println("sticky");
                }
            }
        } catch (Exception em) {
            em.printStackTrace();

            System.out.println("Thread parse Error");
            //TODO: Find a nice way of omitting Sticky threads from staff
        }
        // return threads;
        return threads;

    }

    /**
     *
     * @param url
     * @return
     */
    public static ArrayList<poeThread> getThreads(String url) {
        //make sure this change works
        ArrayList<poeThread> threads = new ArrayList<poeThread>();
        List<Object[]> threads2 = getThreads2(url);

        for (Object[] object : threads2) {
            //download the html content from the link to the post
            String ThreadID = (String) object[0];
            Date DatePosted = (Date) object[1];
            String User = (String) object[2];
            Date DateLast =     (Date) object[3]; 
            
            Connection conn = Jsoup.connect("http://www.pathofexile.com/forum/view-thread/" + ThreadID);
            Response r= null;
            try {
                r = conn.execute();
            } catch (IOException ex) {
                Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            threads.add(new poeThread(ThreadID,DatePosted, User, DateLast, r.body()));
        }
        /*Document doc;
         try {
         // need http protocol
         doc = Jsoup.connect(url).get();

         //each thread is in a table row
         Elements rows = doc.select("tr");

         for (Element e : rows) {
         try {
         //the link to the forum thread... 
         //found as the last link (href) under div w/ class "title"
         String threadID = e.select("div.title > a").last().attr("href");
         threadID = threadID.replaceFirst("/forum/view-thread/", "");

         //gets the date from the last post.. 
         //found as inner text in last span that has class "post_date
         String dt = e.select("span.post_date").last().text();
         //the date is preceeded by "on " so it must be stripped out
         dt = dt.replaceFirst("on ", "");
         //convert to a valid date
         Date dateLast = new SimpleDateFormat("MMMM d, yyyy h:mm a").parse(dt);

         //gets the date original post was done
         //found as the first date in the span with class "post_date"
         dt = e.select("span.post_date").first().text();
         //truncate the non-date portion
         dt = dt.replaceFirst("on ", "");
         //convert to valid date
         Date datePosted = new SimpleDateFormat("MMMM d, yyyy h:mm a").parse(dt);

         //get the link to poster's account
         //found as the link under span with class "post_by_account
         String user = e.select("span.post_by_account > a").first().attr("href");
         //trim down the link to the user name
         user = user.replaceFirst("/account/view-profile/", "");

         //download the html content from the link to the post
         Connection conn = Jsoup.connect("http://www.pathofexile.com/forum/view-thread/" + threadID);
         Response r = conn.execute();

         threads.add(new poeThread(threadID, datePosted, user, dateLast, r.body()));
         //System.out.println(threadID);
         } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("sticky");
         }
         }
         } catch (Exception em) {
         em.printStackTrace();

         System.out.println("Thread parse Error");
         //TODO: Find a nice way of omitting Sticky threads from staff
         }*/
        return threads;
    }
}
