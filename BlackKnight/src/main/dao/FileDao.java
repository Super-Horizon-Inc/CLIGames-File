package main.dao;

import main.models.Map;
import java.io.*;

/**
 * A class for accessing database. A file is served as a database.
 */
public class FileDao {
    /**
     * Database file name.
     */
    private final String fileName = "db.txt";

    /**
     * Constructor. Create new database file if not existed.
     */
    public FileDao() {
        File dbFile = new File(fileName);
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Load Map object saved in the database file.
     * @return the saved Map object.
     */
    public Map loadMap() {
        try {
            //file content got deleted if placing this in constructor
            //reuse objectInputStream causes error -> create new object each time load
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            return (Map) objectInputStream.readObject(); //both Player and Character must implement Serializable
        } catch (IOException e) {
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO exception handling
        return null;
    }

    /**
     * Save Map object to database file or override existing one.
     * @param map the Map object to be saved.
     */
    public void saveOrUpdateMap(Map map) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete the database file if existing.
     */
    public void delete() {
        File dbFile = new File(fileName);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }
}