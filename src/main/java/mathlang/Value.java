package mathlang;

import java.util.Objects;

public class Value {
    public Value(String string) {
        this.string = string;
    }
    public boolean isWord() {
        return Objects.isNull(toDouble()) && Objects.isNull(toInteger());
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (!(obj instanceof Value)) {return false;}
        Value v = (Value) obj;
        return v.toString().equals(this.toString());
    }
    @Override
    public String toString() {
        Integer i = toInteger();
        Double d = toDouble();
        if (Objects.isNull(d)) {return string;}

        String is = i.toString();
        String ds = d.toString();
        if (isWholeNumber(ds)) {return is;}

        return ds;
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
