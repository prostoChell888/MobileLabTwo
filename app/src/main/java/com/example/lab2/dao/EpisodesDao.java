package com.example.lab2.dao;

import com.example.lab2.model.CharacterInfo;
import com.example.lab2.model.Episode;

import java.util.ArrayList;
import java.util.List;

public class EpisodesDao {

    private static List<Episode> episodeList = List.of(
            new Episode(1, "Pilot", "December 2, 2013", "S01E01"),
            new Episode(2, "Lawnmower Dog", "December 9, 2013", "S01E02"),
            new Episode(3, "Anatomy Park", "December 16, 2013", "S01E03"),
            new Episode(4, "M. Night Shaym-Aliens!", "January 13, 2014", "S01E04"),
            new Episode(5, "Meeseeks and Destroy", "January 20, 2014", "S01E05"),
            new Episode(6, "Rick Potion #9", "January 27, 2014", "S01E06"),
            new Episode(7, "Raising Gazorpazorp", "March 10, 2014", "S01E07"),
            new Episode(8, "Rixty Minutes", "March 17, 2014", "S01E08"),
            new Episode(9, "Something Ricked This Way Comes", "March 24, 2014", "S01E09"),
            new Episode(10, "Close Rick-counters of the Rick Kind", "April 7, 2014", "S01E10"),
            new Episode(11, "Ricksy Business", "April 14, 2014", "S01E11"),
            new Episode(12, "A Rickle in Time", "July 26, 2015", "S02E01"),
            new Episode(13, "Mortynight Run", "August 2, 2015", "S02E02"),
            new Episode(14, "Auto Erotic Assimilation", "August 9, 2015", "S02E03"),
            new Episode(15, "Total Rickall", "August 16, 2015", "S02E04"),
            new Episode(16, "Get Schwifty", "August 23, 2015", "S02E05"),
            new Episode(17, "The Ricks Must Be Crazy", "August 30, 2015", "S02E06"),
            new Episode(18, "Big Trouble in Little Sanchez", "September 13, 2015", "S02E07"),
            new Episode(19, "Interdimensional Cable 2: Tempting Fate", "September 20, 2015", "S02E08"),
            new Episode(20, "Look Who's Purging Now", "September 27, 2015", "S02E09"),
            new Episode(21, "The Wedding Squanchers", "October 4, 2015", "S02E10"),
            new Episode(22, "The Rickshank Rickdemption", "April 1, 2017", "S03E01"),
            new Episode(23, "Rickmancing the Stone", "July 30, 2017", "S03E02"),
            new Episode(24, "Pickle Rick", "August 6, 2017", "S03E03"),
            new Episode(25, "Vindicators 3: The Return of Worldender", "August 13, 2017", "S03E04"),
            new Episode(26, "The Whirly Dirly Conspiracy", "August 20, 2017", "S03E05"),
            new Episode(27, "Rest and Ricklaxation", "August 27, 2017", "S03E06"),
            new Episode(28, "The Ricklantis Mixup", "September 10, 2017", "S03E07"),
            new Episode(29, "Morty's Mind Blowers", "September 17, 2017", "S03E08"),
            new Episode(30, "The ABC's of Beth", "September 24, 2017", "S03E09"),
            new Episode(31, "The Rickchurian Mortydate", "October 1, 2017", "S03E10"),
            new Episode(32, "Edge of Tomorty: Rick, Die, Rickpeat", "November 10, 2019", "S04E01"),
            new Episode(33, "The Old Man and the Seat", "November 17, 2019", "S04E02"),
            new Episode(34, "One Crew Over the Crewcoo's Morty", "November 24, 2019", "S04E03"),
            new Episode(35, "Claw and Hoarder: Special Ricktim's Morty", "December 8, 2019", "S04E04"),
            new Episode(36, "Rattlestar Ricklactica", "December 15, 2019", "S04E05"),
            new Episode(37, "Never Ricking Morty", "May 3, 2020", "S04E06"),
            new Episode(38, "Promortyus", "May 10, 2020", "S04E07"),
            new Episode(39, "The Vat of Acid Episode", "May 17, 2020", "S04E08"),
            new Episode(40, "Childrick of Mort", "May 24, 2020", "S04E09"),
            new Episode(41, "Star Mort: Rickturn of the Jerri", "May 31, 2020", "S04E10"),
            new Episode(42, "Mort Dinner Rick Andre", "June 20, 2021", "S05E01"),
            new Episode(43, "Mortyplicity", "June 27, 2021", "S05E02"),
            new Episode(44, "A Rickconvenient Mort", "July 4, 2021", "S05E03"),
            new Episode(45, "Rickdependence Spray", "July 11, 2021", "S05E04"),
            new Episode(46, "Amortycan Grickfitti", "July 18, 2021", "S05E05"),
            new Episode(47, "Rick & Morty's Thanksploitation Spectacular", "July 25, 2021", "S05E06"),
            new Episode(48, "Gotron Jerrysis Rickvangelion", "August 1, 2021", "S05E07"),
            new Episode(49, "Rickternal Friendshine of the Spotless Mort", "August 8, 2021", "S05E08"),
            new Episode(50, "Forgetting Sarick Mortshall", "September 5, 2021", "S05E09"),
            new Episode(51, "Rickmurai Jack", "September 5, 2021", "S05E10")
    );

    public List<Episode> getAll() {
        return episodeList;
    }

    public Episode get(int id) {
        return episodeList.get(id);
    }

    public List<Episode> get(int[] numOfEpisodes) {
        List<Episode> listOfEpisodes = new ArrayList<>();
        for (int numOfEpisode : numOfEpisodes) {
            listOfEpisodes.add(episodeList.get(numOfEpisode - 1));
        }

        return listOfEpisodes;
    }

    public List<Episode> get(int from, int to) {
        return episodeList.subList(from, to);
    }


}
