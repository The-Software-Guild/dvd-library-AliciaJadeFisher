package ui;

import controller.DVDLibrary;
import dto.DVD;
import dto.MPAARating;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class which handles all program input and output
 */
public class LibraryUI
{
    static int invalidInput = -99;
     static DVDLibrary dvdLibrary;
    static Scanner input = new Scanner(System.in);


    /**
     * Main method
     * @param args
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws IOException, ParseException
    {
        dvdLibrary = new DVDLibrary();
        System.out.println("===================== DVD LIBRARY =====================");
        displayMenu();
    }

    /**
     * Displays the main menu to the user
     * @throws IOException
     */
    public static void displayMenu() throws IOException
    {
        // Menu options
        System.out.println("-----------------------------");
        System.out.println("1 - View Library");
        System.out.println("2 - Add dto.DVD to Library");
        System.out.println("3 - Edit dto.DVD in Library");
        System.out.println("4 - Remove dto.DVD from Library");
        System.out.println("5 - Search Library");
        System.out.println("0 - Exit");
        System.out.println("-----------------------------");
        System.out.print("What would you like to do? (0-5): ");
        int menuChoice = validateIntInput(0, 5);

        // Calls the method relevant to the menu choice
        switch (menuChoice)
        {
            case 1 -> viewDVDLibrary(true);
            case 2 -> addDVD();
            case 3 -> editDVD();
            case 4 -> deleteDVD();
            case 5 -> searchDVD();
            default ->
            {
                // Calls the method to save the library and exits the program
                dvdLibrary.saveLibrary();
                System.out.println("Thank you for using dto.DVD Library!");
                System.out.println("=======================================================");
            }
        }
    }

    /**
     * Gets the new dto.DVD inputs and adds it to the library
     * @throws IOException
     */
    public static void addDVD() throws IOException
    {
        System.out.println("----------------------- ADD DVD -----------------------");

        // Get dto.DVD inputs from the user
        String title = getTitleInput();
        Date date = getDateInput();
        MPAARating rating = getRatingInput();
        String director = getDirectorInput();
        String studio = getStudioInput();
        String note = getNoteInput();

        // Creates a new dto.DVD object and calls the method to add it to the library
        DVD dvd = new DVD(title, director, studio, note, date, rating);
        dvdLibrary.addDVD(dvd);
        System.out.println("- " + title +" added to library");

        // Menu options
        System.out.println();
        System.out.println("1 - Add Another dto.DVD");
        System.out.println("0 - Return to Main Menu");

        // Checks if the user wants to return or add another dto.DVD
        if(validateReturn())
        {
            displayMenu();
        }
        else
        {
            addDVD();
        }
    }

    /**
     * Gets updated dto.DVD inputs and updates the library
     * @throws IOException
     */
    public static void editDVD() throws IOException
    {
        DVD dvd;
        System.out.println("---------------------- EDIT dto.DVD ----------------------");
        viewDVDLibrary(false);

        // Gets the selected dto.DVD object from the user
        System.out.print("Enter the number of the dto.DVD you would like to edit: ");
        int index = validateDVDIndex();
        dvd = dvdLibrary.getLibrary().get(index);

        // Loops for as many times as the user wants to edit the dto.DVD object
        loop: while (true)
        {
            displayDVD(index, dvd);

            // Menu options
            System.out.println("Which field would you like edit?");
            System.out.println("1 - Title");
            System.out.println("2 - Release Date");
            System.out.println("3 - MPAA Rating");
            System.out.println("4 - Director");
            System.out.println("5 - Studio");
            System.out.println("6 - Note");
            System.out.println("0 - Return to Main Menu");
            System.out.print("Field (#): ");
            int choice = validateIntInput(0,6);

            // Calls the relevant input method dependent on the field the user selected to edit
            switch (choice)
            {
                case 1 -> dvd.setTitle(getTitleInput());
                case 2 -> dvd.setDate(getDateInput());
                case 3 -> dvd.setRating(getRatingInput());
                case 4 -> dvd.setDirector(getDirectorInput());
                case 5 -> dvd.setStudio(getStudioInput());
                case 6 -> dvd.setNote(getNoteInput());
                default ->
                {
                    break loop;
                }
            }

            // Calls the method to update the dto.DVD object in library
            dvdLibrary.editDvd(index,dvd);

            // Checks if the user wants to continue editing the same dto.DVD object
            System.out.print("Would you like to edit another field? (Y/N): ");
            String cont;
            while (true)
            {
                cont = input.nextLine().toUpperCase();

                if(cont.equals("Y") ||cont.equals("N"))
                {
                    break;
                }
                else
                {
                    System.out.print("Please enter either Y or N: ");
                }
            }

            if(cont.equals("N"))
            {
                break;
            }

        }

        // Menu options
        System.out.println();
        System.out.println("1 - Edit Another dto.DVD");
        System.out.println("0 - Return to Main Menu");

        // Checks if the user wants to return to the main menu, or edit another dto.DVD object
        if(validateReturn())
        {
            displayMenu();
        }
        else
        {
            editDVD();
        }
    }

    /**
     * Deletes a specified dto.DVD object
     * @throws IOException
     */
    public static void deleteDVD() throws IOException
    {
        System.out.println("--------------------- DELETE dto.DVD ---------------------");
        viewDVDLibrary(false);

        // Gets the index of the specified dto.DVD object and calls the method to delete it
        System.out.print("Enter the number of the dto.DVD you would like to delete: ");
        dvdLibrary.deleteDVD(validateDVDIndex());
        System.out.println("- dto.DVD deleted from the library");

        // Menu options
        System.out.println();
        System.out.println("1 - Delete Another dto.DVD");
        System.out.println("0 - Return to Main Menu");

        // Checks if the user wants to return to the main menu or delete another dto.DVD object
        if(validateReturn())
        {
            displayMenu();
        }
        else
        {
            deleteDVD();
        }
    }

    /**
     * Allows a user to search for a dto.DVD by title
     * @throws IOException
     */
    public static void searchDVD() throws IOException
    {
        System.out.println("---------------------- SEARCH dto.DVD ----------------------");

        // Gets the dvd title from the user and calls the method to search the library for it
        System.out.print("Enter the title of the dto.DVD you would like to search for?: ");
        String title = input.nextLine();
        DVD dvd = dvdLibrary.searchDvd(title);

        // Checks the result of the search
        // Tells the user if it was not found, or displays the dto.DVD if it was found
        if(dvd == null)
        {
            System.out.println(title + " not found in library");
        }
        else
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println(dvd.getTitle() + " | " + dateFormat.format(dvd.getDate()) + " | " + dvd.getRating() + " | " +
                    dvd.getDirector() + " | " + dvd.getStudio() + " | " + dvd.getNote());
        }

        // Menu options
        System.out.println();
        System.out.println("1 - Search Again");
        System.out.println("0 - Return to Main Menu");

        // Checks if the user wants to return to the main menu or search for another dto.DVD
        if(validateReturn())
        {
            displayMenu();
        }
        else
        {
            searchDVD();
        }
    }

    /**
     * Displays all dto.DVD objects in the library
     * @param menu - whether this method was called from the main menu or not
     * @throws IOException
     */
    public static void viewDVDLibrary(boolean menu) throws IOException
    {
        System.out.println("---------------------- dto.DVD LIST -----------------------");

        // Gets the library from the DataLibrary object
        HashMap<Integer, DVD> library = dvdLibrary.getLibrary();

        // Checks if the library is empty
        // Tells the user if it is empty, displays each dto.DVD if it is not empty
        if(library.isEmpty())
        {
            System.out.println(" -- No DVDs in library --");
        }
        else
        {
            for (Integer i : library.keySet())
            {
                DVD dvd = library.get(i);
                displayDVD(i,dvd);
            }
        }

        // Checks if the method was called from the main menu
        if(menu)
        {
            // Menu options
            System.out.println();
            System.out.println("0 - Return to Main Menu");

            // Checks if the user wants to return to the main menu
            if(validateReturn())
            {
                displayMenu();
            }
        }
    }

    /**
     * Gets the title input from the user
     * @return title
     */
    public static String getTitleInput()
    {
        System.out.print("Title: ");
        String title = input.nextLine();
        System.out.println();

        return title;
    }

    /**
     * Gets the date input from the user
     * @return date
     */
    public static Date getDateInput()
    {
        System.out.print("Release Date (DD/MM/YYYY): ");
        Date date;
        while(true)
        {
            try
            {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(input.nextLine());
                break;
            }
            catch (ParseException e)
            {
                System.out.print("Please enter a valid date (DD/MM/YYYY): ");
            }
        }
        System.out.println();

        return date;
    }

    /**
     * Gets the rating input from the user
     * @return rating
     */
    public static MPAARating getRatingInput()
    {
        System.out.println("MPAA Rating");
        System.out.println("1 - G");
        System.out.println("2 - PG");
        System.out.println("3 - PG-13");
        System.out.println("4 - R");
        System.out.println("5 - NC-17");
        System.out.print("Rating (1-5): ");
        int ratingChoice = validateIntInput(1,5);

        MPAARating rating = null;
        switch (ratingChoice)
        {
            case 1 -> rating = MPAARating.G;
            case 2 -> rating = MPAARating.PG;
            case 3 -> rating = MPAARating.PG13;
            case 4 -> rating = MPAARating.R;
            case 5 -> rating = MPAARating.NC17;
        }
        System.out.println();

        return rating;
    }

    /**
     * Gets the director input from the user
     * @return director
     */
    public static String getDirectorInput()
    {
        System.out.print("Director: ");
        String director = input.nextLine();
        System.out.println();

        return director;
    }

    /**
     * Gets the studio input from the user
     * @return studio
     */
    public static String getStudioInput()
    {
        System.out.print("Studio: ");
        String studio = input.nextLine();
        System.out.println();

        return studio;
    }

    /**
     * Gets the note input from the user
     * @return note
     */
    public static String getNoteInput()
    {
        System.out.print("Note: ");
        String note = input.nextLine();
        System.out.println();

        return note;
    }

    /**
     * Displays a single dto.DVD object with its index
     * @param i
     * @param dvd
     */
    public static void displayDVD(Integer i, DVD dvd)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(i + " - " + dvd.getTitle() + " | " + dateFormat.format(dvd.getDate()) + " | " + dvd.getRating() + " | " +
                dvd.getDirector() + " | " + dvd.getStudio() + " | " + dvd.getNote());
    }

    /**
     * Validates input for retuning to the main menu
     * @return if the user wants to return to the main menu or not
     */
    public static boolean validateReturn()
    {
        int rtrn;
        while(true)
        {
            try
            {
                rtrn = Integer.parseInt(input.nextLine());

                if(rtrn == 0)
                {
                    return true;
                }

            }catch (NumberFormatException ignored){
                rtrn = invalidInput;
            }

            if(rtrn == invalidInput)
            {

                System.out.print("Please enter a valid menu option from above: ");
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * Validates an int input
     * @param lb - lower bound for input
     * @param ub - upper bound for input
     * @return choice
     */
    public static int validateIntInput(int lb, int ub)
    {
        int choice;

        while(true)
        {
            try{
                choice = Integer.parseInt(input.nextLine());

                if(choice >= lb && choice <= ub)
                {
                    return choice;
                }
            }
            catch(NumberFormatException e)
            {
                continue;
            }

            System.out.print("Please enter a option from above: ");
        }
    }

    /**
     * Validates an index input
     * @return a validated dto.DVD index
     */
    public static int validateDVDIndex()
    {
        int index;
        while(true)
        {
            try
            {
                index = Integer.parseInt(input.nextLine());

                if(dvdLibrary.hasIndex(index))
                {
                    return index;
                }

            }
            catch (NumberFormatException e)
            {
                continue;
            }

            System.out.print("Please enter a valid dto.DVD number from the above list: ");
        }
    }
}