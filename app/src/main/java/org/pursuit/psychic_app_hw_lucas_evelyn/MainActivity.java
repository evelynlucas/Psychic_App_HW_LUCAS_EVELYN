package org.pursuit.psychic_app_hw_lucas_evelyn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showFirstFragment(String text) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ChoiceFragment.newInstance(text))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showResultFragment(String text) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ResultFragment.newInstance(text))
                .addToBackStack(null)
                .commit();
    }
}
