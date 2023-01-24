package test.java.com.melodicalbuild.lang.zennix;

import com.melodicalbuild.lang.zennix.LexicalParser;
import com.melodicalbuild.lang.zennix.token.Token;
import com.melodicalbuild.lang.zennix.token.TokenType;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LexicalParserTest {

    @Test
    public void testPrint() {
        String source = "print \"Hello World\"";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(2, tokens.size());

        int count = 0;
        assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("print", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("Hello World", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());
    }

    @Test
    public void testInput() {

        String source = "input a";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(2, tokens.size());

        int count = 0;
        assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("input", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());
    }

    @Test
    public void testAssignment() {

        String source = "a = 2 + 5";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(5, tokens.size());

        int count = 0;
        assertEquals(TokenType.Variable, tokens.get(count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("=", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("2", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("5", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());
    }

    @Test
    public void testCondition() {

        String source = "if a > 5\n" +
                "    print \"a is greater than 5\"\n" +
                "elif a >= 1\n" +
                "    print \"a is greater than or equal to 1\"\n" +
                "else\n" +
                "    print \"a is less than 1\"\n" +
                "end";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(22, tokens.size());

        int count = 0;
        assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("if", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals(">", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("5", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("print", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("a is greater than 5", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("elif", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals(">=", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("1", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("print", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("a is greater than or equal to 1", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("else", tokens.get(count).getValue());
        assertEquals(5, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(5, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("print", tokens.get(count).getValue());
        assertEquals(6, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("a is less than 1", tokens.get(count).getValue());
        assertEquals(6, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(6, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("end", tokens.get(count).getValue());
        assertEquals(7, tokens.get(count).getRow());
    }

    @Test
    public void testClass() {

        String source = "class Person [ name, age ]\n" +
                "end\n" +
                "person = new Person[\"Randy Marsh\", 45]\n" +
                "print person :: name + \" is \" + person :: age + \" years old\"";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(32, tokens.size());

        int count = 0;
        assertEquals(TokenType.Keyword, tokens.get(count).getType());
        assertEquals("class", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("Person", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("[", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("name", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals(",", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("age", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("]", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("end", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("person", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("=", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("new", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("Person", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("[", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals("Randy Marsh", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals(",", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("45", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.GroupDivider, tokens.get(++count).getType());
        assertEquals("]", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(3, tokens.get(count).getRow());

        assertEquals(TokenType.Keyword, tokens.get(++count).getType());
        assertEquals("print", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("person", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("::", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("name", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals(" is ", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("person", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("::", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("age", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("+", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());

        assertEquals(TokenType.Text, tokens.get(++count).getType());
        assertEquals(" years old", tokens.get(count).getValue());
        assertEquals(4, tokens.get(count).getRow());
    }

    @Test
    public void testComment() {
        String source = "# a = 5\n" +
                "a = 5 # a is equal to 5";
        LexicalParser parser = new LexicalParser(source);
        List<Token> tokens = parser.parse();

        assertEquals(6, tokens.size());

        int count = 0;
        assertEquals(TokenType.Comment, tokens.get(count).getType());
        assertEquals("# a = 5", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.LineBreak, tokens.get(++count).getType());
        assertEquals("\n", tokens.get(count).getValue());
        assertEquals(1, tokens.get(count).getRow());

        assertEquals(TokenType.Variable, tokens.get(++count).getType());
        assertEquals("a", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Operator, tokens.get(++count).getType());
        assertEquals("=", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Numeric, tokens.get(++count).getType());
        assertEquals("5", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());

        assertEquals(TokenType.Comment, tokens.get(++count).getType());
        assertEquals("# a is equal to 5", tokens.get(count).getValue());
        assertEquals(2, tokens.get(count).getRow());
    }

}
