package telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String call() {
        StringBuilder result = new StringBuilder();
        for (String number : numbers) {
            if (numberChecker(number)) {
                result.append("Invalid number!").append(System.lineSeparator());
            } else {
                result.append(String.format("Calling... %s", number)).append(System.lineSeparator());
            }
        }

        return String.valueOf(result).trim();
    }

    @Override
    public String browse() {
        StringBuilder result = new StringBuilder();
        for (String url : urls) {
            if (urlChecker(url)) {
                result.append("Invalid URL!").append(System.lineSeparator());
            } else {
                result.append(String.format("Browsing: %s!", url)).append(System.lineSeparator());
            }
        }

        return String.valueOf(result).trim();
    }


    public static boolean urlChecker(String url) {
        boolean hasDigit = false;
        for (int i = 0; i < url.length(); i++) {
            if (Character.isDigit(url.charAt(i))) {
                hasDigit = true;
                break;
            }
        }
        return hasDigit;
    }

    public static boolean numberChecker(String number) {
        boolean hasLetter = false;
        for (int i = 0; i < number.length(); i++) {
            if (Character.isLetter(number.charAt(i))) {
                hasLetter = true;
                break;
            }
        }
        return hasLetter;
    }


    }

