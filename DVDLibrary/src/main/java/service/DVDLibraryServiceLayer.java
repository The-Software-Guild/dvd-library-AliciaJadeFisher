package service;

import dao.DVDLibraryDaoException;
import dao.DVDLibraryPersistenceException;
import dto.DVD;
import java.text.ParseException;
import java.util.Map;

public interface DVDLibraryServiceLayer
{
    void addDvd(DVD dvd) throws DVDLibraryDaoException, ParseException, DVDLibraryPersistenceException;

    DVD editDvd(Integer index, DVD dvd) throws DVDLibraryDaoException, ParseException, DVDLibraryPersistenceException;

    Map<Integer, DVD> getAllDVDs() throws DVDLibraryDaoException, ParseException;

    DVD getDVD(Integer index) throws DVDLibraryDaoException, ParseException;

    DVD searchDVD(String title) throws DVDLibraryDaoException, ParseException;

    DVD removeDVD(Integer index) throws DVDLibraryDaoException, ParseException, DVDLibraryPersistenceException;

}
