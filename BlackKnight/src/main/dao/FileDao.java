package main.dao;

import main.models.Map;
import java.io.*;

public class FileDao {
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;
    private static final String fileName = "db.txt";

    public FileDao() {
        try {
            System.out.println("AAA");
            if (objectInputStream == null) {
                System.out.println("BBB");
//                File dbFile = new File(fileName);
//                dbFile.createNewFile();
                objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            }
            if (objectOutputStream == null) {
                System.out.println("CCC");
                File dbFile = new File(fileName);
                dbFile.createNewFile();
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            }
        }
        catch (IOException e) {
            //e.printStackTrace();
        }
        catch (Exception e) {
            System.out.println(e.toString());
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
            //System.out.println("aaa");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            //System.out.println(e.toString());
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