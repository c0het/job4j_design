package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Parameter does not exist");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] argSplit = arg.split("=", 2);
            if (checkFormat(argSplit)) {
                values.put(argSplit[0].substring(1), argSplit[1]);
            }
        }
    }


    private boolean checkFormat(String[] argSplit) {
        boolean rsl = true;
        if (argSplit.length < 2 || !argSplit[0].startsWith("-")
                || argSplit[0].substring(1).length() == 0
                || argSplit[1].length() == 0) {

                throw new IllegalArgumentException("Parameters are incorrect");
        }
        return rsl;
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No input parameters");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}