import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class which deals with writing to and reading from a file
 */
public class FileHandler
{
    String fileName;

    /**
     * Main constructor
     * @param fileName - name of the file to be used
     * @throws IOException
     * @throws ParseException
     */
    public FileHandler(String fileName) throws IOException, ParseException
    {
        this.fileName = fileName;
        createFile();
    }

    /**
     * Method which deals with file creation
     * @throws IOException
     * @throws ParseException
     */
    public void createFile() throws IOException, ParseException
    {
        File file = new File(fileName);

        // Checks if a file exits
        // If it does, then it reads from it
        // If it doesn't, then it creates one
        if(file.exists())
        {
            readFromFile();
        }
        else
        {
            file.createNewFile();
        }
    }

    /**
     * Method which writes a hashMap to file
     * @param library - hashMap of DVDs
     * @throws IOException
     */
    public void writeToFile(HashMap<Integer, DVD> library) throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));

        for(DVD dvd : library.values())
        {
            writer.println(dvd.getTitle() + "::" + dvd.getDirector() + "::" + dvd.getStudio() + "::" +
                    dvd.getNote() + "::" + dvd.getDate() + "::" + dvd.getRating());
        }

        writer.flush();
        writer.close();
    }

    /**
     * Method which reads every line from a file
     * @return library - hashMap of DVDs
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public HashMap<Integer, DVD> readFromFile() throws FileNotFoundException, ParseException
    {
        HashMap<Integer, DVD> library = new HashMap<>();

        // Loops through each line of the file
        // Splits each line into it's parts and saves them as a DVD object, before putting the object into the HashMap
        if(new File(fileName).length() != 0)
        {
            Scanner reader = new Scanner(new BufferedReader(new FileReader(fileName)));
            int index = 0;

            while(reader.hasNextLine())
            {
                String currentLine = reader.nextLine();
                String[] parts = currentLine.split("::");

                library.put(index, new DVD(parts[0], parts[1], parts[2], parts[3],
                        new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy").parse(parts[4]), MPAARating.valueOf(parts[5])));

                index++;
            }

            reader.close();
        }
        return library;
    }
}
