package michal.edu.first.NewStore;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import michal.edu.first.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewAddressFragment extends Fragment {


    public NewAddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_address, container, false);
    }

}
