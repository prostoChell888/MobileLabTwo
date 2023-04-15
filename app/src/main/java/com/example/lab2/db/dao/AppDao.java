package com.example.lab2.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.lab2.db.model.Episode;
import com.example.lab2.db.model.Personage;

import java.util.List;

@Dao
public interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllPersonage(List<Personage> personage);


    @Query("SELECT * FROM Personage ORDER BY characterId")
    List<Personage> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllEpisode(List<Episode> episodes);


    @Query("SELECT * FROM episode WHERE episodeId IN (:episodesId) ORDER BY episodeId")
    List<Episode> getAll(List<Integer> episodesId);
}