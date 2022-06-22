package dao;

import dto.DVD;

import java.util.Map;

public interface DVDLibraryDao
{
    DVD addDvd(Integer index, DVD dvd);

    Map<Integer, DVD> getAllDVDs();

    DVD getDVD(Integer index);

    DVD removeDVD(Integer index);
}
