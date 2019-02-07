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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private FragmentInterface fragmentInterface;
    private MediaPlayer song;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInterface) {
            fragmentInterface = (FragmentInterface) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        song = MediaPlayer.create(rootView.getContext(), R.raw.doomed);
        song.start();
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView textView = view.findViewById(R.id.intro_textview);
        final Spinner spinner = view.findViewById(R.id.spinner);
        final Button themeButton = view.findViewById(R.id.theme_button);
        textView.setText(R.string.intro_text);

        themeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinnerSelection = spinner.getSelectedItem().toString();
                fragmentInterface.showFirstFragment(spinnerSelection);
            }
        });
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
