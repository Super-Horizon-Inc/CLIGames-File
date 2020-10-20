package test.dao;

import main.dao.FileDao;
import main.models.Map;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import main.models.*;
import main.dao.*;

public class FileDaoTest {
//    @Test
//    public void loadMapShouldReturnNull() {
//        FileDao fileDao = new FileDao();
//        assertEquals("should return null.",null, fileDao.loadMap());
//    }

    @Test
    public void loadMapShouldReturnMapObject() {
        Map map = new Map();
        map.setName("map1");
        FileDao fileDao = new FileDao();
        fileDao.saveOrUpdateMap(map);
        assertNotEquals("should not return null.", null, fileDao.loadMap());
    }


}