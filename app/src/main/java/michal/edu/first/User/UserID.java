package michal.edu.first.User;

import michal.edu.first.Store.Java.Store;

public class UserID {

    public static String userID;
    public static User thisUser;
    public static Store thisStore;

    public static void logOut(){
        userID = null;
        thisUser = null;
        thisStore = null;
    }
}
