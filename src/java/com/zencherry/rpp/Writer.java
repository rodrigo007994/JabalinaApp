package com.zencherry.rpp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by rodri on 2/14/2018.
 */
public class Writer {
    static void writeToFile(String fileName, Tramite tramite) throws IOException {
        Tramites tramites= Reader.readFromFile();
        File f = new File(fileName);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tramites.add(tramite);
        oos.writeObject(tramites);
        oos.flush();
        oos.close();
    }
}