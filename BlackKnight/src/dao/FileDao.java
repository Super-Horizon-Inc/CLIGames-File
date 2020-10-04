package dao;

import models.Map;
import java.io.*;

public class FileDao {
    private static ObjectInputStream objectInputStream = null;
    private static ObjectOutputStream objectOutputStream = null;
    private static final String fileName = "db.txt";

    public FileDao() {
        try {
            if (objectInputStream == null) {
                objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            }
            if (objectOutputStream == null) {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            }
        }
        catch (IOException e) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // in java doc we should mention that we are defining id as final because we don't want to allow user
    // unintentionally reassign an id
//    public E findById(int id) {
//        try {
//            ObjectInputStream objectInputStream = this.getInputObjManager();
//            List<E> entityList = (List<E>) objectInputStream.readObject();
//            for (E entity: entityList) {
//                Class obj = entity.getClass();
//                Field field = obj.getField("id");
//                Object objInstance = new Object();
//                Integer value = (int) field.get(objInstance);
//                //TODO compare to Serializable obj
//                if (value == id) {
//                    return entity;
//                }
//            }
//            return null;
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//            //TODO handle it correctly
//            return null;
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            //TODO handle it correctly
//            return null;
//        }
//        catch (NoSuchFieldException e) {
//            e.printStackTrace();
//            //TODO handle it correctly
//            return null;
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//            //TODO handle it correctly
//            return null;
//        }
//    }

}