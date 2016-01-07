package entities;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    public User(long id) {
        this.id = id;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
