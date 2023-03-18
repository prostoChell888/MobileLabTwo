package com.example.lab2.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2.R;
import com.example.lab2.RecycleViewInterface;
import com.example.lab2.dao.CharactersDao;


public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharapterHolder> {

    private final CharactersDao characters;


    public CharacterAdapter( CharactersDao characters) {

        this.characters = characters;
    }

    @NonNull
    @Override
    public CharapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_character, parent, false);

        return new CharapterHolder(view, (RecycleViewInterface) parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull CharapterHolder holder, int position) {
        holder.name.setText(characters.get(position).getName());
        holder.avatar.setImageResource(characters.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return characters.getAll().size();
    }

    public static class CharapterHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView name;

        public CharapterHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);

            avatar = itemView.findViewById(R.id.photo_of_character);
            name = itemView.findViewById(R.id.name_of_character);

            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    recycleViewInterface.onItemClick(pos);

                }
            });
        }
    }
}