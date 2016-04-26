package t360.panov;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Main {

    ExecutorService service;
    NumberEncoder encoder;

    public Main() {
        service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public static void main(String[] args) throws IOException {
        new Main().processNumbersFile("dictionary.txt", "input.txt");
    }

    public void processNumbersFile(String dictionaryFile, String numbersFilename) throws IOException {

        /**
         * By design you are able to override any method and change
         * behavior or state without actually rewrite whole class.
         *
         * Code as clear as possible and self commented.
         */
        LettersMapper lettersMapper = new LettersMapper() {
            @Override
            public void processWrongMapping(String word) {
                //here the "word" which can't be encoded and is possible to handle it here
            }
        };
        Dictionary dictionary = new Dictionary(lettersMapper).fillFromFile(dictionaryFile);
        encoder = new NumberEncoder(dictionary);

        try (Stream<String> lines = Files.lines(Paths.get(numbersFilename))) {
            lines.forEach(this::processRawNumber);
        }

        service.shutdown();
    }

    public void processRawNumber(String rawNumber) {
        service.submit(() -> {
            List<String> numberEncodings = encoder.getNumberEncodings(rawNumber);
            for (String numberEncoding : numberEncodings) {
                System.out.println(rawNumber + ": " + numberEncoding);
            }
        });
    }
}
