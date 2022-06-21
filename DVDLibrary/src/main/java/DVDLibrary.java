import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DVDLibrary
{
    int indexNotFound = -99;
    HashMap<Integer,DVD> library;
    FileHandler handler;

    public DVDLibrary() throws IOException, ParseException
    {
        handler = new FileHandler("DVDLibrary.txt");
        readLibrary();
    }

    public HashMap<Integer,DVD> getLibrary()
    {
        return library;
    }

    public void readLibrary() throws FileNotFoundException, ParseException
    {
        library = handler.readFromFile();
    }

    public void saveLibrary() throws IOException
    {
        handler.writeToFile(library);
    }

    public void addDVD(DVD dvd)
    {
       int index = library.keySet().size() + 1;
       library.put(index, dvd);
    }

    public void deleteDvd(DVD dvd)
    {
        library.remove(getIndex(dvd));
    }

    public void editDvd(int index)
    {
        DVD dvd = library.get(index);

        // code to update values here

        library.put(index,dvd);
    }

    public int getIndex(DVD dvd)
    {
        for(int i : library.keySet())
        {
            if(library.get(i).equals(dvd))
            {
                return i;
            }
        }
        return indexNotFound;
    }

}
