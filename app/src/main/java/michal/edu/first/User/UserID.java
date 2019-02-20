package michal.edu.first.User;

import java.util.ArrayList;

import michal.edu.first.Store.Java.Branch;
import michal.edu.first.Store.Java.Store;

public class UserID {

    public static String userID;
    public static User thisUser;
    public static Store thisStore;
    public static ArrayList<Branch> thisBranches;

    public static void logOut(){
        userID = null;
        thisUser = null;
        thisStore = null;
        thisBranches = null;
    }
}
