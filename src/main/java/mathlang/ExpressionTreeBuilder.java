package mathlang;

import java.text.NumberFormat;
import java.text.ParsePosition;

public class ExpressionTreeBuilder {

    // https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    private boolean isNumeric(String str) {
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(str, pos);
        return str.length() == pos.getIndex();
    }
    public BinaryExpressionNode build(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("+")) {
                return new AdditionExpressionNode(
                        new ConstantExpressionNode(Integer.parseInt(tokens[i+1])),
                        new ConstantExpressionNode(Integer.parseInt(tokens[i+2])));
            } else if (isNumeric(token)) {
                return new ConstantExpressionNode(Integer.parseInt(token));
            }
        }
        return null;
    }
}
