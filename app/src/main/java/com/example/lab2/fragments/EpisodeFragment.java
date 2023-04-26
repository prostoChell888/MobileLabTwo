package com.example.lab2.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab2.R;
import com.example.lab2.adapters.EpisodeAdapter;
import com.example.lab2.db.model.Episode;
import com.example.lab2.util.RecycleViewInterface;
import com.example.lab2.viewModel.EpisodeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EpisodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EpisodeFragment extends Fragment  {

    RecyclerView recyclerView;
    EpisodeViewModel viewModel;
    EpisodeAdapter adapter;

    List<Episode> episodeDtoList = new ArrayList<>();

    private static final String ARG_PARAM1 = "param1";

    private String[] numOfEpisodesArr;
    public EpisodeFragment() {}


    public static EpisodeFragment newInstance(String[] numOfEpisodesArr) {
        EpisodeFragment fragment = new EpisodeFragment();
        Bundle args = new Bundle();
        args.putStringArray(ARG_PARAM1, numOfEpisodesArr);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            numOfEpisodesArr = getArguments().getStringArray(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_episode, container, false);
        recyclerView = view.findViewById(R.id.thecondRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new EpisodeAdapter(view.getContext(), episodeDtoList);
        recyclerView.setAdapter(adapter);
        getEpisodes(numOfEpisodesArr);

        return view;
    }

    private void getEpisodes(String[] numOfEpisodesArr) {
        viewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
        viewModel.makeApiCall(numOfEpisodesArr).observe(getViewLifecycleOwner(),
                new Observer<List<Episode>>() {
                    @Override
                    public void onChanged(@NonNull List<Episode> episodeDtos) {
                        episodeDtoList = episodeDtos;
                        adapter.setEpisodes(episodeDtos);
                    }
                });
    }


}