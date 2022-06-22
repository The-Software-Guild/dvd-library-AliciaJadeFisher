package controller;

import dao.DVDLibraryDao;
import dao.DVDLibraryDaoException;
import ui.DVDLibraryView;

import java.text.ParseException;

/**
 * Class which handles program flow
 */
public class DVDLibraryController
{
    private DVDLibraryView view;
    private DVDLibraryDao dao;

    /**
     * Main constuctor
     * @param view - program view object
     * @param dao - program data access object
     */
    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao)
    {
        this.view = view;
        this.dao = dao;
    }

    public void run()
    {
        entryMessage();
        boolean keepGoing = true;
        int menuSelection = 0;

        try
        {
            while(keepGoing)
            {
                menuSelection = getMenuSelection();

                switch (menuSelection)
                {
                    case 0 -> keepGoing = false;
                    case 1 -> listDvds();
                    case 2 -> addDVD();
                    case 3 -> editDVD();
                    case 4 -> deleteDVD();
                    case 5 -> searchDVD();
                    default -> unknownCommand();
                }
            }

            exitMessage();
        }
        catch (DVDLibraryDaoException | ParseException e)
        {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection()
    {
        return view.printMainMenuAndGerSelection();
    }

    private void listDvds() throws DVDLibraryDaoException, ParseException
    {
        view.displayDisplayAllBanner();
        view.displayDVDMap(dao.getAllDVDs());
    }

    private void addDVD() throws ParseException, DVDLibraryDaoException
    {
        view.displayAddDVDBanner();
        dao.addDvd(view.getDVDInfo());
        view.displayAddSuccessBanner();
    }

    private void editDVD() throws DVDLibraryDaoException, ParseException
    {
        view.displayEditDVDBanner();
        int index = view.getDVDIdChoice();
        dao.editDvd(index, view.getDVDInfo());
        view.displayEditSuccessBanner();
    }

    private void deleteDVD() throws DVDLibraryDaoException, ParseException
    {
        view.displayRemoveDVDBanner();
        int index = view.getDVDIdChoice();
        view.displayRemoveResult(dao.removeDVD(index));
    }

    private void searchDVD() throws DVDLibraryDaoException, ParseException
    {
        view.displayDisplayDVDBanner();
        String title = view.getDVDTitleChoice();
        view.displayDVD(dao.searchDVD(title));
    }

    private void entryMessage()
    {
        view.displayEntryBanner();
    }

    private void unknownCommand()
    {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage()
    {
        view.displayExitBanner();
    }

}
