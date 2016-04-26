package t360.panov;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DictionaryTest extends TestCase {

    @Test
    public void test_add_word() throws Exception {
        Dictionary dic = new Dictionary(new LettersMapper());

        assertEquals("", dic.addWord(null));
        assertEquals("", dic.addWord(""));
        assertEquals("privet", dic.addWord("Privet"));
        assertEquals("abfordermenge", dic.addWord("Abfo\"rdermenge"));
    }

    @Test
    public void test_dictionary_key_sorting() throws Exception {
        Dictionary dic = new Dictionary(new LettersMapper());
        dic.addWord("e");
        dic.addWord("ee");
        dic.addWord("eee");

        Iterator<Map.Entry<String, List<String>>> iterator = dic.iterator();
        assertEquals("0", iterator.next().getKey());
        assertEquals("00", iterator.next().getKey());
        assertEquals("000", iterator.next().getKey());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void test_dictionary_word_diff() throws Exception {
        Dictionary dic = new Dictionary(new LettersMapper());
        dic.addWord("bku");
        dic.addWord("Bku");
        dic.addWord("BKU");

        Iterator<Map.Entry<String, List<String>>> iterator = dic.iterator();
        Map.Entry<String, List<String>> next = iterator.next();
        assertEquals("777", next.getKey());
        assertEquals(3, next.getValue().size());

        assertFalse(iterator.hasNext());
    }

    public void test_word_validation() throws Exception {
        Dictionary dic = new Dictionary(new LettersMapper());

        assertFalse(dic.isValidWord(null));
        assertFalse(dic.isValidWord(" "));
        assertFalse(dic.isValidWord(""));
        assertFalse(dic.isValidWord("-"));
        assertFalse(dic.isValidWord("monad-"));
        assertFalse(dic.isValidWord("m/"));

        assertTrue(dic.isValidWord("s   "));
        assertTrue(dic.isValidWord("   s   "));
        assertTrue(dic.isValidWord("   s"));
        assertTrue(dic.isValidWord("zynismusfo\"rdernd"));
        assertTrue(dic.isValidWord("\"rdernd"));
        assertTrue(dic.isValidWord("Zylinderko\""));
        assertTrue(dic.isValidWord("Zy li nd erko\""));
    }
}