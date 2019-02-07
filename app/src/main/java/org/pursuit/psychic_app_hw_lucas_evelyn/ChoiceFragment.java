package org.pursuit.psychic_app_hw_lucas_evelyn;

import android.content.Context;
import android.media.MediaPlayer;
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
    private View rootView;
    private MediaPlayer song;


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
        rootView = inflater.inflate(R.layout.fragment_choice, container, false);
        song = MediaPlayer.create(rootView.getContext(), R.raw.bauhaus);
        song.start();
        return rootView;
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


        if (getArguments() != null && getArguments().containsKey(FRAGMENT_STRING_KEY)) {
//            textView.setText(getArguments().getString(FRAGMENT_STRING_KEY));
            String choiceString = getArguments().getString(FRAGMENT_STRING_KEY);
            switch (choiceString) {
                case "Goth Singers":
                    imageView1.setImageResource(R.drawable.skinnypuppy);
                    imageView2.setImageResource(R.drawable.bauhaus);
                    imageView3.setImageResource(R.drawable.christian_death);
                    imageView4.setImageResource(R.drawable.gallowdance);
                    clickListeners();
                    break;
                case "Creepy Dolls":
                    imageView1.setImageResource(R.drawable.doll1);
                    imageView2.setImageResource(R.drawable.doll2);
                    imageView3.setImageResource(R.drawable.doll3);
                    imageView4.setImageResource(R.drawable.doll4);
                    clickListeners();
                    break;
                case "Haunted Houses":
                    imageView1.setImageResource(R.drawable.hh1);
                    imageView2.setImageResource(R.drawable.hh2);
                    imageView3.setImageResource(R.drawable.hh3);
                    imageView4.setImageResource(R.drawable.hh4);
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
                }
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (computerChoice == 2) {
                    addCorrectToDatabase();
                } else {
                    addWrongToDatabase();
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
    }

    private void addCorrectToDatabase() {
        helper = DatabaseHelper.getInstance(getActivity());
        helper.addEntry(new GuessModel(1, 0));
        fragmentInterface.showResultFragment(getString(R.string.guess_correct));

        for (int i = 0; i < helper.getGuessList().size(); i++) {
            Log.d("database_row: ", helper.getGuessList().get(i).getCorrectGuess() + " " + helper.getGuessList().get(i).getWrongGuess());
        }
    }

    private void addWrongToDatabase() {
        helper = DatabaseHelper.getInstance(getActivity());
        helper.addEntry(new GuessModel(0, 1));
        fragmentInterface.showResultFragment(getString(R.string.guess_wrong));

        for (int i = 0; i < helper.getGuessList().size(); i++) {
            Log.d("database_row: ", helper.getGuessList().get(i).getCorrectGuess() + " " + helper.getGuessList().get(i).getWrongGuess());
        }
        Log.d("Averages", " " + helper.guessCorrectPercentage());
    }


    @Override
    public void onDetach() {
        super.onDetach();
        song.release();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        song.release();
    }

    @Override
    public void onPause() {
        super.onPause();
        song.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        song.start();
    }
}
