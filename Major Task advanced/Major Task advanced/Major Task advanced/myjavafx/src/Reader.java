import java.util.ArrayList;


public class Reader extends User {
    private ArrayList<Book> orderList;

    public Reader(String id, String password, String firstName, String lastName, String address,
                  String cellPhone, String email, boolean isBlocked, String type) {
        super(id, password, firstName, lastName, address, cellPhone, email, isBlocked, "Reader");
        orderList = new ArrayList<Book>();
    }

    public ArrayList<Book> getOrderList() {
        return orderList;
    }
    public void addToOrderList(Book book) {
        orderList.add(book);
    }

    public void setOrderList(ArrayList<Book> orderList) {
        this.orderList = orderList;
    }
}



