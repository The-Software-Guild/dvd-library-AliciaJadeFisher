package dao;

import dto.DVD;

import java.text.ParseException;
import java.util.Map;

public interface DVDLibraryDao
{
    DVD addDvd(Integer index, DVD dvd) throws DVDLibraryDaoException, ParseException;

    Map<Integer, DVD> getAllDVDs() throws DVDLibraryDaoException, ParseException;

    DVD getDVD(Integer index) throws DVDLibraryDaoException, ParseException;

    DVD removeDVD(Integer index) throws DVDLibraryDaoException, ParseException;
}
