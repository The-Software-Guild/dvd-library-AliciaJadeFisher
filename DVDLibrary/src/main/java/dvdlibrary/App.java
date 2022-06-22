package dvdlibrary;

import controller.DVDLibraryController;
import dao.DVDLibraryDao;
import dao.DVDLibraryDaoFileImpl;
import ui.DVDLibraryView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

/** EXAMPLE OUTPUT
 * ===================== DVD LIBRARY =====================
 * -----------------------------
 * 1 - View Library
 * 2 - Add DVD to Library
 * 3 - Edit DVD in Library
 * 4 - Remove DVD from Library
 * 5 - Search Library for a DVD
 * 0 - Exit
 * -----------------------------
 * What would you like to do? (0-5):
 * 1
 * ----------------------- ALL DVDS -----------------------
 * 0 - The Three Musketeers | 11-02-1994 | PG | Stephen Herek | Walt Disney Pictures | Amazing film!!
 * 1 - A Knight's Tale | 31-08-2001 | PG | Brian Helgeland | Columbia Pictures | Highly reccomend this one!
 * 2 - Practical Magic | 22-01-1999 | PG13 | Griffin Dunne | Warner Bros. Pictures | Beautiful movie
 * 3 - Serenity | 07-10-2005 | PG13 | Joss Whedon | Universal Studios | Perfect movie
 * Please press enter to continue.
 */

public class App
{
    public static void main(String[] args)
    {
        UserIO uio = new UserIOConsoleImpl();
        DVDLibraryView view = new DVDLibraryView(uio);
        DVDLibraryDao dao = new DVDLibraryDaoFileImpl();

        DVDLibraryController controller = new DVDLibraryController(view,dao);
        controller.run();
    }
}
