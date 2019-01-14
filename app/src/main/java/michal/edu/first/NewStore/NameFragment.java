package michal.edu.first.NewStore;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import michal.edu.first.NewStore.NewStoreFragment;
import michal.edu.first.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NameFragment extends Fragment {

    Button btnNext;

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_name, container, false);
        btnNext = v.findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new NewStoreFragment()).commit();
            }
        });

        return v;
    }

}
