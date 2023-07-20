package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {
    @Test
    void getsTokensOfLiteralValue() {
        ExpressionParser parser = new ExpressionParser();

        assertArrayEquals(new String[]{"1"},parser.getTokens("1"));
        assertArrayEquals(new String[]{"2"},parser.getTokens("2"));
        assertArrayEquals(new String[]{"23"},parser.getTokens("23"));

        String[] s1 = new String[]{"(","1",")"};
        assertArrayEquals(s1,parser.getTokens("(1)"));

        String[] s2 = new String[]{"(","21",")"};
        assertArrayEquals(s2,parser.getTokens("(21)"));

        String[] s3 = new String[]{"(","-21",")"};
        assertArrayEquals(s3,parser.getTokens("(-21)"));

        String[] s4 = new String[]{"(","+21",")"};
        assertArrayEquals(s4,parser.getTokens("(+21)"));

        assertArrayEquals(new String[]{"3.14"}, parser.getTokens("3.14"));
    }

    @Test
    void getsTokensOfFunctionCallWithLiterals() {
        ExpressionParser parser = new ExpressionParser();

        String[] t1 = new String[]{"+","1","-2"};
        assertArrayEquals(t1, parser.getTokens("+ 1 -2"));

        String[] t2 = new String[]{"(","+","1","-2",")"};
        assertArrayEquals(t2, parser.getTokens("(+ 1 -2)"));

        String[] t3 = new String[]{"(","+","30","4",")"};
        assertArrayEquals(t3, parser.getTokens("(+ 30 4)"));

        String[] t4 = new String[]{"(","+","30.1","4.74",")"};
        assertArrayEquals(t4, parser.getTokens("(+ 30.1 4.74)"));
    }

    @Test
    void getsTokensOfNestedFunctionCalls() {
        ExpressionParser parser = new ExpressionParser();

        String[] t1 = new String[]{"(","+","5","(","+","1","2",")",")"};
        assertArrayEquals(t1,parser.getTokens("(+ 5 (+ 1 2))"));

        String[] t2 = new String[]{"(","+","(","+","1","2",")","5",")"};
        assertArrayEquals(t2,parser.getTokens("(+ (+ 1 2) 5)"));

        String[] t3 = new String[]{"(","+","5","(","+","1","(","+","11","2",")",")",")"};
        assertArrayEquals(t3,parser.getTokens("(+ 5 (+ 1 (+ 11 2)))"));

        String[] t4 = new String[]{"(","+","5","(","+","1","(","+","11.314","2",")",")",")"};
        assertArrayEquals(t4,parser.getTokens("(+ 5 (+ 1 (+ 11.314 2)))"));
    }
}