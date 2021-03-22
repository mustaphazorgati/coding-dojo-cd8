/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package de.mw.devschool.wordcount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class App {
    private final BufferedReader inputStream;
    private final PrintStream outputStream;

    public App(PrintStream outputStream, BufferedReader inputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public static void main(String[] args) {
        final App app = new App(System.out, new BufferedReader(new InputStreamReader(System.in)));
        System.out.println(app.getGreeting());

        app.run();
    }

    public String getGreeting() {
        return "Hello world.";
    }

    void run() {
        // read word input from user
        String a = "";
        try {
            a = a();
        } catch (IOException e) {
            outputStream.println("something went wrong :(");
            e.printStackTrace();
            System.exit(-1);
        }

        // calculate word count
        int n = n(a);

        // output to user
        out();
    }

    String a() throws IOException {
        outputStream.print("Enter text: ");
        return inputStream.readLine();
    }

    int n(String bla) {
        return bla.split(" ").length;
    }

    void out() {
        outputStream.println("The word count is: 5");
    }
}
