package com.example.lab2.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.R;
import com.example.lab2.adapters.EpisodeAdapter;
import com.example.lab2.dao.EpisodesDao;

public class ViewEpisodeActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_episode);

        Bundle myBundle = getIntent().getExtras();
        int[] stringArray = myBundle.getIntArray("numOfEpisodes");

        RecyclerView recyclerView = findViewById(R.id.thecondRecyclerView);

        EpisodesDao episodesInfo = new EpisodesDao();


        EpisodeAdapter adapter = new EpisodeAdapter(this, episodesInfo.get(stringArray));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
