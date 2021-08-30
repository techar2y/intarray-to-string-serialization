package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main (String[] args)
    {
        try
        {
            int[] arr = IntStream.generate(() -> new Random().nextInt(1000)).limit(200).toArray();
            String serializedString = serializeIntArrToString(arr);
            int[] deserializedArr = deserializeStringToIntArr(serializedString);
            Arrays.stream(deserializedArr).forEach(x -> System.out.println(x));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    static int[] deserializeStringToIntArr(String serializedString) throws IOException, ClassNotFoundException
    {
        try
        {
            byte b[] = Base64.getDecoder().decode(serializedString.getBytes());
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            return (int[]) si.readObject();
        } catch (IOException ioex) {
            throw ioex;
        } catch (ClassNotFoundException clnex) {
            throw clnex;
        }
    }

    static String serializeIntArrToString(int[] arr) throws IOException
    {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(arr);
            so.flush();
            return Base64.getEncoder().encodeToString(bo.toByteArray());
        } catch (IOException ioex) {
            throw ioex;
        }
    }
}
