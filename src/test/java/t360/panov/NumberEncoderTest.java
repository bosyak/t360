package t360.panov;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class NumberEncoderTest {

    @Test
    public void testName() throws Exception {
        Dictionary dict = new Dictionary(new LettersMapper());
        dict.addWord("an");
        dict.addWord("blau");
        dict.addWord("Bo\"");
        dict.addWord("Boot");
        dict.addWord("bo\"s");
        dict.addWord("da");
        dict.addWord("Fee");
        dict.addWord("fern");
        dict.addWord("Fest");
        dict.addWord("fort");
        dict.addWord("je");
        dict.addWord("jemand");
        dict.addWord("mir");
        dict.addWord("Mix");
        dict.addWord("Mixer");
        dict.addWord("Name");
        dict.addWord("neu");
        dict.addWord("o\"d");
        dict.addWord("Ort");
        dict.addWord("so");
        dict.addWord("Tor");
        dict.addWord("Torf");
        dict.addWord("Wasser");

        NumberEncoder en = new NumberEncoder(dict);

        assertEquals(0, en.getNumberEncodings("112").size());

        assertEquals(2, en.getNumberEncodings("5624-82").size());
        assertTrue(en.getNumberEncodings("5624-82").contains("mir Tor"));
        assertTrue(en.getNumberEncodings("5624-82").contains("Mix Tor"));

        assertEquals(3, en.getNumberEncodings("4824").size());
        assertTrue(en.getNumberEncodings("4824").contains("Torf"));
        assertTrue(en.getNumberEncodings("4824").contains("fort"));
        assertTrue(en.getNumberEncodings("4824").contains("Tor 4"));

        assertEquals(3, en.getNumberEncodings("10/783--5").size());
        assertTrue(en.getNumberEncodings("10/783--5").contains("neu o\"d 5"));
        assertTrue(en.getNumberEncodings("10/783--5").contains("je bo\"s 5"));
        assertTrue(en.getNumberEncodings("10/783--5").contains("je Bo\" da"));

        assertEquals(1, en.getNumberEncodings("381482").size());
        assertTrue(en.getNumberEncodings("381482").contains("so 1 Tor"));

        assertEquals(3, en.getNumberEncodings("04824").size());
        assertTrue(en.getNumberEncodings("04824").contains("0 Torf"));
        assertTrue(en.getNumberEncodings("04824").contains("0 fort"));
        assertTrue(en.getNumberEncodings("04824").contains("0 Tor 4"));
    }

    @Test
    public void test_match_number() throws Exception {
        NumberEncoder en = new NumberEncoder(new Dictionary(new LettersMapper()));
        assertTrue(en.isValidNumbers("/6/-7-7"));
        assertTrue(en.isValidNumbers("7565-5389376221-3-//93-/1990151477910/7539324003"));
        assertTrue(en.isValidNumbers("38-"));

        assertFalse(en.isValidNumbers("1234F"));
        assertFalse(en.isValidNumbers(""));
        assertFalse(en.isValidNumbers(" "));
        assertFalse(en.isValidNumbers("\n"));
        assertFalse(en.isValidNumbers(null));
    }
}