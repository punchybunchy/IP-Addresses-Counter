package punchybunchy.code.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IpAddressGenerator {

    private static final int MIN = 0;
    private static final int MAX = 256;

    public static void generateIpAddressesCollection(int amountOfIps, String fileName) {

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < amountOfIps; i++) {
            printWriter.println(generateIpAddress());
        }
        printWriter.close();
    }

    private static String generateIpAddress() {
        final int numberOfOctets = 4;
        int[] randomNumbers = new int[numberOfOctets];
        return IntStream
                .range(0, randomNumbers.length)
                .mapToObj(index -> randomNumbers[index] = getRandomNumber())
                .map(Object::toString)
                .collect(Collectors.joining("."));
    }
    private static int getRandomNumber() {
        Random random = new Random();
        int diff = MAX - MIN;
        return random.nextInt(diff);
    }




}
