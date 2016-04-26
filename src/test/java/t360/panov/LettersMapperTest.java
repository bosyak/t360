package t360.panov;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Andrey Panov on 31.01.2016
 */
public class LettersMapperTest {

    @Test
    public void test_code_mapping() throws Exception {
        LettersMapper map = new LettersMapper();

        assertEquals("0", map.letterToDigit("e"));
        assertEquals("1", map.letterToDigit("j"));
        assertEquals("1", map.letterToDigit("n"));
        assertEquals("1", map.letterToDigit("q"));
        assertEquals("2", map.letterToDigit("r"));
        assertEquals("2", map.letterToDigit("w"));
        assertEquals("2", map.letterToDigit("x"));
        assertEquals("3", map.letterToDigit("d"));
        assertEquals("3", map.letterToDigit("s"));
        assertEquals("3", map.letterToDigit("y"));
        assertEquals("4", map.letterToDigit("f"));
        assertEquals("4", map.letterToDigit("t"));
        assertEquals("5", map.letterToDigit("a"));
        assertEquals("5", map.letterToDigit("m"));
        assertEquals("6", map.letterToDigit("c"));
        assertEquals("6", map.letterToDigit("i"));
        assertEquals("6", map.letterToDigit("v"));
        assertEquals("7", map.letterToDigit("b"));
        assertEquals("7", map.letterToDigit("k"));
        assertEquals("7", map.letterToDigit("u"));
        assertEquals("8", map.letterToDigit("l"));
        assertEquals("8", map.letterToDigit("o"));
        assertEquals("8", map.letterToDigit("p"));
        assertEquals("9", map.letterToDigit("g"));
        assertEquals("9", map.letterToDigit("h"));
        assertEquals("9", map.letterToDigit("z"));

        assertEquals("0111777", map.letterToDigit("ejnqbku"));
    }

    @Test
    public void test_word_cleanup() throws Exception {
        LettersMapper map = new LettersMapper();

        assertEquals("abfordermenge", map.cleanUpWordToLowerCase("Abfo\"rdermenge"));
        assertEquals("etotext", map.cleanUpWordToLowerCase("Eto Text"));
        assertEquals("popugaev", map.cleanUpWordToLowerCase("38popugaev"));
    }
}