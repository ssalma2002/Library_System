
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class App extends Application {

    Library library = new Library();

    // Declare variables for UI components
    private Stage stage;
    private BorderPane root;
    private Scene loginScene;
    private Label loginLabel, idLabel, passwordLabel;
    private TextField idField;
    private PasswordField passwordField;
    private Button loginButton;
    private Label messageLabel;
    private int iteration = 0;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Initialize stage and root pane
        stage = primaryStage;
        root = new BorderPane();

        stage.initStyle(StageStyle.UTILITY);

        // Initialize UI components for login scene
        loginLabel = new Label("Library Management Software App");
        loginLabel.setStyle("-fx-font-size: 24px; -fx-padding: 10px;");
        idLabel = new Label("ID:");
        passwordLabel = new Label("Password:");
        idField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        messageLabel = new Label();

        // Initialize event handler for login button
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                messageLabel.setText("");
                String id = idField.getText();
                String password = passwordField.getText();
                User userType = library.getUserType(id, password, library.users);
                if (userType != null) {
                    if (userType.getType().equals("Librarian")) {
                        showLibrarianDashboard(userType, library.users, library.readers,
                                library.books, library.librarians);
                    } else if (userType.getType().equals("Reader")) {
                        showReaderDashboard(userType, library.users, library.readers,
                                library.books, library.librarians);
                    }
                } else {
                    messageLabel.setText("Invalid ID or password");
                }
            }
        });

        // Initialize UI components for login form
        GridPane loginForm = new GridPane();
        loginForm.setHgap(10);
        loginForm.setVgap(10);
        loginForm.setPadding(new Insets(20));
        loginForm.setAlignment(Pos.CENTER);
        loginForm.add(idLabel, 0, 0);
        loginForm.add(idField, 1, 0);
        loginForm.add(passwordLabel, 0, 1);
        loginForm.add(passwordField, 1, 1);
        loginForm.add(loginButton, 1, 2);
        loginForm.add(messageLabel, 1, 3);

        // Add login label and form to login scene
        loginScene = new Scene(root, 400, 300);
        root.setTop(loginLabel);
        root.setCenter(loginForm);


        // Set the scene for the stage and show it

        stage.setScene(loginScene);

        stage.show();
    }



    // This method shows the librarian dashboard
    private void showLibrarianDashboard(User user, ArrayList<User> users, ArrayList<Reader> readers,
                                        ArrayList<Book> books, ArrayList<Librarian> librarians)
    {
        // Initialize UI components for librarian dashboard
        Label librarianLabel = new Label("Welcome " + user.getFirstName() + " " + user.getLastName());
        Button addButton = new Button("Add Book");
        Button removeButton = new Button("Remove Book");
        Button SearchBook = new Button("Search Book");
        Button SearchUsers = new Button("Search Users");
        Button RemoveUser = new Button("Remove User");
        Button AddUser = new Button("Add User");
        Button BlockUser = new Button("Block User");
        Label lbl = new Label();


        Button logoutButton = new Button("Logout");
        // Set UI component properties
        librarianLabel.setFont(new Font("Arial", 24));
        addButton.setPrefWidth(150);
        removeButton.setPrefWidth(150);
        SearchBook.setPrefWidth(150);
        SearchUsers.setPrefWidth(150);
        RemoveUser.setPrefWidth(150);
        AddUser.setPrefWidth(150);
        BlockUser.setPrefWidth(150);
        lbl.setFont(new Font("Arial", 14));

        logoutButton.setPrefWidth(150);


        // Create layout for librarian dashboard
        VBox librarianBox = new VBox(5, librarianLabel, addButton, removeButton, SearchBook,
                AddUser, RemoveUser, SearchUsers,
                BlockUser, logoutButton , lbl);
        librarianBox.setAlignment(Pos.CENTER);

        // Set event handlers for buttons
        addButton.setOnAction(e -> {
            // Show add book scene
            showAddBookScene(user, users, readers, books, librarians);
        });

        removeButton.setOnAction(e -> {
            // Show remove book scene
            showRemoveBookScene(user, users, readers, books, librarians);
        });
        SearchBook.setOnAction(e -> {
            if(books.size() == 0)
            {
                lbl.setText("No Books exists");
            }
            else
                ShowSearchBook(user, users, readers, books, librarians);
        });

        SearchUsers.setOnAction(e -> {
            showSearchUserScene(user, users, readers, books, librarians);
        });
        RemoveUser.setOnAction(e -> {
            ShowRemoveUserScene(user, users, readers, books, librarians);
        });
        BlockUser.setOnAction(e -> {
            showBlockUserScene(user, users, readers, books, librarians);
        });
        AddUser.setOnAction(e -> {
            ShowAddUser(user, users, readers, books, librarians);
        });

        logoutButton.setOnAction(e -> {
            // Show login scene
            stage.setScene(loginScene);
            stage.show();
        });

        // Set librarian dashboard as the root of the scene
        Scene librarianScene = new Scene(librarianBox, 400, 400);

        stage.setScene(librarianScene);
    }

    // This method shows the reader dashboard
    private void showReaderDashboard(User user, ArrayList<User> users, ArrayList<Reader> readers,
                                     ArrayList<Book> books, ArrayList<Librarian> librarians)
    {
        // Initialize UI components for reader dashboard
        Label NewLabel = new Label("    Library Policy: Books are only rented for 5 days");
        Label NewLabel1 = new Label("If any fail to return book in time will get blocked");
        Label readerLabel = new Label("Welcome " + user.getFirstName() + " " + user.getLastName());
        Button searchButton = new Button("Search Book");
        Button RentBook = new Button("Rent a Book");
        Button OrderList = new Button("View OrderList");
        Button logoutButton = new Button("Logout");
        Label lbl = new Label();

        // Set UI component properties
        NewLabel.setFont(new Font("Arial", 12));
        NewLabel1.setFont(new Font("Arial", 12));
        readerLabel.setFont(new Font("Arial", 24));
        lbl.setFont(new Font("Arial" , 14));
        searchButton.setPrefWidth(150);

        OrderList.setPrefWidth(150);
        RentBook.setPrefWidth(150);
        logoutButton.setPrefWidth(150);

        // Create layout for reader dashboard
        VBox readerBox = new VBox(20,NewLabel , NewLabel1, readerLabel, searchButton,
                RentBook, OrderList, logoutButton ,lbl);
        readerBox.setAlignment(Pos.CENTER);
        searchButton.setOnAction(e -> {

            if(books.size() == 0)
            {
                lbl.setText("No Books exists");
            }
            else
            ShowSearchBook(user, users, readers, books, librarians);
        });

        RentBook.setOnAction(e ->
        {
            if(books.size() == 0)
            {
                lbl.setText("No Books exists");
            }
            else
            ShowRentBookScene(user, users, readers, books, librarians);
        });
        OrderList.setOnAction(e -> {
            Reader r1 = (Reader)user;
            if(r1.getOrderList().size() == 0)
            {

                lbl.setText("OrderList is Empty!");
            }
            else
            ShowOrderList(user, users, readers, books, librarians);
        });

        logoutButton.setOnAction(e -> {
            // Show login scene
            stage.setScene(loginScene);
            stage.show();
        });

        // Set reader dashboard as the root of the scene
        Scene readerScene = new Scene(readerBox, 400, 400);
        stage.setScene(readerScene);
    }

    private void ShowOrderList( User user, ArrayList<User> users, ArrayList<Reader> readers,
                                ArrayList<Book> books, ArrayList<Librarian> librarians)
    {
        Reader reader = (Reader)user;
        Book books2 = reader.getOrderList().get(iteration);
       int x = reader.getOrderList().size();

        Label addBookLabel = new Label("View OrderList");
        addBookLabel.setFont(new Font("Arial", 24));

                Label titleLabel = new Label("Title:");
                Label authorLabel = new Label("Author:");
                Label isbnLabel = new Label("Serial Number:");
                Label titleField = new Label(books2.getTitle());
                Label authorField = new Label(books2.getAuthor());
                Label isbnField = new Label(books2.getSerial());
                Label txt = new Label();
                // Set UI component properties

                titleLabel.setFont(new Font("Arial", 16));
                authorLabel.setFont(new Font("Arial", 16));
                isbnLabel.setFont(new Font("Arial", 16));
                titleField.setFont(new Font("Arial", 16));
                authorField.setFont(new Font("Arial", 16));
                isbnField.setFont(new Font("Arial", 16));


            Button backButton = new Button("Back");
            Button Next = new Button("Next");
            Button MainMenu = new Button("Menu");
            Button Return = new Button("Return");
            backButton.setPrefWidth(150);
            MainMenu.setPrefWidth(150);
            Next.setPrefWidth(150);
            Return.setPrefWidth(150);
        GridPane addBookForm = new GridPane();
            addBookForm.setHgap(10);
            addBookForm.setVgap(10);
            addBookForm.add(titleLabel, 0, 1);
            addBookForm.add(titleField, 1, 1);
            addBookForm.add(authorLabel, 0, 2);
            addBookForm.add(authorField, 1, 2);
            addBookForm.add(isbnLabel, 0, 3);
            addBookForm.add(isbnField, 1, 3);
            addBookForm.add(Return, 0, 4);
            addBookForm.setPadding(new Insets(20));
            addBookForm.setAlignment(Pos.CENTER);
            addBookForm.add(addBookLabel, 0, 0);
        addBookForm.add(txt, 0, 6);
            if(iteration < x-1)
                addBookForm.add(Next , 0 , 5);

                addBookForm.add(MainMenu , 1 , 6);

        if(iteration > 0)
            addBookForm.add(backButton, 1, 5);


            // Set event handlers for buttons

            Next.setOnAction(e -> {
                iteration++;
                ShowOrderList(user, users, readers, books, librarians);
            });
            MainMenu.setOnAction(e -> {
                // Show librarian dashboard
                iteration = 0;
                showReaderDashboard(user, users, readers, books, librarians);
            });
            backButton.setOnAction(e -> {

                iteration--;
                ShowOrderList(user, users, readers, books, librarians);
            });
            Return.setOnAction(e -> {
                reader.getOrderList().remove(books2);
                books2.setIs_available(true);
                txt.setText("Returned! Return to Menu!");
            });

            // Set add book scene as the root of the scene
            Scene addBookScene = new Scene(addBookForm, 400, 400);
            stage.setScene(addBookScene);
        }



    private void ShowRemoveUserScene(User user , ArrayList<User> users , ArrayList<Reader> readers
            , ArrayList<Book> books , ArrayList<Librarian> librarians)
    {
        Label RemoveUserLabel = new Label("Remove User");

        Label ID = new Label("ID:");
        TextField IDAns= new TextField();

        Button Remove = new Button("Remove");
        Button BackButton = new Button("back");
        Label messageLabel = new Label();

        RemoveUserLabel.setFont(new Font("Arial", 20));
        ID.setFont(new Font("Arial", 14));

        IDAns.setPrefWidth(200);
        BackButton.setPrefWidth(150);
        Remove.setPrefWidth(150);

        GridPane Show = new GridPane();
        Show.setHgap(10);
        Show.setVgap(10);
        Show.setPadding(new Insets(20));
        Show.setAlignment(Pos.CENTER);
        Show.add(RemoveUserLabel , 0 ,1);
        Show.add(ID, 0, 2);
        Show.add(IDAns, 1, 2);
        Show.add(Remove,0,3);
        Show.add(BackButton,1,3);
        Show.add(messageLabel, 1, 4);

        BackButton.setOnAction(e -> {
            // Show librarian dashboard
            showLibrarianDashboard(user, users ,readers , books , librarians);
        });
        Remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String id = IDAns.getText();
                Iterator<User> iterator = users.iterator();
                boolean found = false;
                while (iterator.hasNext()) {
                    User user = iterator.next();
                    if (user.getId().equals(id) && user.getType().equalsIgnoreCase("Reader")) {
                        ArrayList<Book> books5 = ((Reader)user).getOrderList();
                        Iterator<Book> iterator1 = books5.iterator();
                        while (iterator1.hasNext()) {
                            Book book2 = iterator1.next();
                            book2.setIs_available(true);
                            books5.remove(book2);
                        }
                        users.remove(user);
                        readers.remove(user);
                        messageLabel.setText("Removed Successfully");
                        found = true;
                    }
                }
                if(!found)
                {messageLabel.setText("User not found / Cannot be removed");}
            }
        });
        Scene AddUserScene = new Scene(Show, 400, 400);
        stage.setScene(AddUserScene);

    }
    private void ShowRentBookScene(User user , ArrayList<User> users , ArrayList<Reader> readers
            , ArrayList<Book> books , ArrayList<Librarian> librarians)
    {
        Label SearchBookLabel = new Label("Rent a Book");
        Label TextFieldLabel = new Label("Serial:");
        TextField Serial = new TextField();
        Button SearchButton = new Button("Rent");
        Button backButton = new Button("Back");
        Label messageLabel = new Label();

        SearchBookLabel.setFont(new Font("Arial", 20));
        TextFieldLabel.setFont(new Font("Arial", 16));
        Serial.setPrefWidth(200);
        backButton.setPrefWidth(150);
        SearchButton.setPrefWidth(150);

        GridPane SearchBook = new GridPane();
        SearchBook.setHgap(10);
        SearchBook.setVgap(10);
        SearchBook.setPadding(new Insets(20));
        SearchBook.setAlignment(Pos.CENTER);
        SearchBook.add(SearchBookLabel, 1, 0);
        SearchBook.add(TextFieldLabel, 0, 1);
        SearchBook.add(Serial, 1, 1);
        SearchBook.add(SearchButton, 1, 4);
        SearchBook.add(backButton, 1, 5);
        SearchBook.add(messageLabel, 1, 3);

        backButton.setOnAction(e ->  {
            // Show librarian dashboard
            showReaderDashboard(user, users ,readers , books , librarians);
        });

        Scene SearchBookScene = new Scene(SearchBook, 400, 400);
        stage.setScene(SearchBookScene);

        SearchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String SerialAns = Serial.getText();
                Book book = library.SearchBook(SerialAns , books);
                if (book != null)
                {
                    if(book.Is_available() == true && user.isBlocked() == false)
                    {
                        book.setIs_available(false);
                        book.setRentdate(new Date());
                        Reader r1 = (Reader)user;
                        r1.addToOrderList(book);
                        messageLabel.setText("Book Rented Successfully");
                    }
                    else if(book.Is_available() == false){
                        messageLabel.setText("Book already Rented");
                    }
                    else if(user.isBlocked() == true)
                    {
                        messageLabel.setText("Blocked");
                    }
                }
                else {
                    messageLabel.setText("Book not found");
                }
            }
        });

    }

    private void ShowAddUser(User user , ArrayList<User> users , ArrayList<Reader> readers
            , ArrayList<Book> books , ArrayList<Librarian> librarians)
    {
        Label ShowAddUserLabel = new Label("Add User");
        Label FirstName = new Label("First Name:");
        TextField FirstNameAns = new TextField();
        FirstNameAns.setPromptText("ex: John");
        Label LastName = new Label("Last Name");
        TextField LastNameAns = new TextField();
        LastNameAns.setPromptText("ex: Wick");
        Label ID = new Label("ID: ");
        TextField IDAns = new TextField();
        IDAns.setPromptText("ex: 123");
        Label Address = new Label("Address: ");
        TextField AddressAns = new TextField();
        AddressAns.setPromptText("ex: Nasr City,Egypt");
        Label Type = new Label("Type: ");
        TextField TypeAns = new TextField();
        TypeAns.setPromptText("Librarian/Reader");
        Label Cell = new Label("CellPhone: ");
        TextField CellAns = new TextField();
        CellAns.setPromptText("ex: +201555599682");
        Label Email = new Label("Email: ");
        TextField EmailAns = new TextField();
        EmailAns.setPromptText("ex: johnwick@gmail.com");
        Label Password = new Label("Password: ");
        TextField PasswordAns = new TextField();
        Button Add = new Button("Add User");
        Button BackButton = new Button("back");
        Label messageLabel = new Label();
        ShowAddUserLabel.setFont(new Font("Arial", 20));
        FirstName.setFont(new Font("Arial", 14));
        LastName.setFont(new Font("Arial", 14));
        ID.setFont(new Font("Arial", 14));
        Address.setFont(new Font("Arial", 14));
        Type.setFont(new Font("Arial", 14));
        Cell.setFont(new Font("Arial", 14));
        Email.setFont(new Font("Arial", 14));
        Password.setFont(new Font("Arial", 14));
        FirstNameAns.setPrefWidth(200);
        LastNameAns.setPrefWidth(200);
        IDAns.setPrefWidth(200);
        AddressAns.setPrefWidth(200);
        TypeAns.setPrefWidth(200);
        CellAns.setPrefWidth(200);
        EmailAns.setPrefWidth(200);
        PasswordAns.setPrefWidth(200);

        BackButton.setPrefWidth(150);
        Add.setPrefWidth(150);

        GridPane Show = new GridPane();
        Show.setHgap(10);
        Show.setVgap(10);
        Show.setPadding(new Insets(20));
        Show.setAlignment(Pos.CENTER);
        Show.add(ShowAddUserLabel , 0 ,1);
        Show.add(FirstName, 0, 2);
        Show.add(FirstNameAns, 1, 2);
        Show.add(LastName,0,3);
        Show.add(LastNameAns,1,3);
        Show.add(ID, 0, 4);
        Show.add(IDAns, 1, 4);
        Show.add(Address, 0, 5);
        Show.add(AddressAns, 1, 5);
        Show.add(Type , 0 , 6);
        Show.add(TypeAns , 1, 6);
        Show.add(Cell , 0, 7);
        Show.add(CellAns, 1, 7);
        Show.add(Email, 0, 8);
        Show.add(EmailAns, 1, 8);
        Show.add(Password , 0 , 9);
        Show.add(PasswordAns , 1 , 9);



        Show.add(Add , 0 , 10);
        Show.add(BackButton,1,10);
        Show.add(messageLabel, 1, 11);
        BackButton.setOnAction(e -> {
            // Show librarian dashboard
            showLibrarianDashboard(user, users ,readers , books , librarians);
        });
        Add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String firstName = FirstNameAns.getText();
                String lastName = LastNameAns.getText();
                String Address = AddressAns.getText();
                String Type = TypeAns.getText();
                String id = IDAns.getText();
                String Cell = CellAns.getText();
                String Email = EmailAns.getText();
                boolean mail = library.EmailCheck(Email);
                String password = PasswordAns.getText();
                boolean blocked = false;
                Boolean IDChecker = library.CompareDataFields(id , users);
                Boolean PasswordChecker = library.CompareDataFieldsPassword(password , users);

                if (mail == false)
                {
                    messageLabel.setText("Incorrect mail");
                }
                else if(PasswordChecker == true)
                {
                    messageLabel.setText("Please Choose Another Password");
                }
                else if(IDChecker == true) {
                    messageLabel.setText("ID already exists!");
                }
                else
                {
                    if (Type.equalsIgnoreCase("Reader"))
                    {
                        Type = "Reader";
                        Reader R1 = new Reader(id, password, firstName, lastName, Address, Cell, Email, blocked, Type);
                        users.add(R1);
                        readers.add(R1);
                        messageLabel.setText("Added Successfully");

                    } else if (Type.equalsIgnoreCase("Librarian"))
                    {
                        Type = "Librarian";
                        Librarian L1 = new Librarian(id, password, firstName, lastName, Address, Cell, Email, blocked, Type);
                        users.add(L1);
                        librarians.add(L1);
                        messageLabel.setText("Added Successfully");

                    } else
                        messageLabel.setText("Invalid Type");
                }
            }
        });

        Scene AddUserScene = new Scene(Show, 400, 400);
        stage.setScene(AddUserScene);



    }
    private void showAddBookScene(User user , ArrayList<User> users , ArrayList<Reader> readers
            , ArrayList<Book> books , ArrayList<Librarian> librarians)
    {
        // Initialize UI components for add book scene
        Label addBookLabel = new Label("Add Book");
        Label titleLabel = new Label("Title:");
        Label authorLabel = new Label("Author:");
        Label isbnLabel = new Label("Serial Number:");
        Label messageLabel = new Label();
        TextField titleField = new TextField();
        titleField.setPromptText("ex: Harry Potter");
        TextField authorField = new TextField();
        authorField.setPromptText("ex: JK Rowling");
        TextField isbnField = new TextField();
        isbnField.setPromptText("ex: 123456789");
        Button addButton = new Button("Add");
        Button backButton = new Button("Back");

        // Set UI component properties
        addBookLabel.setFont(new Font("Arial", 24));
        titleLabel.setFont(new Font("Arial", 16));
        authorLabel.setFont(new Font("Arial", 16));
        isbnLabel.setFont(new Font("Arial", 16));
        titleField.setPrefWidth(200);
        authorField.setPrefWidth(200);
        isbnField.setPrefWidth(200);
        addButton.setPrefWidth(150);
        backButton.setPrefWidth(150);

        // Create layout for add book scene
        GridPane addBookForm = new GridPane();
        addBookForm.setHgap(10);
        addBookForm.setVgap(10);
        addBookForm.setPadding(new Insets(20));
        addBookForm.setAlignment(Pos.CENTER);
        addBookForm.add(addBookLabel, 0, 0);
        addBookForm.add(titleLabel, 0, 1);
        addBookForm.add(titleField, 1, 1);
        addBookForm.add(authorLabel, 0, 2);
        addBookForm.add(authorField, 1, 2);
        addBookForm.add(isbnLabel, 0, 3);
        addBookForm.add(isbnField, 1, 3);
        addBookForm.add(addButton, 1, 4);
        addBookForm.add(backButton, 1, 5);
        addBookForm.add(messageLabel , 0 ,6);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String Title = titleField.getText();
                String Author = authorField.getText();
                String isbn = isbnField.getText();
                Book cmp = library.SearchBook(isbn , books);
                Boolean bool = true ? cmp== null: cmp != null;
                if(bool == true)
                {
                    books.add(new Book(Title, Author, isbn));
                    messageLabel.setText("Book Added Successfully");
                }
                else
                    messageLabel.setText("Choose another Serial Number");
            }
        });

        backButton.setOnAction(e -> {
            // Show librarian dashboard
            showLibrarianDashboard(user, users ,readers , books , librarians);
        });

        // Set add book scene as the root of the scene
        Scene addBookScene = new Scene(addBookForm, 400, 400);
        stage.setScene(addBookScene);
    }


    private void showBlockUserScene(User user , ArrayList<User> users , ArrayList<Reader> readers ,
                                    ArrayList<Book> books , ArrayList<Librarian> librarians)
    {
        Label SearchUserLabel = new Label("Block Users");
        Label TextFieldLabel = new Label("ID:");
        TextField ID = new TextField();
        Button SearchButton = new Button("Search");
        Button backButton = new Button("Back");
        Label messageLabel = new Label();
        Label messageLabe = new Label();

        SearchUserLabel.setFont(new Font("Arial", 20));
        TextFieldLabel.setFont(new Font("Arial", 16));
        messageLabel.setFont(new Font("Arial", 16));
        messageLabe.setFont(new Font("Arial", 16));

        ID.setPrefWidth(200);
        backButton.setPrefWidth(150);
        SearchButton.setPrefWidth(150);

        GridPane SearchUser = new GridPane();
        SearchUser.setHgap(10);
        SearchUser.setVgap(10);
        SearchUser.setPadding(new Insets(20));
        SearchUser.setAlignment(Pos.CENTER);
        SearchUser.add(SearchUserLabel, 1, 0);
        SearchUser.add(TextFieldLabel, 0, 1);
        SearchUser.add(ID, 1, 1);
        SearchUser.add(SearchButton, 0, 5);
        SearchUser.add(backButton, 1, 5);
        SearchUser.add(messageLabel, 0, 3);
        SearchUser.add(messageLabe,1,4);

        backButton.setOnAction(e ->  {
            // Show librarian dashboard
            showLibrarianDashboard(user, users ,readers , books , librarians);
        });

        Scene SearchUserScene = new Scene(SearchUser, 400, 400);
        stage.setScene(SearchUserScene);

        SearchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                String id = ID.getText();
                User user = library.SearchU(id, users);
                if(user != null)
                {
                    if(user.getType().equalsIgnoreCase("librarian"))
                    {
                        messageLabel.setText("User is a Librarian");
                        messageLabe.setText("");
                    }
                    else
                    {
                        {
                            Reader r1 = (Reader)user;
                            Book Block = library.Block(r1);
                            if (r1.isBlocked() == true) {
                                messageLabel.setText("User already blocked");
                                messageLabe.setText("");
                            } else {

                                if (Block != null) {
                                    user.setBlocked(true);
                                    messageLabel.setText("User is blocked");
                                    messageLabe.setText("");
                                    messageLabe.setText(Block.getRentdate().toString());
                                    ArrayList<Book> books5 = r1.getOrderList();
                                    Iterator<Book> iterator = books5.iterator();
                                    while (iterator.hasNext()) {
                                        Book book2 = iterator.next();
                                        book2.setIs_available(true);
                                        books5.remove(book2);
                                    }
                                } else {
                                    messageLabel.setText("OrderList is empty\n" +
                                            "/no late books found");
                                    messageLabe.setText("");


                                }

                            }
                        }
                    }

                }
                else
                {
                    messageLabel.setText("User doesn't exist");
                    messageLabe.setText("");
                }

            }
        });
    }
    private void showUser(User user , User SearchingUser, ArrayList<User> users , ArrayList<Reader> readers , ArrayList<Book> books , ArrayList<Librarian> librarians)
    {
        Label FullName = new Label("Full Name:");
        Label FullNameAns = new Label(user.getFirstName() + " "+ user.getLastName());
        Label ID = new Label("ID: ");
        Label IDAns = new Label(user.getId());
        Label Address = new Label("Address: ");
        Label AddressAns = new Label(user.getAddress());
        Label Type = new Label("Type: ");
        Label TypeAns = new Label(user.getType());
        Label Cell = new Label("CellPhone: ");
        Label CellAns = new Label(user.getCellPhone());
        Label Email = new Label("Email: ");
        Label EmailAns = new Label(user.getEmail());
        Label isBlocked = new Label("Is this user blocked? ");
        Label isBlockedAns = new Label(user.isBlockedStr());
        Button BackButton = new Button("back");

        FullName.setFont(new Font("Arial", 14));
        FullNameAns.setFont(new Font("Arial", 14));
        ID.setFont(new Font("Arial", 14));
        IDAns.setFont(new Font("Arial", 14));
        Address.setFont(new Font("Arial", 14));
        AddressAns.setFont(new Font("Arial", 14));
        Type.setFont(new Font("Arial", 14));
        TypeAns.setFont(new Font("Arial", 14));
        Cell.setFont(new Font("Arial", 14));
        CellAns.setFont(new Font("Arial", 14));
        Email.setFont(new Font("Arial", 14));
        EmailAns.setFont(new Font("Arial", 14));
        isBlocked.setFont(new Font("Arial", 14));
        isBlockedAns.setFont(new Font("Arial", 14));

        BackButton.setPrefWidth(150);

        GridPane Show = new GridPane();
        Show.setHgap(10);
        Show.setVgap(10);
        Show.setPadding(new Insets(20));
        Show.setAlignment(Pos.CENTER);
        Show.add(FullName, 0, 1);
        Show.add(FullNameAns, 1, 1);
        Show.add(ID, 0, 2);
        Show.add(IDAns, 1, 2);
        Show.add(Address, 0, 3);
        Show.add(AddressAns, 1, 3);
        Show.add(Type , 0 , 4);
        Show.add(TypeAns , 1, 4);
        Show.add(Cell , 0, 5);
        Show.add(CellAns, 1, 5);
        Show.add(Email, 0, 6);
        Show.add(EmailAns, 1, 6);
        Show.add(isBlocked, 0, 7);
        Show.add(isBlockedAns, 1, 7);
        Show.add(BackButton,1,8);

        BackButton.setOnAction(e -> {
            // Show librarian dashboard
            showSearchUserScene(SearchingUser, users ,readers , books , librarians);
        });

        Scene ShowScene = new Scene(Show, 400, 400);
        stage.setScene(ShowScene);
    }


    private void showBook(Book book , User user, ArrayList<User> users , ArrayList<Reader> readers , ArrayList<Book> books , ArrayList<Librarian> librarians)
    {
        Label BookTitle = new Label("Book Title: ");
        Label BookTitleAns = new Label(book.getTitle());
        Label Author = new Label("Author Name: ");
        Label AuthorAns = new Label(book.getAuthor());
        Label Serial = new Label("Serial Number ");
        Label SerialAns = new Label(book.getSerial());

        Button BackButton = new Button("back");

        BookTitle.setFont(new Font("Arial", 14));
        BookTitleAns.setFont(new Font("Arial", 14));
        Author.setFont(new Font("Arial", 14));
        AuthorAns.setFont(new Font("Arial", 14));
        Serial.setFont(new Font("Arial", 14));
        SerialAns.setFont(new Font("Arial", 14));


        BackButton.setPrefWidth(150);

        GridPane Show = new GridPane();
        Show.setHgap(10);
        Show.setVgap(10);
        Show.setPadding(new Insets(20));
        Show.setAlignment(Pos.CENTER);
        Show.add(BookTitle, 0, 1);
        Show.add(BookTitleAns, 1, 1);
        Show.add(Author, 0, 2);
        Show.add(AuthorAns, 1, 2);
        Show.add(Serial, 0, 3);
        Show.add(SerialAns, 1, 3);
        Show.add(BackButton,1,4);

        BackButton.setOnAction(e -> {
            // Show librarian dashboard
            ShowSearchBook(user, users ,readers , books , librarians);
        });

        Scene ShowScene = new Scene(Show, 400, 400);
        stage.setScene(ShowScene);
    }

    private void ShowSearchBook(User user , ArrayList<User> users , ArrayList<Reader> readers , ArrayList<Book> books , ArrayList<Librarian> librarians)
    {
        Book b1 = books.get(0);
        Label SearchBookLabel = new Label("Search Book");
        Label TextFieldLabel = new Label("Serial:");
        TextField Serial = new TextField();
        Serial.setPromptText("ex: Harry Potter");
        Button SearchButton = new Button("Search");
        Button backButton = new Button("Back");
        Button Scroll = new Button("Search All");
        Label messageLabel = new Label();

        SearchBookLabel.setFont(new Font("Arial", 20));
        TextFieldLabel.setFont(new Font("Arial", 16));
        Serial.setPrefWidth(200);
        backButton.setPrefWidth(150);
        SearchButton.setPrefWidth(150);
        Scroll.setPrefWidth(150);

        GridPane SearchBook = new GridPane();
        SearchBook.setHgap(10);
        SearchBook.setVgap(10);
        SearchBook.setPadding(new Insets(20));
        SearchBook.setAlignment(Pos.CENTER);
        SearchBook.add(SearchBookLabel, 1, 0);
        SearchBook.add(TextFieldLabel, 0, 1);
        SearchBook.add(Serial, 1, 1);
        SearchBook.add(SearchButton, 1, 4);
        SearchBook.add(backButton, 1, 5);
        SearchBook.add(messageLabel, 1, 3);
        SearchBook.add(Scroll , 0 , 4);

        backButton.setOnAction(e ->  {
            // Show librarian dashboard
            if(user.getType().equals("Librarian"))
            showLibrarianDashboard(user, users ,readers , books , librarians);
            else
                showReaderDashboard(user, users ,readers , books , librarians);

        });

        Scene SearchBookScene = new Scene(SearchBook, 400, 400);
        stage.setScene(SearchBookScene);

        SearchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String SerialAns = Serial.getText();
                Book book = library.SearchBook(SerialAns , books);
                if (book != null)
                {
                    showBook(book, user, users ,readers , books , librarians);
                }
                else {
                    messageLabel.setText("Book not found");
                }
            }
        });

        Scroll.setOnAction(e ->{

                showBookScroll(user,b1,b1, users ,readers , books , librarians);
        });


    }
    private void showBookScroll(User user,Book book,Book Change, ArrayList<User> users , ArrayList<Reader> readers , ArrayList<Book> books , ArrayList<Librarian> librarians)
    {
        Book book1 = books.get(iteration);
        int x = books.size();
        Label FullName = new Label("Book Name:");
        Label FullNameAns = new Label(book1.getTitle());
        Label ID = new Label("Author ");
        Label IDAns = new Label(book1.getAuthor());
        Label Address = new Label("Serial ");
        Label AddressAns = new Label(book1.getSerial());

        Button BackButton = new Button("back");
        Button MainMenu = new Button("Menu");
        Button Next =  new Button("Next");

        FullName.setFont(new Font("Arial", 14));
        FullNameAns.setFont(new Font("Arial", 14));
        ID.setFont(new Font("Arial", 14));
        IDAns.setFont(new Font("Arial", 14));
        Address.setFont(new Font("Arial", 14));
        AddressAns.setFont(new Font("Arial", 14));


        BackButton.setPrefWidth(150);
        Next.setPrefWidth(150);
        MainMenu.setPrefWidth(150);

        GridPane Show = new GridPane();
        Show.setHgap(10);
        Show.setVgap(10);
        Show.setPadding(new Insets(20));
        Show.setAlignment(Pos.CENTER);
        Show.add(FullName, 0, 1);
        Show.add(FullNameAns, 1, 1);
        Show.add(ID, 0, 2);
        Show.add(IDAns, 1, 2);
        Show.add(Address, 0, 3);
        Show.add(AddressAns, 1, 3);

        if(iteration>0)
            Show.add(BackButton,1,4);
        if(iteration<x-1)
            Show.add(Next,0,4);

        Show.add(MainMenu,0,5);

        BackButton.setOnAction(e -> {
            // Show librarian dashboard
            iteration--;
            showBookScroll(user,book,book1, users ,readers , books , librarians);
        });
        Next.setOnAction(e -> {
            iteration++;
            showBookScroll(user,book , book1, users ,readers , books , librarians);
        });
        MainMenu.setOnAction(e -> {
            // Show librarian dashboard
            iteration = 0;
            ShowSearchBook(user, users, readers, books, librarians);
        });

        Scene ShowScene = new Scene(Show, 400, 400);
        stage.setScene(ShowScene);
    }
    private void showUserScroll(User user,User Change, ArrayList<User> users , ArrayList<Reader> readers
            , ArrayList<Book> books , ArrayList<Librarian> librarians)
    {
        User user1 = users.get(iteration);
        int x = users.size();
        Label FullName = new Label("Full Name:");
        Label FullNameAns = new Label(user1.getFirstName() + " "+ user1.getLastName());
        Label ID = new Label("ID: ");
        Label IDAns = new Label(user1.getId());
        Label Address = new Label("Address: ");
        Label AddressAns = new Label(user1.getAddress());
        Label Type = new Label("Type: ");
        Label TypeAns = new Label(user1.getType());
        Label Cell = new Label("CellPhone: ");
        Label CellAns = new Label(user.getCellPhone());
        Label Email = new Label("Email: ");
        Label EmailAns = new Label(user1.getEmail());
        Label isBlocked = new Label("Is this user blocked? ");
        Label isBlockedAns = new Label(user1.isBlockedStr());
        Button BackButton = new Button("back");
        Button MainMenu = new Button("Menu");
        Button Next =  new Button("Next");

        FullName.setFont(new Font("Arial", 14));
        FullNameAns.setFont(new Font("Arial", 14));
        ID.setFont(new Font("Arial", 14));
        IDAns.setFont(new Font("Arial", 14));
        Address.setFont(new Font("Arial", 14));
        AddressAns.setFont(new Font("Arial", 14));
        Type.setFont(new Font("Arial", 14));
        TypeAns.setFont(new Font("Arial", 14));
        Cell.setFont(new Font("Arial", 14));
        CellAns.setFont(new Font("Arial", 14));
        Email.setFont(new Font("Arial", 14));
        EmailAns.setFont(new Font("Arial", 14));
        isBlocked.setFont(new Font("Arial", 14));
        isBlockedAns.setFont(new Font("Arial", 14));

        BackButton.setPrefWidth(150);
        Next.setPrefWidth(150);
        MainMenu.setPrefWidth(150);

        GridPane Show = new GridPane();
        Show.setHgap(10);
        Show.setVgap(10);
        Show.setPadding(new Insets(20));
        Show.setAlignment(Pos.CENTER);
        Show.add(FullName, 0, 1);
        Show.add(FullNameAns, 1, 1);
        Show.add(ID, 0, 2);
        Show.add(IDAns, 1, 2);
        Show.add(Address, 0, 3);
        Show.add(AddressAns, 1, 3);
        Show.add(Type , 0 , 4);
        Show.add(TypeAns , 1, 4);
        Show.add(Cell , 0, 5);
        Show.add(CellAns, 1, 5);
        Show.add(Email, 0, 6);
        Show.add(EmailAns, 1, 6);
        Show.add(isBlocked, 0, 7);
        Show.add(isBlockedAns, 1, 7);
        if(iteration>0)
            Show.add(BackButton,1,8);
        if(iteration<x-1)
            Show.add(Next,0,8);

        Show.add(MainMenu,0,9);

        BackButton.setOnAction(e -> {
            // Show librarian dashboard
            iteration--;
            showUserScroll(user,user1, users ,readers , books , librarians);
        });
        Next.setOnAction(e -> {
            iteration++;
            showUserScroll(user , user1, users ,readers , books , librarians);
        });
        MainMenu.setOnAction(e -> {
            // Show librarian dashboard
            iteration = 0;
            showSearchUserScene(user, users, readers, books, librarians);
        });

        Scene ShowScene = new Scene(Show, 400, 400);
        stage.setScene(ShowScene);
    }
    private void showSearchUserScene(User user , ArrayList<User> users , ArrayList<Reader> readers
            , ArrayList<Book> books , ArrayList<Librarian> librarians)
    {

        Label SearchUserLabel = new Label("Search for User");
        Label TextFieldLabel = new Label("ID:");
        TextField ID = new TextField();
        Button SearchButton = new Button("Search");
        Button Scroll = new Button("Search All");

        Button backButton = new Button("Back");

        Label messageLabel = new Label();

        SearchUserLabel.setFont(new Font("Arial", 20));
        TextFieldLabel.setFont(new Font("Arial", 16));



        ID.setPrefWidth(200);
        backButton.setPrefWidth(150);
        SearchButton.setPrefWidth(150);
        Scroll.setPrefWidth(150);

        GridPane SearchUser = new GridPane();
        SearchUser.setHgap(10);
        SearchUser.setVgap(10);
        SearchUser.setPadding(new Insets(20));
        SearchUser.setAlignment(Pos.CENTER);
        SearchUser.add(SearchUserLabel, 1, 0);
        SearchUser.add(TextFieldLabel, 0, 1);
        SearchUser.add(ID, 1, 1);
        SearchUser.add(SearchButton, 1, 4);
        SearchUser.add(Scroll,0,4);
        SearchUser.add(backButton, 1, 5);
        SearchUser.add(messageLabel, 1, 3);

        backButton.setOnAction(e ->  {
            // Show librarian dashboard
            showLibrarianDashboard(user, users ,readers , books , librarians);
        });
        Scroll.setOnAction(e ->{
            showUserScroll(user,user, users ,readers , books , librarians);
        });

        Scene SearchUserScene = new Scene(SearchUser, 400, 400);
        stage.setScene(SearchUserScene);
        SearchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String id = ID.getText();
                User searchID = library.SearchU(id, users);
                if (searchID != null)
                {
                    showUser(searchID, user, users ,readers , books , librarians);
                }
                else {
                    messageLabel.setText("User not found");
                }
            }
        });
    }



    private void showRemoveBookScene(User user , ArrayList<User> users , ArrayList<Reader> readers ,
                                     ArrayList<Book> books , ArrayList<Librarian> librarians)
    {
    // Initialize UI components for remove book scene
    Label removeBookLabel = new Label("Remove Book");
    Label titleLabel = new Label("Title:");
    Label messageLabel = new Label();


    Label isbnLabel = new Label("Serial:");
    TextField titleField = new TextField();
    TextField isbnField = new TextField();

    titleField.setPromptText("ex: Hary Potter");
    isbnField.setPromptText("ex: 123456789");

    Button removeButton = new Button("Remove");
    Button backButton = new Button("Back");
    
    // Set UI component properties
    removeBookLabel.setFont(new Font("Arial", 24));
    titleLabel.setFont(new Font("Arial", 16));

    isbnLabel.setFont(new Font("Arial", 16));
    titleField.setPrefWidth(200);

    isbnField.setPrefWidth(200);
    removeButton.setPrefWidth(150);
    backButton.setPrefWidth(150);
    
    // Create layout for remove book scene
    GridPane removeBookForm = new GridPane();
    removeBookForm.setHgap(10);
    removeBookForm.setVgap(10);
    removeBookForm.setPadding(new Insets(20));
    removeBookForm.setAlignment(Pos.CENTER);
    removeBookForm.add(removeBookLabel, 0, 0);
    removeBookForm.add(titleLabel, 0, 1);
    removeBookForm.add(titleField, 1, 1);

    removeBookForm.add(isbnLabel, 0, 2);
    removeBookForm.add(isbnField, 1, 2);
    removeBookForm.add(removeButton, 0, 3);
    removeBookForm.add(backButton, 1, 3);
    removeBookForm.add(messageLabel,0 , 4);
    
    // Set event handlers for buttons
    removeButton.setOnAction(e -> {
        // Remove book from database
        String title = titleField.getText();
        String isbn = isbnField.getText();

        Book Exist = library.BookRemoval(title , isbn , books);
        if(Exist != null)
        {
            books.remove(Exist);
            messageLabel.setText("Book Removed Successfully");
        }
        else
            messageLabel.setText("Book not found");



    });
    
    backButton.setOnAction(e ->  {
        // Show librarian dashboard
        showLibrarianDashboard(user, users ,readers , books , librarians);
    });
    
    // Set remove book scene as the root of the scene
    Scene removeBookScene = new Scene(removeBookForm, 400, 400);
    stage.setScene(removeBookScene);
}



public static void main(String[] args) {
    launch(args);
}
}


