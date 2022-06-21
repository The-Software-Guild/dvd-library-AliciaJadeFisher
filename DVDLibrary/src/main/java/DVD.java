import java.util.Date;

public class DVD
{
    private String title, director, studio, note;
    private Date date;
    private MPAARating rating;

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

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    public String getStudio()
    {
        return studio;
    }

    public void setStudio(String studio)
    {
        this.studio = studio;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public MPAARating getRating()
    {
        return rating;
    }

    public void setRating(MPAARating rating)
    {
        this.rating = rating;
    }
}
