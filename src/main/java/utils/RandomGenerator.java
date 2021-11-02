package utils;

import java.util.Random;

public class RandomGenerator {

    public String generateStringUpperCase(int length) {

        //string will be used as a source of characters for generating string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //result string initiation
        StringBuilder randomString = new StringBuilder();

        //new object of random class
        Random rand = new Random();

        //array that will store characters from source string
        char[] text = new char[length];

        //for loop to generate array of a given length
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }

        //for loop to fill in result string with characters out of array
        for (char c : text) {
            randomString.append(c);
        }
        //method return new random string of given length
        return randomString.toString();
    }

    public String generateStringLowerCase(int length) {

        //string will be used as a source of characters for generating string
        String characters = "abcdefghijklmnopqrstuvwxyz";
        //result string initiation
        StringBuilder randomString = new StringBuilder();

        //new object of random class
        Random rand = new Random();

        //array that will store characters from source string
        char[] text = new char[length];

        //for loop to generate array of a given length
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }

        //for loop to fill in result string with characters out of array
        for (char c : text) {
            randomString.append(c);
        }
        //method return new random string of given length
        return randomString.toString();
    }

    public int generateNumber(int length) {

        //start of the boundary
        int min = (int) Math.pow(10, length - 1);

        //end of the boundary
        int max = (int) Math.pow(10, length); // bound is exclusive

        //new object of random class
        Random random = new Random();

        //return random number
        return (random.nextInt(max - min) + min);
    }

    public boolean generateBoolean() {

        //Math.random() generates random from 0.0 to 1.0, so
        //there is a 50% to have true or false randomly
        return Math.random() < 0.5;
    }

    public String generateEmail(int nameLength, int domainLength) {

        //generate email name of specific length with lowercase random letters
        String emailName = generateStringLowerCase(nameLength);
        //generate domain name of specific length with lowercase random letters
        String emailDomain = generateStringLowerCase(domainLength);

        //return complete email
        return emailName + "@" + emailDomain + ".com";
    }

    public String generateName(int nameLength) {

        //generate name of specific length with uppercase first letter
        return generateStringUpperCase(1) + generateStringLowerCase(nameLength-1);
    }

    public String generateAlphanumeric(int length) {

        //string will be used as a source of characters for generating string
        String characters = "1234567890abcdefghijklmnopqrstuvwxyz";
        //result string initiation
        StringBuilder randomString = new StringBuilder();

        //new object of random class
        Random rand = new Random();

        //array that will store characters from source string
        char[] text = new char[length];

        //for loop to generate array of a given length
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }

        //for loop to fill in result string with characters out of array
        for (char c : text) {
            randomString.append(c);
        }
        //method return new random string of given length
        return randomString.toString();
    }


}
