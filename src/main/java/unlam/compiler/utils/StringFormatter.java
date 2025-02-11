package unlam.compiler.utils;

public class StringFormatter {
    private static final int TABLE_WIDTH = 60;

    private StringFormatter() {
    }

    public static String centerText(String text) {
        if (text == null) {
            text = "";
        }

        int textLength = text.length();
        if (textLength >= TABLE_WIDTH) {
            return text.substring(0, TABLE_WIDTH);
        }

        int leftPadding = (TABLE_WIDTH - textLength) / 2;
        int rightPadding = TABLE_WIDTH - textLength - leftPadding;

        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }
}