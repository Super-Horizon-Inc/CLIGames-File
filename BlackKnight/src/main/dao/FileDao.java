package main.dao;

import main.models.Map;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDao {
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;
    private static final String fileName = "db.txt";

    public FileDao() {
//        try {
//            File dbFile = new File(fileName);
//            dbFile.createNewFile();
//            if (objectInputStream == null) {
//                System.out.println("BBB");
//                objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
//            }
//            if (objectOutputStream == null) {
//                System.out.println("CCC");
//                objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
//            }
//        }
//        catch (IOException e) {
//            //e.printStackTrace();
//        }
//        catch (Exception e) {
//            System.out.println(e.toString());
//            e.printStackTrace();
//        }
    }

    public Map loadMap() {
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            Map map = (Map) objectInputStream.readObject();
            objectInputStream.close();
            return map;
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
            File dbFile = new File(fileName);
            dbFile.createNewFile();
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(dbFile));
            objectOutputStream.writeObject(map);
            //objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}