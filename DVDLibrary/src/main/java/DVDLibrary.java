import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

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
       int index = library.keySet().size();
       library.put(index, dvd);
    }

    public void deleteDVD(int index)
    {
        library.remove(index);
    }

    public void editDvd(int index, DVD dvd)
    {
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
