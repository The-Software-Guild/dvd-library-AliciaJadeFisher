import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
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

    public static void displayMenu()
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

                System.out.println(i + " - " + dvd.getTitle() + " | " + dvd.getDate() + " | " + dvd.getRating() + " | " +
                        dvd.getDirector() + " | " + dvd.getStudio() + " | " + dvd.getNote() + " | ");
            }
        }
    }
    public static void addDVD()
    {
        System.out.println("----------------------- ADD DVD -----------------------");

        System.out.print("Title: ");
        String title = input.nextLine();

        System.out.print("Release Date: ");


    }
    public static void editDVD(){}
    public static void deleteDVD(){}
}
