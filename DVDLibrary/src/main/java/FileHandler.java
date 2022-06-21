import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler
{
    String fileName;

    public FileHandler(String fileName)
    {
        this.fileName = fileName;
    }

    public void writeToFile(List<DVD> text) throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));

        for(DVD dvd : text)
        {
            writer.println(dvd.getTitle() + "::" + dvd.getDirector() + "::" + dvd.getStudio() + "::" +
                    dvd.getNote() + "::" + dvd.getDate() + "::" + dvd.getRating());
        }

        writer.flush();
        writer.close();
    }

    public List<String> readFromFile() throws FileNotFoundException
    {
        List<String> fileText = new ArrayList<>();
        Scanner reader = new Scanner(new BufferedReader(new FileReader(fileName)));

        while (reader.hasNextLine())
        {
            fileText.add(reader.nextLine());
        }

        reader.close();
        return fileText;
    }
}
