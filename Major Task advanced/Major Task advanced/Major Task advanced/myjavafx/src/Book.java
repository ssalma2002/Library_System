import java.util.Date;

public class Book {
    private String title;
    private String Author;

    private String Serial;
    private boolean is_available;
    private static int count = 0;
    private Date date;
    private Date Rentdate;

    public Book(String title , String Author , String Serial) {
        this.title = title;
        this.Author = Author;
        this.Serial = Serial;
        count++;
        this.is_available = true;
        this.date = new Date();
    }

    public String getAuthor() {
        return Author;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String serial) {
        Serial = serial;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return title;
    }

    public boolean Is_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getRentdate() {
        return Rentdate;
    }

    public void setRentdate(Date rentdate) {
        Rentdate = rentdate;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title  + '}';
    }

    

    public static int getCount() {
        return count;
    }
}
