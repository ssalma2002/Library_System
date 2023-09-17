import java.util.ArrayList;
import java.util.Scanner;

public class Librarian  extends User {
    public Librarian(String id, String password, String firstName, String lastName,
                     String address, String cellPhone, String email, boolean isBlocked, String type) {
        super(id, password, firstName, lastName, address, cellPhone, email, isBlocked, "Librarian");
    }
}