package com.zencherry.rpp;

import java.io.*;

/**
 * Created by rodri on 2/14/2018.
 */
public class Reader {
    public static Tramites readFromFile() {
        FileInputStream fileIn = null;
        Tramites tramites= new Tramites();
        try {
            fileIn = new FileInputStream("tramites.db");
            ObjectInputStream in = new ObjectInputStream(fileIn);
                try{
                    tramites =((Tramites)in.readObject());
                } catch(EOFException e){
                    e.printStackTrace();
                }
            in.close();
            fileIn.close();
            return tramites;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    }
