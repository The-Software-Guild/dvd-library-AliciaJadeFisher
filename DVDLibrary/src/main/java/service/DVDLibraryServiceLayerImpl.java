package service;

import dao.DVDLibraryAuditDao;
import dao.DVDLibraryDao;
import dao.DVDLibraryDaoException;
import dao.DVDLibraryPersistenceException;
import dto.DVD;

import java.text.ParseException;
import java.util.Map;

public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer
{
    private DVDLibraryDao dao;
    private DVDLibraryAuditDao auditDao;

    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao, DVDLibraryAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void addDvd(DVD dvd) throws DVDLibraryDaoException, ParseException, DVDLibraryPersistenceException
    {
        dao.addDvd(dvd);
        auditDao.writeAuditEntry("DVD " + dvd.getTitle() + " CREATED.");
    }

    @Override
    public DVD editDvd(Integer index, DVD dvd) throws DVDLibraryDaoException, ParseException, DVDLibraryPersistenceException
    {
        dao.editDvd(index, dvd);
        auditDao.writeAuditEntry("DVD " + dvd.getTitle() + " EDITED.");
        return dvd;
    }

    @Override
    public Map<Integer, DVD> getAllDVDs() throws DVDLibraryDaoException, ParseException
    {
        return dao.getAllDVDs();
    }

    @Override
    public DVD getDVD(Integer index) throws DVDLibraryDaoException, ParseException
    {
        return dao.getDVD(index);
    }

    @Override
    public DVD searchDVD(String title) throws DVDLibraryDaoException, ParseException
    {
        return dao.searchDVD(title);
    }

    @Override
    public DVD removeDVD(Integer index) throws DVDLibraryDaoException, ParseException, DVDLibraryPersistenceException
    {
        DVD removedDVD = dao.removeDVD(index);
        auditDao.writeAuditEntry("DVD " + removedDVD.getTitle() + " REMOVED.");
        return removedDVD;
    }
}
