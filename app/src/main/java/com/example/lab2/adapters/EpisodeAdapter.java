package com.example.lab2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.R;
import com.example.lab2.model.Episode;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeHolder> {



    private final List<Episode> episodes;

    public EpisodeAdapter( List<Episode> episodes) {

        this.episodes = episodes;
    }

    @NonNull
    @Override
    public EpisodeAdapter.EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_episode, parent, false);

        return new EpisodeAdapter.EpisodeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.EpisodeHolder holder, int position) {
        holder.numOfEpisode.setText(episodes.get(position).getEpisode());
        holder.nameOfEpisode.setText(episodes.get(position).getName());
        holder.dateRealiseOfEpisode.setText(episodes.get(position).getAirDate());
    }


    @Override
    public int getItemCount() {
        return episodes.size();
    }

    public static class EpisodeHolder extends RecyclerView.ViewHolder {
        TextView numOfEpisode;
        TextView nameOfEpisode;
        TextView dateRealiseOfEpisode;

        public EpisodeHolder(@NonNull View itemView) {
            super(itemView);

            numOfEpisode = itemView.findViewById(R.id.id_of_episode);
            nameOfEpisode = itemView.findViewById(R.id.name_of_episode);
            dateRealiseOfEpisode = itemView.findViewById(R.id.date_of_relise);

        }
    }

}
