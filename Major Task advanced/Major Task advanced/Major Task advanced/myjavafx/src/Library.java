import java.util.ArrayList;
import java.util.Iterator;
public class Library {
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<Reader> readers = new ArrayList<Reader>();
    ArrayList<Librarian> librarians = new ArrayList<Librarian>();

    public Library() {

        Librarian librarian1 = new Librarian("12345", "12345", "Anthony", "Davies",
                "23rd Wall Street", "+201011155234", "anthony@gmail.com",
                false, "librarian");


        Reader reader1 = new Reader("21345", "21345", "Luka",
                "Modric", "22nd Wall Street", "+01011477682",
                "Modric@gmail.com", false, "reader");
        Reader reader2 = new Reader("1", "1", "L",
                "Mo", "22nd Wall Street", "+01011477682",
                "Modric@gmail.com", false, "reader");


        Book book1 = new Book("Harry Potter", "JK Rowling", "12345");


        librarians.add(librarian1);


        readers.add(reader1);
        readers.add(reader2);


        books.add(book1);


        users.add(librarian1);

        users.add(reader1);
        users.add(reader2);

    }

    // This method checks the user's credentials and returns the user's type (librarian or reader)
    public User getUserType(String id, String password, ArrayList<User> users) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                if (user.getType().equalsIgnoreCase("Librarian")
                        || user.getType().equalsIgnoreCase("Reader"))
                    return user;
            }
        }
        return null;
    }

    public Boolean CompareDataFields(String DATA, ArrayList<User> users) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user1 = iterator.next();
            if (user1.getId().equals(DATA)) {
                return true;
            }
        }
        return false;
    }
    public Boolean CompareDataFieldsPassword(String DATA, ArrayList<User> users) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user1 = iterator.next();
            if (user1.getPassword().equals(DATA)) {
                return true;
            }
        }
        return false;
    }
    public Book Block(Reader reader) {


        if (reader.isBlocked() == false) {
            ArrayList<Book> OrderList = reader.getOrderList();
            if (OrderList.isEmpty()) {
                System.out.println("Order list is empty");
            } else {
                for (int i = 0; i < OrderList.size(); i++) {
                    Book book = OrderList.get(i);
                    //if user has the book for 5 days
                    if (book.getRentdate().getDay() >= 5) {
                        return book;
                    }
                }
            }
        }
        return null;
    }

    public Book SearchBook(String Serial, ArrayList<Book> books) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getSerial().equals(Serial)) {
                return book;
            }
        }
        return null;
    }

    public Book BookRemoval(String Title, String isbn, ArrayList<Book> books) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getTitle().equals(Title) && book.getSerial().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public User SearchU(String ID, ArrayList<User> users) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(ID)) {
                return user;
            }
        }
        return null;
    }

    public boolean EmailCheck(String Email) {
        if (Email.contains("@gmail.com"))
            return true;
        if (Email.contains("@hotmail.com"))
            return true;
        if (Email.contains("@yahoo.com"))
            return true;
        return false;
    }
}
