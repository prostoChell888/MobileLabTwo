package com.example.lab2.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.R;
import com.example.lab2.adapters.EpisodeAdapter;
import com.example.lab2.db.model.Episode;
import com.example.lab2.dto.EpisodeDto;
import com.example.lab2.viewModel.EpisodeViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewEpisodeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EpisodeViewModel viewModel;
    EpisodeAdapter adapter;

    List<Episode> episodeDtoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_episode);

        recyclerView = findViewById(R.id.thecondRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EpisodeAdapter(this, episodeDtoList);
        recyclerView.setAdapter(adapter);

        String[] numOfEpisodesArr = getIntent()
                .getExtras()
                .getStringArray("numOfEpisodes");

        getEpisodes(numOfEpisodesArr);
    }


    private void getEpisodes(String[] numOfEpisodesArr) {
        viewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
        viewModel.makeApiCall(numOfEpisodesArr)
                .observe(this, new Observer<List<Episode>>() {
            @Override
            public void onChanged(@NonNull List<Episode> episodeDtos) {
                episodeDtoList = episodeDtos;
                adapter.setEpisodes(episodeDtos);
            }
        });
    }
}
