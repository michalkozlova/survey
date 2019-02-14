package michal.edu.first.User;

import michal.edu.first.Questionnaire.Java.FullQuiz;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private Boolean hasStore = false;

    public User(){}

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String firstName, String lastName, Boolean hasStore) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasStore = hasStore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getHasStore() {
        return hasStore;
    }

    public void setHasStore(Boolean hasStore) {
        this.hasStore = hasStore;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hasStore=" + hasStore +
                '}';
    }
}
