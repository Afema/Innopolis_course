package ru.inno.course.homework1;

public class Homework1 {
    public static void main(String[] args) {
        // Возвращает длину строки (количество символов)
        String city = "Москва Москва ";
        int result = city.length();
        System.out.println(result);

        // method checks if the input string is empty or not
        boolean result1 = city.isEmpty();
        System.out.println(result1);

        // checks if a String is whitespace, empty ("") or null
        boolean result2 = city.isBlank();
        System.out.println(result2);

        // method returns the substring of this string, always returns a new string
        String result3 = city.substring(0, 3);
        System.out.println(result3);

        // method returns the position of the first occurrence of specified character(s) in a string
        int result4 = city.indexOf("а");
        System.out.println(result4);

        // method returns the position of the last occurrence of specified character(s) in a string
        int result5 = city.lastIndexOf("а");
        System.out.println(result5);

        // method converts a string to lower case letters
        String result6 = city.toLowerCase();
        System.out.println(result6);

        // method converts a string to upper case letters
        String result7 = city.toUpperCase();
        System.out.println(result7);

        // method replaces all occurrences of a String in another String
        String result8 = city.replace("М", " ");
        System.out.println(result8);

        // method checks whether a string starts with the specified character(s)
        boolean result9 = city.startsWith("Мос");
        System.out.println(result9);
        boolean result10 = city.startsWith("р");
        System.out.println(result10);

        // method checks whether a string ends with the specified character(s)
        boolean result11 = city.endsWith("ва");
        System.out.println(result11);
        boolean result12 = city.startsWith("р");
        System.out.println(result12);

        // method is used to return String whose value is the concatenation of given String repeated count times
        String result13 = city.repeat(3);
        System.out.println(result13);

        // method checks whether a string contains a sequence of characters
        boolean result14 = city.contains("оскв");
        System.out.println(result14);
        boolean result15 = city.contains("Л");
        System.out.println(result15);

        // method appends a specified string at the end of the current string and returns the new combined string
        String result16 = city.concat(" - столица нашей Родины!");
        System.out.println(result16);

        // method  removes whitespaces from the left and right of the string
        String result17 = city.trim();
        System.out.println(result17);
    }
}
