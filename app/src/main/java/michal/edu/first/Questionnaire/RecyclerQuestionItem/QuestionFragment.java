package michal.edu.first.Questionnaire.RecyclerQuestionItem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import michal.edu.first.Questionnaire.Java.Question;
import michal.edu.first.Questionnaire.Java.Section;
import michal.edu.first.R;


/**
 * A simple {@link Fragment} subclass.
 */

public class QuestionFragment extends Fragment {


    private QuestionAdapter adapter;

    public void addQuestion(Question q){
        adapter.addQuestion(q);
    }
    public RecyclerView rvQuestions;

    public static QuestionFragment newInstance(Section section) {

        Bundle args = new Bundle();
        args.putSerializable("section", section);

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);

        rvQuestions = v.findViewById(R.id.rvQuestions);
        Section section = (Section) getArguments().getSerializable("section");
        adapter = new QuestionAdapter(section.getQuestions(), getActivity());
        rvQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvQuestions.setAdapter(adapter);

        return v;
    }
}
