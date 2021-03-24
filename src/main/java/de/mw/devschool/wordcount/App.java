/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package de.mw.devschool.wordcount;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class App {
    private static final String STOP_WORDS_FILE = "stopwords.txt";

    private final BufferedReader inputStream;
    private final PrintStream outputStream;
    private final WordCounter wordCounter;

    public App(PrintStream outputStream, BufferedReader inputStream, WordCounter wordCounter) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.wordCounter = wordCounter;
    }

    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter(initializeStopWordsFile());
        final App app = new App(System.out, new BufferedReader(new InputStreamReader(System.in)), wordCounter);
        System.out.println(app.getGreeting());

        app.run(args.length > 0 ? args[0] : null);
    }

    private static Set<String> initializeStopWordsFile() {
        Set<String> stopWords = new HashSet<>();
        try (InputStream stopWordsFile = App.class.getResourceAsStream(STOP_WORDS_FILE);
             InputStreamReader inputStreamReader = new InputStreamReader(stopWordsFile, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                stopWords.add(line);
            }

        } catch (IOException e) {
            System.err.printf("Could not find/read '%s' file. Continuing without any stopwords.%n", STOP_WORDS_FILE);
        }
        return stopWords;
    }

    public String getGreeting() {
        return "Hello world.";
    }


    void run(String filename) {
        String input = null;
        if (filename != null) {
            try {
                input = readInputFromFile(filename);
            } catch (IOException e) {
                outputStream.println("Could not find/read input file. Opening CLI now...");
            }
        }

        if (input == null) {
            try {
                input = readInputFromCli();
            } catch (IOException e) {
                outputStream.println("Failed to read input.");
                e.printStackTrace();
                System.exit(-1);
            }
        }

        long wordCount = wordCounter.countWords(input);
        printWordCount(wordCount);
    }

    private String readInputFromFile(String filename) throws IOException {
        Path path = Path.of(filename);
        outputStream.printf("Reading input from file '%s'%n", path.toAbsolutePath());
        return Files.readString(path);
    }


    String readInputFromCli() throws IOException {
        outputStream.print("Enter text: ");
        return inputStream.readLine();
    }

    void printWordCount(long wordCount) {
        outputStream.println("The word count is: " + wordCount);
    }
}
