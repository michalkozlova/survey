package michal.edu.first.NewStore;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import michal.edu.first.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewStoreFragment extends Fragment {

    Spinner spinnerType, spBranchNum;
    EditText etStoreNameEng;
    Button btnNext;

    public NewStoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_store, container, false);
        spinnerType = v.findViewById(R.id.spType);
        spBranchNum = v.findViewById(R.id.spBranchNum);
        btnNext = v.findViewById(R.id.btnNext);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerType.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.amount, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spBranchNum.setAdapter(adapter1);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new NewAddressFragment()).commit();
            }
        });

        return v;
    }
}
