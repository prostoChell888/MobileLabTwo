package com.example.lab2.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.lab2.R;
import com.example.lab2.activity.MainActivity;
import com.example.lab2.adapters.CharacterAdapter;
import com.example.lab2.db.model.Personage;
import com.example.lab2.dto.PersonageDto;
import com.example.lab2.util.RecycleViewInterface;
import com.example.lab2.viewModel.PersonageViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonageFragment extends Fragment  {

    RecyclerView recyclerView;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    PersonageViewModel viewModel;

    List<PersonageDto> allPersonageDtos = new LinkedList<>();

    public PersonageFragment() {
        // Required empty public constructor
    }

    public static PersonageFragment newInstance() {
        return new PersonageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PersonageViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_personage, container, false);

        nestedScrollView = view.findViewById(R.id.scroll_view);
        progressBar = view.findViewById(R.id.progress_bar);

        recyclerView = view.findViewById(R.id.firstRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CharacterAdapter adapter = new CharacterAdapter(view.getContext(), allPersonageDtos);
        recyclerView.setAdapter(adapter);
        if (allPersonageDtos.size() == 0) {
            viewModel.makeApiCall();
            viewModel.getPersonages().observe( getViewLifecycleOwner(), new Observer<List<Personage>>() {
                @Override
                public void onChanged(@NonNull List<Personage> personage) {
                    List<PersonageDto> personageDtos = modelToDto(personage);
                    allPersonageDtos = personageDtos;
                    adapter.setCharacters(personageDtos);
//  удалено         viewModel.setNextPage(personage.size() / 20 + 1);
                }
            });
        }



        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @SuppressLint("FragmentLiveDataObserve")
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY,
                                       int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    progressBar.setVisibility(View.VISIBLE);

                    viewModel.makeApiCall();
                    viewModel.getPersonages().observe(PersonageFragment.this,
                            new Observer<List<Personage>>() {
                                @Override
                                public void onChanged(@NonNull List<Personage> personage) {
                                    List<PersonageDto> personageDtos = modelToDto(personage);
                                    allPersonageDtos = personageDtos;
                                    adapter.setCharacters(personageDtos);
//                удалено                    viewModel.setNextPage(personage.size() / 20 + 1);
                                }
                            });
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }

    private List<PersonageDto> modelToDto (List < Personage > personage) {
        List<PersonageDto> personageDtos = new LinkedList<>();

        for (Personage pers : personage) {
            personageDtos.add(new PersonageDto(pers.getCharacterId(),
                    pers.getName(), pers.getImage(), pers.getEpisodes().split(";")));
        }

        return personageDtos;
    }


}