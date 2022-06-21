import java.util.Date;

public class DVD
{
    String title, director, studio, note;
    Date date;
    MPAARating rating;

    public DVD(String title, String director, String studio, String note, Date date, MPAARating rating)
    {
        this.title = title;
        this.director = director;
        this.studio = studio;
        this.note = note;
        this.date = date;
        this.rating = rating;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDirector()
    {
        return director;
    }

    public String getStudio()
    {
        return studio;
    }

    public String getNote()
    {
        return note;
    }

    public Date getDate()
    {
        return date;
    }

    public MPAARating getRating()
    {
        return rating;
    }
}
