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
            if (checkFormat(arg)) {
                String[] argSplit = arg.split("=");
                values.put(argSplit[0].substring(1), arg.substring(argSplit[0].length() + 1));
            }
        }
    }


    private boolean checkFormat(String arg) {
        boolean rsl = true;
        if (arg.split("=").length < 2 || !arg.split("=")[0].startsWith("-")
                || arg.split("=")[0].substring(1).length() == 0) {

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