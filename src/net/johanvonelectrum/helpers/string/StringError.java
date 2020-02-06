package net.johanvonelectrum.helpers.string;

public class StringError {
    public static String NotVoid(String[][] strs) {
        String error = "";
        for (String[] str: strs) {
            if (str[1] == null || str[1] == "") error += str[0] + " must be a valid string." + "\n";
        }
        return error;
    }
}
