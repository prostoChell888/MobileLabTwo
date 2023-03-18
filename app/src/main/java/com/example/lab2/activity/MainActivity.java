package com.example.lab2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab2.R;
import com.example.lab2.RecycleViewInterface;
import com.example.lab2.adapters.CharacterAdapter;
import com.example.lab2.dao.CharactersDao;

public class MainActivity extends AppCompatActivity  implements RecycleViewInterface {
    private  final CharactersDao charactersInfo = new CharactersDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.firstRecyclerView);

        CharactersDao characterInfos = new CharactersDao();

        CharacterAdapter adapter = new CharacterAdapter(characterInfos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ViewEpisodeActivity.class);
        intent.putExtra("numOfEpisodes", charactersInfo.get(position).getEpisods());
        startActivity(intent);
    }
}