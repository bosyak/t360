package t360.panov;


import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LettersMapper {
    private TreeMap<Character, String> mapping;

    public LettersMapper() {
        mapping = new TreeMap<>();
        initMapping(mapping);
    }

    public void initMapping(TreeMap<Character, String> digMapping) {
        digMapping.put('e', "0");
        digMapping.put('j', "1");
        digMapping.put('n', "1");
        digMapping.put('q', "1");
        digMapping.put('r', "2");
        digMapping.put('w', "2");
        digMapping.put('x', "2");
        digMapping.put('d', "3");
        digMapping.put('s', "3");
        digMapping.put('y', "3");
        digMapping.put('f', "4");
        digMapping.put('t', "4");
        digMapping.put('a', "5");
        digMapping.put('m', "5");
        digMapping.put('c', "6");
        digMapping.put('i', "6");
        digMapping.put('v', "6");
        digMapping.put('b', "7");
        digMapping.put('k', "7");
        digMapping.put('u', "7");
        digMapping.put('l', "8");
        digMapping.put('o', "8");
        digMapping.put('p', "8");
        digMapping.put('g', "9");
        digMapping.put('h', "9");
        digMapping.put('z', "9");
    }

    public String letterToDigit(String word) {
        Objects.requireNonNull(word, "word can't be null");
        String result = word.chars()
                .mapToObj(i -> (char) i)
                .map(mapping::get)
                .collect(Collectors.joining());

        if (word.length() == result.length()) {
            return result;
        } else {
            processWrongMapping(word);
        }

        return "";
    }

    public String cleanUpWordToLowerCase(String word) {
        Objects.requireNonNull(word, "word can't be null");
        return word.toLowerCase().replaceAll("[^a-z]", "");
    }

    public void processWrongMapping(String word) {
        //override it if needed;
    }
}
