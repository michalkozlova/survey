package michal.edu.first.User;

import michal.edu.first.Questionnaire.Java.FullQuiz;


//TODO:Delete key

public class User {
    private String id;
    private String key;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean hasStore = false;

    public User(){}

    public User(String key, String id, String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.key = key;
    }

    public User(String id, String key, String email, String firstName, String lastName, Boolean hasStore) {
        this.id = id;
        this.key = key;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasStore = hasStore;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hasStore=" + hasStore +
                '}';
    }
}
