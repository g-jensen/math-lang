package mathlang;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {
    void parsesANumber() {

    }

    @Test
    void getsTokensOfLiteralValue() {
        ExpressionParser parser = new ExpressionParser();

        assertArrayEquals(new String[]{"1"},parser.getTokens("1").toArray());
        assertArrayEquals(new String[]{"2"},parser.getTokens("2").toArray());
        assertArrayEquals(new String[]{"23"},parser.getTokens("23").toArray());

        String[] s1 = new String[]{"(","1",")"};
        assertArrayEquals(s1,parser.getTokens("(1)").toArray());

        String[] s2 = new String[]{"(","21",")"};
        assertArrayEquals(s2,parser.getTokens("(21)").toArray());

        String[] s3 = new String[]{"(","-21",")"};
        assertArrayEquals(s3,parser.getTokens("(-21)").toArray());

        String[] s4 = new String[]{"(","+21",")"};
        assertArrayEquals(s4,parser.getTokens("(+21)").toArray());
    }

    @Test
    void getsTokensOfFunctionCallWithLiterals() {
        ExpressionParser parser = new ExpressionParser();

        String[] t1 = new String[]{"+","1","-2"};
        assertArrayEquals(t1, parser.getTokens("+ 1 -2").toArray());

        String[] t2 = new String[]{"(","+","1","-2",")"};
        assertArrayEquals(t2, parser.getTokens("(+ 1 -2)").toArray());

        String[] t3 = new String[]{"(","+","30","4",")"};
        assertArrayEquals(t3, parser.getTokens("(+ 30 4)").toArray());
    }

    @Test
    void getsTokensOfNestedFunctionCalls() {
        ExpressionParser parser = new ExpressionParser();

        String[] t1 = new String[]{"(","+","5","(","+","1","2",")",")"};
        assertArrayEquals(t1,parser.getTokens("(+ 5 (+ 1 2))").toArray());

        String[] t2 = new String[]{"(","+","(","+","1","2",")","5",")"};
        assertArrayEquals(t2,parser.getTokens("(+ (+ 1 2) 5)").toArray());

        String[] t3 = new String[]{"(","+","5","(","+","1","(","+","11","2",")",")",")"};
        assertArrayEquals(t3,parser.getTokens("(+ 5 (+ 1 (+ 11 2)))").toArray());

    }
}