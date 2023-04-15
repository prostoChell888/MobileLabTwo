package com.example.lab2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lab2.R;
import com.example.lab2.db.model.Personage;
import com.example.lab2.util.RecycleViewInterface;
import com.example.lab2.adapters.CharacterAdapter;
import com.example.lab2.dto.PersonageDto;
import com.example.lab2.dto.CharactersInfo;
import com.example.lab2.viewModel.PersonageViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecycleViewInterface {

    RecyclerView recyclerView;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    List<PersonageDto> allPersonageDtos = new ArrayList<>();
    PersonageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nestedScrollView = findViewById(R.id.scroll_view);
        progressBar = findViewById(R.id.progress_bar);

        recyclerView = findViewById(R.id.firstRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CharacterAdapter adapter = new CharacterAdapter(this, allPersonageDtos);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(PersonageViewModel.class);

        viewModel.makeApiCall();
        viewModel.getPersonages().observe(this, new Observer<List<Personage>>() {
            @Override
            public void onChanged(@NonNull List<Personage> personage) {
                List<PersonageDto> personageDtos = modelToDto(personage);
                allPersonageDtos = personageDtos;
                adapter.setCharacters(personageDtos);
//                viewModel.setNextPage(personage.size() / 20 + 1);
            }
        });


        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY,
                                       int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    progressBar.setVisibility(View.VISIBLE);

                    viewModel.makeApiCall();
                    viewModel.getPersonages().observe(MainActivity.this,
                            new Observer<List<Personage>>() {
                                @Override
                                public void onChanged(@NonNull List<Personage> personage) {
                                    List<PersonageDto> personageDtos = modelToDto(personage);
                                    allPersonageDtos = personageDtos;
                                    adapter.setCharacters(personageDtos);
//                                    viewModel.setNextPage(personage.size() / 20 + 1);
                                }
                            });
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private List<PersonageDto> modelToDto(List<Personage> personage) {
        List<PersonageDto> personageDtos = new LinkedList<>();

        for (Personage pers : personage) {
            personageDtos.add(new PersonageDto(pers.getCharacterId(),
                    pers.getName(), pers.getImage(), pers.getEpisodes().split(";")));
        }

        return personageDtos;
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ViewEpisodeActivity.class);
        String[] strArrWp = allPersonageDtos.get(position).getEpisods();
        String[] newEpisodes = new String[strArrWp.length];

        for (int i = 0; i < newEpisodes.length; i++) {
            if (strArrWp[i] != null)
                newEpisodes[i] = strArrWp[i].split("episode/")[1];
        }
        intent.putExtra("numOfEpisodes", newEpisodes);
        startActivity(intent);
    }
}