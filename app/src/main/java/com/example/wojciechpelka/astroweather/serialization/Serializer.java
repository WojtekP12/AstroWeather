package com.example.wojciechpelka.astroweather.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Wojciech on 2016-07-06.
 */
public class Serializer
{
    public static void Serialize(Object object, String path) {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path))); //Select where you wish to save the file...
            oos.writeObject(object); // write the class as an 'object'
            oos.flush(); // flush the stream to insure all of the information was written to 'save.bin'
            oos.close();// close the stream
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public static Object Deserialize(String path) {
        try
        {
            return loadClassFile(new File(path));
        }
        catch (Exception ex)
        {
            return null;
        }
    }
    private static Object loadClassFile(File f) {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            Object o = ois.readObject();
            return o;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
