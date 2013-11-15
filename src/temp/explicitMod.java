/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author john
 */
public class explicitMod {

    private int valueMin;
    private int valueMax;
    private String text;
    private final Pattern pRemoveIllegalChars = Pattern.compile("\\[|\\]|'");
    private final Pattern pGetNumericPortion = Pattern.compile("\\d+");

    public explicitMod(String input) {
        //take out brackets... also take out ' so it doesn't interfere w/ SQL
        Matcher matcher = pRemoveIllegalChars.matcher(input);
        input = matcher.replaceAll("").trim();

        //REGEX to get the numeric portion
        matcher = pGetNumericPortion.matcher(input);

        List<Object[]> vals = new ArrayList<Object[]>();
        while (matcher.find()) {
            //save the number and where it was located in string
            vals.add(new Object[]{matcher.group(), matcher.start(), matcher.end()});
        }
        if ((vals.size() == 1) || (vals.size() == 2)) {
            int minstart = (Integer) vals.get(0)[1];
            int minstop = (Integer) vals.get(0)[2];
            //if there isn't a second numberic portion... use the first for max value
            int maxstart = (Integer) vals.get(vals.size() - 1)[1];
            int maxstop = (Integer) vals.get(vals.size() - 1)[2];
            valueMin = Integer.parseInt(input.substring(minstart, minstop));
            valueMax = Integer.parseInt(input.substring(maxstart, maxstop));
            text = matcher.replaceAll("X");

        } else {
            valueMin = 0;
            valueMax = 0;
            text = input;
        }
    }

    public int getMinValue() {
        return this.valueMin;
    }

    public int getMaxValue() {
        return this.valueMax;
    }

    public String getText() {
        return this.text;
    }
}
