import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileHandler
{
    String fileName;

    public FileHandler(String fileName) throws IOException, ParseException
    {
        this.fileName = fileName;
        createFile();
    }

    public void createFile() throws IOException, ParseException
    {
        File file = new File(fileName);
        if(file.exists())
        {
            readFromFile();
        }
        else
        {
            file.createNewFile();
        }
    }

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

    public HashMap<Integer, DVD> readFromFile() throws FileNotFoundException, ParseException
    {
        HashMap<Integer, DVD> library = new HashMap<>();

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
