package dao;

import dto.DVD;
import dto.MPAARating;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao
{
    private Map<Integer, DVD> dvdLibrary = new HashMap<>();
    public static final String LIBRARY_FILE = "DVDLibrary.txt";
    public static final String DELIMITER = "::";

    /**
     * Adds a DVD object to the HashMap and save the changes to the file
     * @param dvd - DVD object to add
     * @return - DVD object added to HashMap
     * @throws DVDLibraryDaoException
     * @throws ParseException
     */
    @Override
    public DVD addDvd(DVD dvd) throws DVDLibraryDaoException, ParseException
    {
        readLibrary();
        DVD newDVD = dvdLibrary.put(dvdLibrary.size(), dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public DVD editDvd(Integer index, DVD dvd) throws DVDLibraryDaoException, ParseException
    {
        readLibrary();
        DVD editedDVD = dvdLibrary.put(index, dvd);
        writeLibrary();
        return editedDVD;
    }
    /**
     * Gets all DVD objects in the HashMap
     * @return a HashMap of all DVD Objects
     * @throws DVDLibraryDaoException
     * @throws ParseException
     */
    @Override
    public Map<Integer, DVD> getAllDVDs() throws DVDLibraryDaoException, ParseException
    {
        readLibrary();
        return dvdLibrary;
    }

    /**
     * Gets a single DVD object from the HashMap
     * @param index - index of specified DVD object
     * @return specified DVD object
     * @throws DVDLibraryDaoException
     * @throws ParseException
     */
    @Override
    public DVD getDVD(Integer index) throws DVDLibraryDaoException, ParseException
    {
        readLibrary();
        return dvdLibrary.get(index);
    }

    @Override
    public DVD searchDVD(String title) throws DVDLibraryDaoException, ParseException
    {
        readLibrary();

        for(DVD dvd: dvdLibrary.values())
        {
            if(dvd.getTitle().toLowerCase().equals(title.toLowerCase()))
            {
                return dvd;
            }
        }

        return null;
    }

    /**
     * Removes a DVD from the HashMap and saves the changes to the file
     * @param index - index of DVD object to be deleted
     * @return DVD object which was deleted
     * @throws DVDLibraryDaoException
     * @throws ParseException
     */
    @Override
    public DVD removeDVD(Integer index) throws DVDLibraryDaoException, ParseException
    {
        readLibrary();
        DVD removedDVD = dvdLibrary.remove(index);
        writeLibrary();
        return removedDVD;
    }

    /**
     * Reads the data from the test file
     * @throws DVDLibraryDaoException
     */
    private void readLibrary() throws DVDLibraryDaoException, ParseException
    {
        Scanner reader;

        // Creates a scanner for reading from the file
        try
        {
            reader = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        }
        catch (FileNotFoundException e)
        {
            throw new DVDLibraryDaoException("-_- Could not load library data into memory", e);
        }

        int index = 0;

        // Loops through the file and reads each line and adds each DVD object to the HasHMap
        while(reader.hasNextLine())
        {
            String currentLine =  reader.nextLine();
            DVD currentDVD = unmarshallDVD(currentLine);

            dvdLibrary.put(index, currentDVD);
            index++;
        }

        reader.close();
    }

    /**
     * Writes the list of DVDs to the file
     * @throws DVDLibraryDaoException
     */
    private void writeLibrary() throws DVDLibraryDaoException
    {
        PrintWriter writer;

        // Creates a writer to write to the file
        try
        {
            writer = new PrintWriter(new FileWriter(LIBRARY_FILE));
        }
        catch (IOException e)
        {
            throw new DVDLibraryDaoException("-_- Could not save DVD data.", e);
        }

        // Gets a list of all DVD object and loops through each element to write it to the file
        for(DVD dvd : dvdLibrary.values())
        {
            String fileText = marshallDVD(dvd);
            writer.println(fileText);
            writer.flush();
        }

        writer.close();
    }

    /**
     * Converts a file line to a DVD object
     * @param dvdText - line from file to be converted
     * @return converted DVD object
     * @throws ParseException
     */
    private DVD unmarshallDVD(String dvdText) throws ParseException
    {
        // Splits the text on the delimiter and creates a new DVD object from the split parts
        String[] dvdParts = dvdText.split(DELIMITER);
        return new DVD(dvdParts[0], dvdParts[1], dvdParts[2], dvdParts[3],
                new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy").parse(dvdParts[4]), MPAARating.valueOf(dvdParts[5]));
    }

    /**
     * Converts a DVD object into a file line
     * @param dvd - DVD object to be converted
     * @return coverted file lline
     */
    private String marshallDVD(DVD dvd)
    {
        return dvd.getTitle() + DELIMITER + dvd.getDirector() + DELIMITER + dvd.getStudio() + DELIMITER +
                dvd.getNote() + DELIMITER + dvd.getDate() + DELIMITER + dvd.getRating();
    }
}
