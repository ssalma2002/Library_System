
abstract public class User {
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String type;
    private String cellPhone;
    private String email;
    private boolean isBlocked;
    private static int count=0;



    public User() {
    }
    public User(String id, String password,  String firstName, String lastName, String address,
                String cellPhone, String email, boolean isBlocked, String type) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.cellPhone = cellPhone;
        this.email = email;
        this.isBlocked = isBlocked;
        this.type = type;
        count++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPassword() {
        return password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone ){
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private static int getCount() {
        return count;
    }




    @Override
    public String toString(){
        return "id= " + id + " first name= " + firstName + " last name= " + lastName + " adress= " + address + " cell phone= " + cellPhone + " email= " + email + " is blocked: " + isBlocked ;
    }


public String isBlockedStr()
{
    if(isBlocked == true)
        return "blocked";
    else
        return"not blocked";

}
   
} 

