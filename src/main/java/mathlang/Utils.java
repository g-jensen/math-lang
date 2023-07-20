package mathlang;

import java.text.NumberFormat;
import java.text.ParsePosition;

public class Utils {
    // https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    public static boolean isNumeric(String str) {
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(str, pos);
        return !str.isEmpty() && str.length() == pos.getIndex();
    }
}
