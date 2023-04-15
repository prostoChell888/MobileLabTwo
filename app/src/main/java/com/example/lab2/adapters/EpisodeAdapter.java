package com.example.lab2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.R;
import com.example.lab2.db.model.Episode;
import com.example.lab2.dto.EpisodeDto;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeHolder> {
    private final Context context;
    private  List<Episode> episodeDtos;

    public void setEpisodes(List<Episode> episodeDtos) {
        this.episodeDtos = episodeDtos;
        notifyDataSetChanged();
    }

    public EpisodeAdapter(Context context, List<Episode> episodeDtos) {
        this.context = context;
        this.episodeDtos = episodeDtos;
    }

    @NonNull
    @Override
    public EpisodeAdapter.EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_episode, parent, false);

        return new EpisodeAdapter.EpisodeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.EpisodeHolder holder, int position) {
        holder.numOfEpisode.setText(episodeDtos.get(position).getAirDate());
        holder.nameOfEpisode.setText(episodeDtos.get(position).getName());
        holder.dateRealiseOfEpisode.setText(episodeDtos.get(position).getEpisode());
    }


    @Override
    public int getItemCount() {
        return episodeDtos.size();
    }

    public static class EpisodeHolder extends RecyclerView.ViewHolder {
        TextView numOfEpisode;
        TextView nameOfEpisode;
        TextView dateRealiseOfEpisode;

        public EpisodeHolder(@NonNull View itemView) {
            super(itemView);
            numOfEpisode = itemView.findViewById(R.id.date_of_relise);
            nameOfEpisode = itemView.findViewById(R.id.name_of_episode);
            dateRealiseOfEpisode = itemView.findViewById(R.id.id_of_episode);
        }
    }

}
