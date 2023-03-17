package com.example.lab2.dao;

import com.example.lab2.R;
import com.example.lab2.model.CharacterInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CharactersDao {
    static int[] characterImages = {
            R.drawable.rick_sanchez,
            R.drawable.morty_smith,
            R.drawable.summer,
            R.drawable.beth,
            R.drawable.jerry,
            R.drawable.abadango_cluster_princess,
            R.drawable.abradolf_incler,
            R.drawable.adjudicator_rick,
            R.drawable.agency_director,
            R.drawable.alan_rails,
            R.drawable.einstein
    };


    static String[] charactersNames = {
            "Rick Sanchez ",
            "Morty Smith ",
            "Summer Smith ",
            " Beth Smith ",
            "Jerry Smith ",
            "Abadango Princess ",
            "Abradolf Lincler ",
            "Adjudicator Rick ",
            " Agency Director ",
            "Alan Rails ",
            "Albert Einstein ",
    };

    static int[][] episodes = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51},

            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51},

            {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 38, 39, 40, 41, 42, 43, 44, 45, 46},

            {6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51},

            {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 26, 29, 30, 31, 32, 33, 35, 36, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51},

            {27},

            {10},

            {28},

            {24},

            {25},

            {12}
    };

    private static final List<CharacterInfo> characterInfos = new ArrayList<>();

    static {
        for (int i = 0; i < charactersNames.length; i++) {
            characterInfos.add(new CharacterInfo(i, charactersNames[i], characterImages[i], episodes[i]));
        }
    }




    public List<CharacterInfo> getAll(){
        return characterInfos;
    }

    public CharacterInfo get(int id){
        return characterInfos.get(id);
    }

    public List<CharacterInfo>  get(int from, int to){
        return characterInfos.subList(from, to);
    }
}
