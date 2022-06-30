package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class DVDLibraryAuditDaoFileImpl implements DVDLibraryAuditDao
{
    private static final String AUDIT_FILE = "auditLibrary.txt";

    @Override
    public void writeAuditEntry(String entry) throws DVDLibraryPersistenceException
    {
        PrintWriter writer;

        try
        {
            writer = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e)
        {
            throw new DVDLibraryPersistenceException("Could not persist audit information.", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();
        writer.println(timestamp.toString() + " : " + entry);
        writer.flush();
    }
}
