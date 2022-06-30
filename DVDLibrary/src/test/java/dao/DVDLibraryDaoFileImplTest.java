package dao;

import dto.DVD;
import dto.MPAARating;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DVDLibraryDaoFileImplTest
{

    DVDLibraryDao testDao;

    public DVDLibraryDaoFileImplTest(){
    }

    @BeforeAll
    static void setUpClass(){}

    @AfterAll
    static void tearDownClass(){}

    @BeforeEach
    void setUp() throws IOException
    {
        String testFile = "TestLibrary.txt";
        new FileWriter(testFile);
        testDao = new DVDLibraryDaoFileImpl(testFile);
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    public void testAddGetDVD() throws Exception
    {
        LocalDate date = LocalDate.parse("2006-12-15");
        DVD dvd = new DVD("Eragon",
                "Stefen Fangmeier",
                "20th Century Studios",
                "Absolutely love this movie",
                date,
                MPAARating.PG);

        testDao.addDvd(dvd);
        DVD retrievedDVD = testDao.getDVD(0);

        assertTrue(retrievedDVD.equals(dvd), "Checking retrieved DVD equals added DVD.");
    }

    @Test
    public void testAddGetAllDVDs() throws Exception
    {
        LocalDate firstDate = LocalDate.parse("2006-12-15");
        DVD firstDvd = new DVD("Eragon",
                "Stefen Fangmeier",
                "20th Century Studios",
                "Absolutely love this movie",
                firstDate,
                MPAARating.PG);

        LocalDate secondDate = LocalDate.parse("2004-05-07");
        DVD secondDvd = new DVD("Van Hesling",
                "Stephen Sommers",
                "Universal Pictures",
                "Amazing movie",
                secondDate,
                MPAARating.PG13);

        testDao.addDvd(firstDvd);
        testDao.addDvd(secondDvd);

        Map<Integer, DVD> allDVDs = testDao.getAllDVDs();

        assertNotNull(allDVDs, "The list of DVDs must not be null");
        assertEquals(2, allDVDs.size(), "The list of DVDs should have 2 DVDs");

        assertTrue(allDVDs.containsValue(firstDvd), "The list of DVDs must include Eragon");
        assertTrue(allDVDs.containsValue(secondDvd), "The list of DVDs must include Van Helsing");

    }

    @Test
    public void testEditDVD() throws Exception
    {
        LocalDate firstDate = LocalDate.parse("2006-12-15");
        DVD dvd = new DVD("Eragon",
                "Stefen Fangmeier",
                "20th Century Studios",
                "Absolutely love this movie",
                firstDate,
                MPAARating.PG);

        testDao.addDvd(dvd);
        dvd.setNote("Highly recommend this one!");
        testDao.editDvd(0, dvd);

        DVD editedDvd = testDao.getDVD(0);

        assertTrue(editedDvd.equals(dvd), "The DVD Eragon should have been updated.");
    }

    @Test
    public void testSearchDVD() throws Exception
    {
        LocalDate firstDate = LocalDate.parse("2006-12-15");
        DVD dvd = new DVD("Eragon",
                "Stefen Fangmeier",
                "20th Century Studios",
                "Absolutely love this movie",
                firstDate,
                MPAARating.PG);

        testDao.addDvd(dvd);

        DVD searchedDvd = testDao.searchDVD("Eragon");

        assertTrue(searchedDvd.equals(dvd), "The search should have returned Eragon");
    }

    @Test
    public void testRemoveDVD() throws Exception
    {
        LocalDate firstDate = LocalDate.parse("2006-12-15");
        DVD firstDvd = new DVD("Eragon",
                "Stefen Fangmeier",
                "20th Century Studios",
                "Absolutely love this movie",
                firstDate,
                MPAARating.PG);

        LocalDate secondDate = LocalDate.parse("2004-05-07");
        DVD secondDvd = new DVD("Van Hesling",
                "Stephen Sommers",
                "Universal Pictures",
                "Amazing movie",
                secondDate,
                MPAARating.PG13);

        testDao.addDvd(firstDvd);
        testDao.addDvd(secondDvd);

        DVD removedDvd = testDao.removeDVD(0);
        assertTrue(removedDvd.equals(firstDvd), "The removed DVD should be Eraogn");

        Map<Integer, DVD> allDVDs = testDao.getAllDVDs();

        assertNotNull(allDVDs, "The list of DVDs must not be null");
        assertEquals(1, allDVDs.size(), "The list of DVDs should have only 1 DVD");

        assertFalse(allDVDs.containsKey(firstDvd), "The list of DVDs should not include Eragon");
        assertTrue(allDVDs.containsValue(secondDvd), "The list of DVDs should include Van Helsing");

        removedDvd = testDao.removeDVD(1);
        assertTrue(removedDvd.equals(secondDvd), "The removed DVD should be Van Helsing");

        allDVDs = testDao.getAllDVDs();
        assertTrue(allDVDs.isEmpty(), "The retrieved list of all DVDs should be empty");

        DVD retrievedDVD = testDao.getDVD(0);
        assertNull(retrievedDVD, "Eragon was removed, should be null");

        retrievedDVD = testDao.getDVD(1);
        assertNull(retrievedDVD, "Van Helsing was removed, should be null");

    }
}