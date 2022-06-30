package dao;

public class DVDLibraryPersistenceException extends Exception
{
    public DVDLibraryPersistenceException(String message)
    {
        super(message);
    }

    public DVDLibraryPersistenceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
