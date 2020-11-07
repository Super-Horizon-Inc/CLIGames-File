package main.dao;

import main.models.Map;
import java.io.*;

public class FileDao {
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;
    private static final String fileName = "db.txt";

    public FileDao() {
        try {
            if (objectInputStream == null) {
                objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            }
            if (objectOutputStream == null) {
                File dbFile = new File(fileName);
                dbFile.createNewFile();
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map loadMap() {
        try {
            return (Map) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO exception handling
        return null;
    }

    public void saveOrUpdateMap(Map map) {
        try {
            objectOutputStream.writeObject(map);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}