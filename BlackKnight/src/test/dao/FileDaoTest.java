package test.dao;

import main.dao.FileDao;
import main.models.*;
import static org.junit.Assert.*;

import main.models.Character;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import main.models.*;
import main.dao.*;

import java.io.File;
import java.io.IOException;

public class FileDaoTest {

//    @Before
//    public void initialize() {
//        try {
//            File dbFile = new File("db.txt");
//            dbFile.createNewFile();
//        }
//        catch (IOException e) {
//            System.out.println(e.toString());
//        }
//    }

//    @Test
//    public void loadMapShouldReturnNull() {
//        FileDao fileDao = new FileDao();
//        assertNull("It should return null since db file doesn't exist yet or it's empty.", fileDao.loadMap());
//    }

//    @Test
//    public void loadMapShouldReturnMapObject() {
//        FileDao fileDao = new FileDao();
//        Map map = new Map();
//        Character character = new MainCharacter();
//        character.setName("I'm Main");
//        map.setCharacter(character);
//        fileDao.saveOrUpdateMap(map);
//        assertNotNull("It should return Map object since db file exists and it's not empty.", fileDao.loadMap());
//    }

}