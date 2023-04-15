package com.example.lab2.viewModel;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.lab2.cleients.RickAndMortyClient;
import com.example.lab2.db.DataBase;
import com.example.lab2.db.dao.AppDao;
import com.example.lab2.db.model.Episode;
import com.example.lab2.dto.EpisodeDto;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel extends AndroidViewModel {

    private final AppDao appDao;

    public EpisodeViewModel(@NonNull Application application) {
        super(application);
        appDao = DataBase.getInstance(application).PersonageDao();

    }


    public MutableLiveData<List<Episode>> makeApiCall(String[] numOfEpisodesArr) {
        MutableLiveData<List<Episode>> epsodes = new MutableLiveData<>();

        String episodes = Stream.of(numOfEpisodesArr).map(x -> x + ",")
                .collect(Collectors.joining());

        Call<List<EpisodeDto>> apiCall = RickAndMortyClient.getInstance().getApis()
                .getEpisodes(episodes);

        apiCall.enqueue(new Callback<List<EpisodeDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<EpisodeDto>> call,
                                   @NonNull Response<List<EpisodeDto>> response) {

                List<Episode> episodeDb = dtoToModel(response.body());
                epsodes.postValue(episodeDb);
                Toast.makeText(getApplication(), "Got Episode", Toast.LENGTH_SHORT).show();
                new InsertEpisodeAsyncTask(appDao).execute(episodeDb);
            }

            @Override
            public void onFailure(@NonNull Call<List<EpisodeDto>> call,
                                  @NonNull Throwable t) {
                Toast.makeText(getApplication(), "Error get episode", Toast.LENGTH_SHORT).show();
                new GetEpisodesAsyncTask(appDao, numOfEpisodesArr).execute(epsodes);
            }
        });

        return epsodes;
    }

    private  class GetEpisodesAsyncTask extends AsyncTask<MutableLiveData<List<Episode>>, Void, Void> {
        private final AppDao appDao;
        private String[] episodesInStr;


        private GetEpisodesAsyncTask(AppDao appDao, String[] episodesInStr) {
            this.appDao = appDao;
            this.episodesInStr = episodesInStr;
        }

        @Override
        public Void doInBackground(MutableLiveData<List<Episode>>... personages) {
            List<Integer> integerList = Arrays.stream(episodesInStr)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            List<Episode> allEpisodr = appDao.getAll(integerList);
            personages[0].postValue(allEpisodr);

            return null;
        }
    }

    private  class InsertEpisodeAsyncTask extends AsyncTask<List<Episode>, Void, Void> {
        private final AppDao appDao;

        private InsertEpisodeAsyncTask(AppDao appDao) {
            this.appDao = appDao;

        }

        @Override
        public Void doInBackground(List<Episode>... personages) {
            appDao.insertAllEpisode(personages[0]);
            return null;
        }
    }


    List<Episode> dtoToModel(List<EpisodeDto> episodeDtos) {
        if (episodeDtos == null) return new LinkedList<>();

        List<Episode> episodesModels = new LinkedList<>();
        for (EpisodeDto e : episodeDtos) {
            episodesModels.add(new Episode(e.getId(),e.getName(), e.getEpisode(), e.getAirDate()));
        }

        return episodesModels;
    }

}
