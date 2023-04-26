package com.example.lab2.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lab2.R;

import com.example.lab2.dto.PersonageDto;
import com.example.lab2.util.RecycleViewInterface;

import java.util.List;


public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterHolder> {
    private final Context context;
    private  List<PersonageDto> personage;

    public void setCharacters(List<PersonageDto> personage) {
        this.personage = personage;
        notifyDataSetChanged();
    }

    public CharacterAdapter(Context context, List<PersonageDto> personage) {
        this.context = context;
        this.personage = personage;
    }

    @NonNull
    @Override
    public CharacterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_character, parent, false);

        return new CharacterHolder(view, (RecycleViewInterface) context);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterHolder holder, int position) {
        PersonageDto personageDto = personage.get(position);

        holder.name.setText(personageDto.getName());

        Glide.with(context).load(personageDto.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return personage.size();
    }

    public  class CharacterHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;

        public CharacterHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);

            avatar = itemView.findViewById(R.id.photo_of_character);
            name = itemView.findViewById(R.id.name_of_character);

            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    String[] episodes = personage.get(pos).getEpisods();
                    recycleViewInterface.onItemClick(episodes);
                }
            });
        }
    }
}