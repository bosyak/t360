package t360.panov;


import java.util.*;
import java.util.regex.Pattern;

public class NumberEncoder {
    private Pattern numbersPattern;
    private Dictionary dictionary;

    public NumberEncoder(Dictionary dictionary) {
        Objects.requireNonNull(dictionary, "Dictionary can't be null");
        this.dictionary = dictionary;
        numbersPattern = Pattern.compile("[0-9\\-/]+");
    }

    public List<String> getNumberEncodings(String rawNumbers) {
        List<String> result = new ArrayList<>();
        if (!isValidNumbers(rawNumbers)) return Collections.emptyList();

        String cleanNumbers = cleanUpNumbers(rawNumbers);
        if (!cleanNumbers.isEmpty()) {
            //start recursion
            collectVariants(cleanNumbers, new Tail(new ArrayList<>()), result);
        }

        return result;
    }

    public String cleanUpNumbers(String numbers) {
        return numbers.replaceAll("[\\D]", "");
    }

    public boolean isValidNumbers(String numbers) {
        if (numbers == null) return false;
        return numbersPattern.matcher(numbers).matches();
    }


    private void collectVariants(String remainsNumbers, Tail tail, List<String> finalResults) {
        //check if we are done
        if (remainsNumbers.isEmpty()) {
            commitResult(tail, finalResults);
            return;
        }

        boolean noWordFound = true;

        //iterating over keys (digits) statring from smallest (because of sorting)
        Iterator<Map.Entry<String, List<String>>> iterator = dictionary.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> next = iterator.next();
            //getting key (digits) and correspondencing words from dictionary
            String dicNumber = next.getKey();
            List<String> dicWords = next.getValue();

            //check if key is too long for remaining dicNumber
            if (dicNumber.length() > remainsNumbers.length()) {
                break;
            }

            if (remainsNumbers.startsWith(dicNumber)) {
                noWordFound = false;
                String rest = remainsNumbers.substring(dicNumber.length());
                for (String dicWord : dicWords) {
                    Tail clone = tail.clone();
                    clone.getWords().add(dicWord);
                    //start recursion
                    collectVariants(rest, clone, finalResults);
                }
            }
        }

        //only one digit left. add digit at the end if it was word in previson recursion
        if (remainsNumbers.length() == 1 && tail.isDigitAllowed()) {
            tail.getWords().add(remainsNumbers);
            commitResult(tail, finalResults);
            return;
        }

        //two or more letters left, take first digit and start recursion for the rest
        if (noWordFound && tail.isDigitAllowed() && remainsNumbers.length() >= 2) {
            String digit = remainsNumbers.substring(0, 1);
            String rest = remainsNumbers.substring(1);
            tail.getWords().add(digit);
            collectVariants(rest, tail.clone().setDigitAllowed(false), finalResults);
        }
    }

    private void commitResult(Tail tail, List<String> finalResults) {
        finalResults.add(String.join(" ", tail.getWords()));
    }

    private static class Tail {
        private boolean digitAllowed = true;
        private List<String> words;

        public Tail(List<String> words) {
            this.words = words;
        }

        public Tail clone() {
            return new Tail(new ArrayList<>(words));
        }

        public boolean isDigitAllowed() {
            return digitAllowed;
        }

        public Tail setDigitAllowed(boolean digitAllowed) {
            this.digitAllowed = digitAllowed;
            return this;
        }

        public List<String> getWords() {
            return words;
        }
    }
}
