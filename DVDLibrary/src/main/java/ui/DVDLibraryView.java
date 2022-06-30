package ui;

import dto.DVD;
import dto.MPAARating;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

/**
 * Class which handles all output to the user
 */
public class DVDLibraryView
{
    private UserIO io;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public DVDLibraryView(UserIO io)
    {
        this.io = io;
    }

    /**
     * Displays the main menu to the user and returns their choice
     * @return int representing the menu choice
     */
    public int printMainMenuAndGetSelection()
    {
        io.print("-----------------------------");
        io.print("1 - View Library");
        io.print("2 - Add DVD to Library");
        io.print("3 - Edit DVD in Library");
        io.print("4 - Remove DVD from Library");
        io.print("5 - Search Library for a DVD");
        io.print("0 - Exit");
        io.print("-----------------------------");

        return io.readInt("What would you like to do? (0-5): ", 0, 5);
    }

    /**
     * Gets the details of a DVD object from the user
     * @return a DVD object
     * @throws ParseException
     */
    public DVD getDVDInfo() throws ParseException
    {
        // Get details from user
        String title = io.readString("Title ");
        LocalDate date = LocalDate.parse(io.readString("Release Date (dd/mm/yyyy)"));
        int ratingInt = io.readInt("MPAA Rating\n1 - G\n2 - PG\n3 - PG-13\n4 - R\n5 - NC-17\n Rating (1-5)"
                , 1 ,5);
        String director = io.readString("Director");
        String studio = io.readString("Production Studio");
        String note = io.readString("Note");

        // Convert rating from int to enum value
        MPAARating rating = null;
        switch (ratingInt)
        {
            case 1 -> rating = MPAARating.G;
            case 2 -> rating = MPAARating.PG;
            case 3 -> rating = MPAARating.PG13;
            case 4 -> rating = MPAARating.R;
            case 5 -> rating = MPAARating.NC17;
        }

        return new DVD(title, director, studio, note, date, rating);
    }

    /**
     * Displays all DVDs in the map
     * @param dvdLibrary - a map of all DVD objects
     */
    public void displayDVDMap(Map<Integer, DVD> dvdLibrary)
    {
        for(Integer i : dvdLibrary.keySet())
        {
            DVD dvd = dvdLibrary.get(i);
            String dvdInfo = i + " - " + dvd.getTitle() + " | " + dateFormat.format(dvd.getDate()) + " | " + dvd.getRating() + " | " +
                    dvd.getDirector() + " | " + dvd.getStudio() + " | " + dvd.getNote();

            io.print(dvdInfo);
        }

        io.readString("Please press enter to continue.");
    }

    /**
     * Gets a DVD id input from the suer
     * @return DVD id
     */
    public int getDVDIdChoice()
    {
        return io.readInt("Please enter the DVD ID: ");
    }

    /**
     * Gets a DVD id input from the suer
     * @return DVD id
     */
    public String getDVDTitleChoice()
    {
        return io.readString("Please enter the DVD Title: ");
    }

    /**
     * Displays the info for a single DVD
     * @param dvd - a singld DVD object
     */
    public void displayDVD(DVD dvd)
    {
        if(dvd != null)
        {
            io.print(dvd.getTitle());
            io.print(dateFormat.format(dvd.getDate()));
            io.print(dvd.getRating().toString());
            io.print(dvd.getDirector());
            io.print(dvd.getStudio());
            io.print(dvd.getNote());
            io.print("");
        }
        else
        {
            io.print("DVD does not exist.");
        }

        io.readString("Please press enter to continue");
    }

    /**
     * Displays the result of DVD removal to the user
     * @param dvd - DVD object to be deleted
     */
    public void displayRemoveResult(DVD dvd)
    {
        if(dvd != null)
        {
            io.print("DVD successfully removed.");
        }
        else
        {
            io.print("DVD does not exist.");
        }

        io.readString("Please press enter to continue");
    }

    public void displayAddDVDBanner()
    {
        io.print("----------------------- ADD DVD -----------------------");
    }

    public void displayEditDVDBanner()
    {
        io.print("----------------------- EDIT DVD ----------------------");
    }

    public void displayRemoveDVDBanner()
    {
        io.print("---------------------- DELETE DVD ---------------------");
    }

    public void displayDisplayDVDBanner()
    {
        io.print("---------------------- SEARCH DVD ---------------------");
    }

    public void displayDisplayAllBanner()
    {
        io.print("----------------------- ALL DVDS -----------------------");
    }

    public void displayAddSuccessBanner()
    {
        io.readString("DVD successfully created. Please hit enter to continue.");
    }

    public void displayEditSuccessBanner()
    {
        io.readString("DVD successfully edited. Please hit enter to continue.");
    }

    public void displayEntryBanner()
    {
        io.print("===================== DVD LIBRARY =====================");
    }

    public void displayExitBanner()
    {
        io.print("Thank you for using dto.DVD Library!");
        io.print("=======================================================");
    }

    public void displayUnknownCommandBanner()
    {
        io.print("Unknown command.");
    }

    public void displayErrorMessage(String errorMsg)
    {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}

