package mathlang;

import java.util.Objects;

public class Value {
    public Value(String string) {
        this.string = string;
    }
    public String toString() {
        String i = toInteger().toString();
        String d = toDouble().toString();
        if (isWholeNumber(d)) {
            return i;
        }
        return d;
    }
    public Integer toInteger() {
        Double d = toDouble();
        if (Objects.nonNull(d)) {
            return d.intValue();
        } else {
            return null;
        }
    }
    public Double toDouble() {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    private boolean isWholeNumber(String numberString) {
        int i = numberString.indexOf(".");
        return numberString.substring(i+1).matches("0+");
    }
    private String string;
}
