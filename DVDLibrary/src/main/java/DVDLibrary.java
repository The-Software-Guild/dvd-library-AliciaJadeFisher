import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

/**
 * Class which represents the DVD Library
 */
public class DVDLibrary
{
    int indexNotFound = -99;

    // Attributes of DVDLibrary
    HashMap<Integer,DVD> library;
    FileHandler handler;

    /**
     * Main constructor
     * @throws IOException
     * @throws ParseException
     */
    public DVDLibrary() throws IOException, ParseException
    {
        handler = new FileHandler("DVDLibrary.txt");
        readLibrary();
    }

    /**
     * Retrieves the library of DVDs
     * @return library
     */
    public HashMap<Integer,DVD> getLibrary()
    {
        return library;
    }

    /**
     * Uses the FileHandler to read the file
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public void readLibrary() throws FileNotFoundException, ParseException
    {
        library = handler.readFromFile();
    }

    /**
     * Uses the FileHandler to write to the file
     * @throws IOException
     */
    public void saveLibrary() throws IOException
    {
        handler.writeToFile(library);
    }

    /**
     * Adds a new DVD to the library
     * @param dvd - dvd object input from the user
     */
    public void addDVD(DVD dvd)
    {
       int index = library.keySet().size();
       library.put(index, dvd);
    }

    /**
     * Deletes a DVD from the library
     * @param index - library index input from the user
     */
    public void deleteDVD(int index)
    {
        library.remove(index);
    }

    /**
     * Updates a DVD in the library
     * @param index - library index input from the user
     * @param dvd - dvd object input from the user
     */
    public void editDvd(int index, DVD dvd)
    {
        library.put(index,dvd);
    }

    /**
     * Searches the library for dvd based on the title
     * @param title - title input from the user
     * @return - dvd object if it isfound, null if it is not found
     */
    public DVD searchDvd(String title)
    {
        for(DVD dvd : library.values())
        {
            if(dvd.getTitle().toLowerCase().equals(title.toLowerCase()))
            {
                return dvd;
            }
        }

        return null;
    }

    /**
     * Checks if an index exists in library
     * @param index - index input from user
     * @return if the index exists or not
     */
    public boolean hasIndex(int index)
    {
        return library.containsKey(index);
    }

}
