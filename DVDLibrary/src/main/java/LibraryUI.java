import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class LibraryUI
{
    static int invalidInput = -99;
    static DVDLibrary dvdLibrary;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException
    {
        dvdLibrary = new DVDLibrary();
        System.out.println("===================== DVD LIBRARY =====================");
        displayMenu();
    }

    public static void displayMenu() throws IOException
    {

        System.out.println("-----------------------------");
        System.out.println("1 - View Library");
        System.out.println("2 - Add DVD to Library");
        System.out.println("3 - Edit DVD in Library");
        System.out.println("4 - Remove DVD from Library");
        System.out.println("5 - Search Library");
        System.out.println("0 - Exit");
        System.out.println("-----------------------------");
        System.out.print("What would you like to do? (0-5): ");

        int menuChoice;

        while(true)
        {
            try{
                menuChoice = Integer.parseInt(input.nextLine());

                if(menuChoice < 0 || menuChoice > 5)
                {
                    System.out.print("Please enter a valid menu option (0, 1, 2, 3, 4 or 5): ");
                }
                else
                {
                    break;
                }
            }
            catch(NumberFormatException e)
            {
                System.out.print("Please enter a valid menu option (0, 1, 2, 3, 4 or 5): ");
            }
        }

        switch (menuChoice)
        {
            case 1 -> viewDVDLibrary(true);
            case 2 -> addDVD();
            case 3 -> editDVD();
            case 4 -> deleteDVD();
            case 5 -> searchDVD();
            default ->
            {
                dvdLibrary.saveLibrary();
                System.out.println("Thank you for using DVD Library!");
                System.out.println("=======================================================");
            }
        }
    }
    public static void addDVD() throws IOException
    {
        System.out.println("----------------------- ADD DVD -----------------------");

        String title = getTitleInput();
        Date date = getDateInput();
        MPAARating rating = getRatingInput();
        String director = getDirectorInput();
        String studio = getStudioInput();
        String note = getNoteInput();

        DVD dvd = new DVD(title, director, studio, note, date, rating);
        dvdLibrary.addDVD(dvd);
        displayMenu();
    }
    public static void editDVD() throws IOException
    {
        DVD dvd;
        System.out.println("---------------------- EDIT DVD ----------------------");
        viewDVDLibrary(false);

        System.out.print("Enter the number of the DVD you would like to edit: ");

        int index;
        while(true)
        {
            try
            {
                index = Integer.parseInt(input.nextLine());

                if(dvdLibrary.library.containsKey(index))
                {
                    break;
                }
                else
                {
                    System.out.print("Please enter a valid DVD number from the above list: ");
                }

            }
            catch (NumberFormatException e)
            {
                System.out.print("Please enter a valid DVD number from the above list: ");
            }
        }

        dvd = dvdLibrary.library.get(index);

        loop: while (true)
        {
            displayDVD(index, dvd);
            System.out.println("Which field would you like edit?");
            System.out.println("1 - Title");
            System.out.println("2 - Release Date");
            System.out.println("3 - MPAA Rating");
            System.out.println("4 - Director");
            System.out.println("5 - Studio");
            System.out.println("6 - Note");
            System.out.println("0 - Return to Main Menu");
            System.out.print("Field (#): ");
            int choice;

            while(true)
            {
                try{
                    choice = Integer.parseInt(input.nextLine());

                    if(choice < 0 || choice > 6)
                    {
                        System.out.print("Please enter a valid menu option (0, 1, 2, 3, 4, 5 or 6): ");
                    }
                    else
                    {
                        break;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.print("Please enter a valid menu option (0, 1, 2, 3, 4, 5 or 6): ");
                }
            }

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

            dvdLibrary.editDvd(index,dvd);

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

        displayMenu();
    }
    public static void deleteDVD() throws IOException
    {
        System.out.println("--------------------- DELETE DVD ---------------------");
        viewDVDLibrary(false);

        System.out.print("Enter the number of the DVD you would like to delete: ");

        int index;
        while(true)
        {
            try
            {
                index = Integer.parseInt(input.nextLine());

                if(dvdLibrary.library.containsKey(index))
                {
                    break;
                }
                else
                {
                    System.out.print("Please enter a valid DVD number from the above list: ");
                }

            }
            catch (NumberFormatException e)
            {
                System.out.print("Please enter a valid DVD number from the above list: ");
            }
        }

        dvdLibrary.deleteDVD(index);
        displayMenu();
    }

    public static void searchDVD() throws IOException
    {
        System.out.println("---------------------- SEARCH DVD ----------------------");
        System.out.print("Enter the title of the DVD you would like to search for?: ");
        String title = input.nextLine();

        DVD dvd = dvdLibrary.searchDvd(title);

        if(dvd == null)
        {
            System.out.println(title + " not found in library");
        }
        else
        {
            displayDVD(dvd);
        }

        System.out.println();
        System.out.println("1 - Search Again");
        System.out.println("0 - Return to Main Menu");
        int rtrn;

        while(true)
        {
            try
            {
                rtrn = Integer.parseInt(input.nextLine());

                if(rtrn == 0)
                {
                    displayMenu();
                    break;
                }

            }catch (NumberFormatException ignored){
                rtrn = invalidInput;
            }

            if(rtrn == invalidInput)
            {

                System.out.print("Please enter 0 to return to the main menu: ");
            }
            else
            {
                searchDVD();
            }
        }
    }

    public static void viewDVDLibrary(boolean menu) throws IOException
    {
        HashMap<Integer, DVD> library = dvdLibrary.getLibrary();
        System.out.println("---------------------- DVD LIST -----------------------");

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

        if(menu)
        {
            System.out.println();
            System.out.println("0 - Return to Main Menu");
            int rtrn;

            while(true)
            {
                try
                {
                    rtrn = Integer.parseInt(input.nextLine());

                    if(rtrn == 0)
                    {
                        displayMenu();
                        break;
                    }

                }catch (NumberFormatException ignored){}

                System.out.print("Please enter 0 to return to the main menu: ");
            }


        }
    }

    public static String getTitleInput()
    {
        System.out.print("Title: ");
        String title = input.nextLine();
        System.out.println();

        return title;
    }

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

    public static MPAARating getRatingInput()
    {
        System.out.println("MPAA Rating");
        System.out.println("1 - G");
        System.out.println("2 - PG");
        System.out.println("3 - PG-13");
        System.out.println("4 - R");
        System.out.println("5 - NC-17");
        System.out.print("Rating (1-5): ");

        int ratingChoice;

        while(true)
        {
            try{
                ratingChoice = Integer.parseInt(input.nextLine());

                if(ratingChoice < 1 || ratingChoice > 5)
                {
                    System.out.print("Please enter a valid rating option (1, 2, 3, 4, or 5): ");
                }
                else
                {
                    break;
                }
            }
            catch(NumberFormatException e)
            {
                System.out.print("Please enter a valid rating option (1, 2, 3, 4, or 5): ");
            }
        }

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

    public static String getDirectorInput()
    {
        System.out.print("Director: ");
        String director = input.nextLine();
        System.out.println();

        return director;
    }

    public static String getStudioInput()
    {
        System.out.print("Studio: ");
        String studio = input.nextLine();
        System.out.println();

        return studio;
    }

    public static String getNoteInput()
    {
        System.out.print("Note: ");
        String note = input.nextLine();
        System.out.println();

        return note;
    }
    public static void displayDVD(Integer i, DVD dvd)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(i + " - " + dvd.getTitle() + " | " + dateFormat.format(dvd.getDate()) + " | " + dvd.getRating() + " | " +
                dvd.getDirector() + " | " + dvd.getStudio() + " | " + dvd.getNote());
    }

    public static void displayDVD(DVD dvd)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(dvd.getTitle() + " | " + dateFormat.format(dvd.getDate()) + " | " + dvd.getRating() + " | " +
                dvd.getDirector() + " | " + dvd.getStudio() + " | " + dvd.getNote());
    }
}
