package org.pursuit.psychic_app_hw_lucas_evelyn;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.pursuit.psychic_app_hw_lucas_evelyn.database.DatabaseHelper;
import org.pursuit.psychic_app_hw_lucas_evelyn.model.GuessModel;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChoiceFragment extends Fragment {

    public static final String FRAGMENT_STRING_KEY = "String_key";
    public static final String FRAGMENT_RESULT_KEY = "Result_key";
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private String resultString;
    private DatabaseHelper helper;
    private FragmentInterface fragmentInterface;


    public static ChoiceFragment newInstance(String string) {
        ChoiceFragment fragment = new ChoiceFragment();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_STRING_KEY, string);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInterface = (FragmentInterface) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choice, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.choice_textview);
        imageView1 = view.findViewById(R.id.imageview_one);
        imageView2 = view.findViewById(R.id.imageview_two);
        imageView3 = view.findViewById(R.id.imageview_three);
        imageView4 = view.findViewById(R.id.imageview_four);
        helper = new DatabaseHelper(view.getContext());

        if (getArguments() != null && getArguments().containsKey(FRAGMENT_STRING_KEY)) {
//            textView.setText(getArguments().getString(FRAGMENT_STRING_KEY));
            String choiceString = getArguments().getString(FRAGMENT_STRING_KEY);
            switch (choiceString) {
                case "Goths":
                    imageView1.setImageResource(R.drawable.aljourgensen);
                    imageView2.setImageResource(R.drawable.bauhaus);
                    imageView3.setImageResource(R.drawable.christian_death);
                    imageView4.setImageResource(R.drawable.gallowdance);
                    clickListeners();
                    break;
                case "Punks":
                    imageView1.setImageResource(R.drawable.skinnypuppy);
                    clickListeners();
                    break;
            }
        }
    }

    public void clickListeners() {
        Random random = new Random();
        final int computerChoice = random.nextInt(4) + 1;
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (computerChoice == 1) {
                    addCorrectToDatabase();
                } else {
                    addWrongToDatabase();
//                Toast.makeText(v.getContext(), "Computer: " + computerChoice + ". Your choice: 1", Toast.LENGTH_SHORT).show();
                }}
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (computerChoice == 2) {
                    addCorrectToDatabase();
                } else {
                    addWrongToDatabase();
//                    resultString = "Wrong! Your wrong average is blah";
//                    Log.d("shmoop", resultString);
//                    Toast.makeText(v.getContext(), resultString, Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (computerChoice == 3) {
                    addCorrectToDatabase();
                } else {
                    addWrongToDatabase();
//                    resultString = "Wrong! Your wrong average is blah";
//                    Log.d("shmoop", resultString);
//                    Toast.makeText(v.getContext(), resultString, Toast.LENGTH_SHORT).show();
                }

            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (computerChoice == 4) {
                    addCorrectToDatabase();
                } else {
                    addWrongToDatabase();
                }
            }
        });

//        for (int i = 0; i < helper.getGuessList().size(); i++) {
//            Log.d("database_row: ", helper.getGuessList().get(i).getCorrect_guess() + " " + helper.getGuessList().get(i).getWrong_guess());
//        }
    }

    private void addCorrectToDatabase() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getActivity());
        databaseHelper.addEntry(new GuessModel(1, 0));
        resultString = "You are correct and Average is: " + databaseHelper.guessCorrectPercentage();
        fragmentInterface.showResultFragment(resultString);
                for (int i = 0; i < helper.getGuessList().size(); i++) {
            Log.d("database_row: ", helper.getGuessList().get(i).getCorrect_guess() + " " + helper.getGuessList().get(i).getWrong_guess());
        }
    }

    private void addWrongToDatabase() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getActivity());
        databaseHelper.addEntry(new GuessModel(0, 1));

        resultString = "You are wrong";
        fragmentInterface.showResultFragment(resultString);
        for (int i = 0; i < helper.getGuessList().size(); i++) {
            Log.d("database_row: ", helper.getGuessList().get(i).getCorrect_guess() + " " + helper.getGuessList().get(i).getWrong_guess());
        }
        Log.d("Averages", " " + helper.guessCorrectPercentage());
    }
}
