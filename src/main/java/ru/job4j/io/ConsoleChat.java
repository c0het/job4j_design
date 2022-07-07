package ru.job4j.io;



import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";

    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String whatSayUser = scanner.nextLine();
        log.add(whatSayUser);
        String whatSayBot = readPhrases();
        boolean flag = true;
        while (!OUT.equals(whatSayUser)) {
            if (!STOP.equals(whatSayUser)) {
                System.out.println(whatSayBot);
                log.add(whatSayBot);
                whatSayBot = readPhrases();
                whatSayUser = scanner.nextLine();
                log.add(whatSayUser);
            } else {
                flag = false;
                while (!flag) {
                    whatSayUser = scanner.nextLine();
                    log.add(whatSayUser);
                    if (CONTINUE.equals(whatSayUser)) {
                        flag = true;
                    } else if (OUT.equals(whatSayUser)) {
                        break;
                    }
                }
            }
        }
        saveLog(log);
    }

    private String readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader("whatsSayBot", Charset.forName("WINDOWS-1251")))) {
            phrases = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases.get((int) (Math.random() * phrases.size()));
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter("chatWithBot.txt", Charset.forName("WINDOWS-1251"), true)))) {
           log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("", "");
        cc.run();
    }
}