package michal.edu.first.User;

import java.util.ArrayList;

import michal.edu.first.Login.User;

public interface UserListener {
    void onUserCallback(ArrayList<User> users);
}
