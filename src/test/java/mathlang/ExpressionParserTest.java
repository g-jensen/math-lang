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
    void getsTokensOfSymbolDefinition() {
        ExpressionParser parser = new ExpressionParser();

        assertArrayEquals(new String[]{"def","g","5"},parser.getTokens("def g 5"));

        String[] t1 = {"(","def","greg","(","+","1","2",")",")"};
        assertArrayEquals(t1,parser.getTokens("(def greg (+ 1 2))"));
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

    @Test
    void getsTokensOfList() {
        ExpressionParser parser = new ExpressionParser();

        String[] t1 = {"[","1","2","]"};
        assertArrayEquals(t1, parser.getTokens("[1 2]"));

        String[] t2 = {"[","19","2","]"};
        assertArrayEquals(t2, parser.getTokens("[ 19 2]"));

        String[] t3 = {"[","12","26","]"};
        assertArrayEquals(t3, parser.getTokens("[ 12 26 ]"));

        String[] t4 = {"[","(","+","1","2",")","19","]"};
        assertArrayEquals(t4, parser.getTokens("[(+ 1 2) 19]"));

        String[] t5 = {"[","greg","]"};
        assertArrayEquals(t5, parser.getTokens("[greg]"));

        String[] t6 = {"[","greg","hello1","]"};
        assertArrayEquals(t6, parser.getTokens("[greg hello1]"));
    }

    @Test
    void getsTokensOfFunctionDefinition() {
        ExpressionParser parser = new ExpressionParser();

        String[] t1 = {"fun","myFun","[","]","1"};
        assertArrayEquals(t1, parser.getTokens("fun myFun [] 1"));

        String[] t2 = {"(","fun","otherFun","[","]","(","+","1","1",")",")"};
        assertArrayEquals(t2, parser.getTokens("(fun otherFun [] (+ 1 1))"));

        String[] t3 = {"fun","myFun2","[","]","1"};
        assertArrayEquals(t3, parser.getTokens("fun myFun2 [] 1"));

        String[] t4 = {"fun","myFun","[","param1","]","(","+","1","1",")"};
        assertArrayEquals(t4, parser.getTokens("fun myFun [param1] (+ 1 1)"));

        String[] t5 = {"fun","add","[","a","b","]","(","+","a","b",")"};
        assertArrayEquals(t5, parser.getTokens("fun add [a b] (+ a b)"));
    }
}