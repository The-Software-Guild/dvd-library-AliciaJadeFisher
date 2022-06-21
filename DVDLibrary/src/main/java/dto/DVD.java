package dto;

import java.util.Date;

/**
 * Class which represents a single dto.DVD object
 */
public class DVD
{
    // Attributes of dto.DVD
    private String title, director, studio, note;
    private Date date;
    private MPAARating rating;

    /**
     * Main constructor
     * @param title - title of the dvd
     * @param director - director of the dvd
     * @param studio - studio which produced the dvd
     * @param note - personal rating from the user
     * @param date - dvd release date
     * @param rating - MPAA rating
     */
    public DVD(String title, String director, String studio, String note, Date date, MPAARating rating)
    {
        this.title = title;
        this.director = director;
        this.studio = studio;
        this.note = note;
        this.date = date;
        this.rating = rating;
    }

    /**
     * Retrieves the dvd title
     * @return title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the dvd title
     * @param title - title input from the user
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Retrieves the dvd director
     * @return director
     */
    public String getDirector()
    {
        return director;
    }

    /**
     * Sets the dvd director
     * @param director - director input from the user
     */
    public void setDirector(String director)
    {
        this.director = director;
    }

    /**
     * Retrieves the dvd studio
     * @return studio
     */
    public String getStudio()
    {
        return studio;
    }

    /**
     * Sets the dvd studio
     * @param studio - studio input from the user
     */
    public void setStudio(String studio)
    {
        this.studio = studio;
    }

    /**
     * Retrieves the dvd note
     * @return note
     */
    public String getNote()
    {
        return note;
    }

    /**
     * Sets the dvd note
     * @param note - note input from the user
     */
    public void setNote(String note)
    {
        this.note = note;
    }

    /**
     * Retrieves the dvd date
     * @return date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Sets the dvd date
     * @param date - date input from the user
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * Retrieves the dvd rating
     * @return rating
     */
    public MPAARating getRating()
    {
        return rating;
    }

    /**
     * Sets the dvd rating
     * @param rating - rating input from the user
     */
    public void setRating(MPAARating rating)
    {
        this.rating = rating;
    }
}