package org.pursuit.psychic_app_hw_lucas_evelyn;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.psychic_app_hw_lucas_evelyn.database.DatabaseHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    public static final String FRAGMENT_STRING_KEY = "String_key";
    private MediaPlayer song;
    private FragmentInterface fragmentInterface;
    private DatabaseHelper helper;
    private View rootView;


    public static ResultFragment newInstance(String string) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_STRING_KEY, string);
        resultFragment.setArguments(args);
        return resultFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInterface = (FragmentInterface) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_result, container, false);
        song = MediaPlayer.create(rootView.getContext(), R.raw.doomed);
        song.start();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textViewPercentage = view.findViewById(R.id.percent_textview);
        TextView textViewString = view.findViewById(R.id.result_textview);
        helper = new DatabaseHelper(view.getContext());
        String percentString = String.format("%.0f", helper.guessCorrectPercentage());
        textViewPercentage.setText(percentString + getString(R.string.percentage));
        if (getArguments() != null && getArguments().containsKey(FRAGMENT_STRING_KEY)) {
            textViewString.setText(getArguments().getString(FRAGMENT_STRING_KEY));
        }
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
