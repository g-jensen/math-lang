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

        String[] s1 = {"(","1",")"};
        assertArrayEquals(s1,parser.getTokens("(1)"));

        String[] s2 = {"(","21",")"};
        assertArrayEquals(s2,parser.getTokens("(21)"));

        String[] s3 = {"(","-21",")"};
        assertArrayEquals(s3,parser.getTokens("(-21)"));

        String[] s4 = {"(","+21",")"};
        assertArrayEquals(s4,parser.getTokens("(+21)"));

        assertArrayEquals(new String[]{"3.14"}, parser.getTokens("3.14"));
        assertArrayEquals(new String[]{".1"}, parser.getTokens(".1"));
    }

    @Test
    void getsTokensOfSymbol() {
        ExpressionParser parser = new ExpressionParser();

        assertArrayEquals(new String[]{"e"},parser.getTokens("e"));
        assertArrayEquals(new String[]{"(","e",")"},parser.getTokens("(e)"));
        assertArrayEquals(new String[]{"(","ln","e",")"},parser.getTokens("(ln e)"));

        assertArrayEquals(new String[]{"tao"},parser.getTokens("tao"));
        assertArrayEquals(new String[]{"(","tao",")"},parser.getTokens("(tao)"));
        assertArrayEquals(new String[]{"(","ln","tao",")"},parser.getTokens("(ln tao)"));
    }

    @Test
    void getsTokensOfFunctionCallWithLiterals() {
        ExpressionParser parser = new ExpressionParser();

        String[] t1 = {"+","1","-2"};
        assertArrayEquals(t1, parser.getTokens("+ 1 -2"));

        String[] t2 = {"(","+","1","-2",")"};
        assertArrayEquals(t2, parser.getTokens("(+ 1 -2)"));

        String[] t3 = {"(","+","30","4",")"};
        assertArrayEquals(t3, parser.getTokens("(+ 30 4)"));

        String[] t4 = {"(","+","30.1","4.74",")"};
        assertArrayEquals(t4, parser.getTokens("(+ 30.1 4.74)"));

        String[] t5 = {"+",".1","-2"};
        assertArrayEquals(t5, parser.getTokens("+ .1 -2"));

        String[] t6 = {"exp","1"};
        assertArrayEquals(t6, parser.getTokens("exp 1"));

        String[] t7 = {"(","exp","2",")"};
        assertArrayEquals(t7, parser.getTokens("(exp 2)"));
    }

    @Test
    void getTokensOfNonExistentFunctionCall() {
        ExpressionParser parser = new ExpressionParser();

        String[] t1 = {"p","1","2"};
        assertArrayEquals(t1,parser.getTokens("p 1 2"));

        String[] t2 = {"q","3","5"};
        assertArrayEquals(t2,parser.getTokens("q 3 5"));

        String[] t3 = {".","1","-2"};
        assertArrayEquals(t3, parser.getTokens(". 1 -2"));
    }

    @Test
    void getsTokensOfNestedFunctionCalls() {
        ExpressionParser parser = new ExpressionParser();

        String[] t1 = {"(","+","5","(","+","1","2",")",")"};
        assertArrayEquals(t1,parser.getTokens("(+ 5 (+ 1 2))"));

        String[] t2 = {"(","+","(","+","1","2",")","5",")"};
        assertArrayEquals(t2,parser.getTokens("(+ (+ 1 2) 5)"));

        String[] t3 = {"(","+","5","(","+","1","(","+","11","2",")",")",")"};
        assertArrayEquals(t3,parser.getTokens("(+ 5 (+ 1 (+ 11 2)))"));

        String[] t4 = {"(","+","5","(","+","1","(","+","11.314","2",")",")",")"};
        assertArrayEquals(t4,parser.getTokens("(+ 5 (+ 1 (+ 11.314 2)))"));

        String[] t5 = {"(","exp","(","exp","2",")",")"};
        assertArrayEquals(t5, parser.getTokens("(exp (exp 2))"));

        String[] t6 = {"(","exp","(","+","2","1",")",")"};
        assertArrayEquals(t6, parser.getTokens("(exp (+ 2 1))"));
    }
}