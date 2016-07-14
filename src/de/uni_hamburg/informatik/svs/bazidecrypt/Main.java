package de.uni_hamburg.informatik.svs.bazidecrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            byte[] content = Files.readAllBytes(Paths.get(args[0]));
            System.out.println(decodeBazi(content, getMessageKey(content)));
        } catch (IOException | IndexOutOfBoundsException e) {
            System.err.println("Datei konnte nicht gelesen werden: " + e.getMessage());
        }
    }

    private static byte[] getMessageKey(byte[] message) {
        return Arrays.copyOfRange(message, message.length - 10, message.length);
    }

    private static String decodeBazi(byte[] message, byte[] key) {
        byte[] result = new byte[message.length];

        for (int i = 0; i < message.length; ++i) {
            result[i] = (byte) (message[i] ^ key[i % 10]);
        }

        return new String(result);
    }
}
