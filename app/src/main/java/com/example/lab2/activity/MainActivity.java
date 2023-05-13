package com.example.lab2.activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import androidx.fragment.app.Fragment;


import com.example.lab2.R;
import com.example.lab2.fragments.EpisodeFragment;
import com.example.lab2.fragments.PersonageFragment;
import com.example.lab2.util.RecycleViewInterface;


public class MainActivity extends AppCompatActivity implements RecycleViewInterface {


    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            fragment = PersonageFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frame_container, fragment, "main_fragment");
            transaction.commit();
        }
    }

    @Override
    public void onItemClick(String[] episodes) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        String[] newEpisodes = new String[episodes.length];

        for (int i = 0; i < newEpisodes.length; i++) {
            if (episodes[i] != null)
                newEpisodes[i] = episodes[i].split("episode/")[1];
        }
        Fragment fragment = EpisodeFragment.newInstance(newEpisodes);
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack("myTrnsaction");
        transaction.commit();
    }
}