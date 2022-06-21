import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class LibraryUI
{
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
        viewDVDLibrary();

        int menuChoice;
        System.out.println("-----------------------------");
        System.out.println("1 - Add DVD to Library");
        System.out.println("2 - Edit DVD in Library");
        System.out.println("3 - Remove DVD from Library");
        System.out.println("0 - Exit");
        System.out.println("-----------------------------");
        System.out.print("What would you like to do? (0-3): ");

        while(true)
        {
            try{
                menuChoice = Integer.parseInt(input.nextLine());

                if(menuChoice < 0 || menuChoice > 3)
                {
                    System.out.print("Please enter a valid menu option (0, 1, 2, 3, or 4): ");
                }
                else
                {
                    break;
                }
            }
            catch(NumberFormatException e)
            {
                System.out.print("Please enter a valid menu option (0, 1, 2, 3, or 4): ");
            }
        }

        switch (menuChoice)
        {
            case 1 -> addDVD();
            case 2 -> editDVD();
            case 3 -> deleteDVD();
            default ->
            {
                dvdLibrary.saveLibrary();
                System.out.println("Thank you for using DVD Library!");
                System.out.println("=======================================================");
            }
        }
    }

    public static void viewDVDLibrary()
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

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                System.out.println(i + " - " + dvd.getTitle() + " | " + dateFormat.format(dvd.getDate()) + " | " + dvd.getRating() + " | " +
                        dvd.getDirector() + " | " + dvd.getStudio() + " | " + dvd.getNote() + " | ");
            }
        }
    }
    public static void addDVD() throws IOException
    {
        System.out.println("----------------------- ADD DVD -----------------------");

        System.out.print("Title: ");
        String title = input.nextLine();
        System.out.println();

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

        System.out.print("Director: ");
        String director = input.nextLine();
        System.out.println();

        System.out.print("Studio: ");
        String studio = input.nextLine();
        System.out.println();

        System.out.print("Note: ");
        String note = input.nextLine();
        System.out.println();

        DVD dvd = new DVD(title, director, studio, note, date, rating);
        dvdLibrary.addDVD(dvd);
        displayMenu();
    }
    public static void editDVD()
    {
        System.out.println("---------------------- EDIT DVD ----------------------");
        viewDVDLibrary();

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
    }
    public static void deleteDVD() throws IOException
    {
        System.out.println("--------------------- DELETE DVD ---------------------");
        viewDVDLibrary();

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
}
