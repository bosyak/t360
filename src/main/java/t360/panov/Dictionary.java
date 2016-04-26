package t360.panov;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Dictionary {
    private LettersMapper lettersMapper;
    private TreeMap<String, List<String>> dictionaryMap;
    private Pattern wordPattern;

    public Dictionary(LettersMapper lettersMapper) {
        Objects.requireNonNull(lettersMapper, "LettersMapper can't be null");

        this.lettersMapper = lettersMapper;
        wordPattern = Pattern.compile("([ ]*?[\\w+\"]+[ ]*?)+");

        dictionaryMap = new TreeMap<>((s1, s2) -> {
            if (s1.length() > s2.length()) {
                return 1;
            } else if (s1.length() < s2.length()) {
                return -1;
            } else {
                return s1.compareTo(s2);
            }
        });
    }

    public Dictionary fillFromFile(String dictionaryFilename) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(dictionaryFilename))) {
            lines
                    .filter(this::isValidWord)
                    .forEach(this::addWord);
        }
        return this;
    }

    /**
     * Create mapping for passed rawWord
     *
     * @return normalized word or empty String if nothing is added
     */
    public String addWord(String rawWord) {
        if (rawWord == null || rawWord.replaceAll("[\\s]*", "").length() == 0) return "";
        String normalizedWord = lettersMapper.cleanUpWordToLowerCase(rawWord);
        String digits = lettersMapper.letterToDigit(normalizedWord);
        if (!digits.isEmpty()) {
            addWord(rawWord.trim(), digits);
            return normalizedWord;
        }
        return "";
    }

    public boolean isValidWord(String rawWord) {
        if (rawWord == null) return false;
        return wordPattern.matcher(rawWord).matches();
    }

    private void addWord(String rawWord, String digits) {
        List<String> list = dictionaryMap.get(digits);
        if (list == null) {
            list = new ArrayList<>();
            dictionaryMap.put(digits, list);
        }
        list.add(rawWord);
    }

    public Iterator<Map.Entry<String, List<String>>> iterator() {
        return dictionaryMap.entrySet().iterator();
    }

}
