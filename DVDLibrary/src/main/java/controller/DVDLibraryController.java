package controller;

import dao.DVDLibraryDao;
import dao.DVDLibraryDaoException;
import dao.DVDLibraryPersistenceException;
import service.DVDLibraryServiceLayer;
import ui.DVDLibraryView;

import java.text.ParseException;

/**
 * Class which handles program flow
 */
public class DVDLibraryController
{
    private DVDLibraryView view;
    private DVDLibraryServiceLayer service;

    /**
     * Main constuctor
     * @param view - program view object
     * @param service - program service layer object
     */
    public DVDLibraryController(DVDLibraryView view, DVDLibraryServiceLayer service)
    {
        this.view = view;
        this.service = service;
    }

    public void run()
    {
        entryMessage();

        try
        {
           menuLoop: while(true)
            {
               int menuSelection = getMenuSelection();

                switch (menuSelection)
                {
                    case 0 ->
                    {
                        break menuLoop;
                    }
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
        catch (DVDLibraryDaoException | DVDLibraryPersistenceException | ParseException e)
        {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection()
    {
        return view.printMainMenuAndGetSelection();
    }

    private void listDvds() throws DVDLibraryDaoException, ParseException
    {
        view.displayDisplayAllBanner();
        view.displayDVDMap(service.getAllDVDs());
    }

    private void addDVD() throws ParseException, DVDLibraryDaoException, DVDLibraryPersistenceException
    {
        view.displayAddDVDBanner();
        service.addDvd(view.getDVDInfo());
        view.displayAddSuccessBanner();
    }

    private void editDVD() throws DVDLibraryDaoException, ParseException, DVDLibraryPersistenceException
    {
        view.displayEditDVDBanner();
        int index = view.getDVDIdChoice();
        service.editDvd(index, view.getDVDInfo());
        view.displayEditSuccessBanner();
    }

    private void deleteDVD() throws DVDLibraryDaoException, ParseException, DVDLibraryPersistenceException
    {
        view.displayRemoveDVDBanner();
        int index = view.getDVDIdChoice();
        view.displayRemoveResult(service.removeDVD(index));
    }

    private void searchDVD() throws DVDLibraryDaoException, ParseException
    {
        view.displayDisplayDVDBanner();
        String title = view.getDVDTitleChoice();
        view.displayDVD(service.searchDVD(title));
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
