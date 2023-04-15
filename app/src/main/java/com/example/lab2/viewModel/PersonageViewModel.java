package com.example.lab2.viewModel;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lab2.cleients.RickAndMortyClient;
import com.example.lab2.db.DataBase;
import com.example.lab2.db.dao.AppDao;
import com.example.lab2.db.model.Personage;
import com.example.lab2.dto.CharactersInfo;
import com.example.lab2.dto.PersonageDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonageViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Personage>> personages;
    List<Personage> allPersonageDtos = new ArrayList<>();
    String nextPage = "1";
    EpisodeViewModel episodeViewModel;

    private final AppDao appDao;



    public PersonageViewModel(@NonNull Application application) {
        super(application);
        appDao = DataBase.getInstance(application).PersonageDao();
        personages = new MutableLiveData<>();

        personages.postValue(new ArrayList<>());

        episodeViewModel = new EpisodeViewModel(application);
    }

    public LiveData<List<Personage>> getPersonages() {
        return personages;
    }

    public void makeApiCall() {

        if (nextPage == null) {
            Toast.makeText(getApplication(),
                    "All characters downloud", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<CharactersInfo> apiCall = RickAndMortyClient.getInstance().getApis()
                .getCharacters(nextPage);


        apiCall.enqueue(new Callback<CharactersInfo>() {
            @Override
            public void onResponse(@NonNull Call<CharactersInfo> call,
                                   @NonNull Response<CharactersInfo> response) {
                CharactersInfo resCharactersInfo;
                if (response.isSuccessful() && response.body() != null) {

                    resCharactersInfo = response.body();
                    String next = resCharactersInfo.getNextPage();
                    if (next != null) {
                        nextPage = next.split("page=")[1];
                    } else {
                        nextPage = null;
                    }

                    Toast.makeText(getApplication(), "Got Persons", Toast.LENGTH_SHORT).show();
                    List<Personage> resPersonageDtos = dtoToModel(resCharactersInfo.getCharactersInfo());
                    new InsertPersonAsyncTask(appDao).execute(resPersonageDtos);
                    allPersonageDtos.addAll(resPersonageDtos);
                    personages.postValue(allPersonageDtos);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CharactersInfo> call,
                                  @NonNull Throwable t) {

                Toast.makeText(getApplication(), "Error loud", Toast.LENGTH_SHORT).show();
                new GetPersonAsyncTask(appDao).execute(personages);
            }
        });
    }

    private class GetPersonAsyncTask extends AsyncTask<MutableLiveData<List<Personage>>, Void, Void> {
        private final AppDao appDao;

        private GetPersonAsyncTask(AppDao appDao) {
            this.appDao = appDao;
        }

        @Override
        public Void doInBackground(MutableLiveData<List<Personage>>... personages) {
            allPersonageDtos = appDao.getAll();
            nextPage = (allPersonageDtos.size() / 20 + 1) + "";
            personages[0].postValue(allPersonageDtos);

            return null;
        }
    }

    private class InsertPersonAsyncTask extends AsyncTask<List<Personage>, Void, Void> {
        private final AppDao appDao;

        private InsertPersonAsyncTask(AppDao appDao) {
            this.appDao = appDao;
        }

        @Override
        public Void doInBackground(List<Personage>... personages) {
            if (personages[0] != null)
                appDao.insertAllPersonage(personages[0]);

            return null;
        }
    }

    static List<Personage> dtoToModel(List<PersonageDto> personageDtos) {
        List<Personage> personageList = new LinkedList<>();

        for (PersonageDto pers : personageDtos) {
            StringBuilder sb = new StringBuilder();
            for (String ep : pers.getEpisods()) {
                sb.append(ep).append(';');
            }

            personageList.add(new Personage(pers.getId(),
                    pers.getName(),
                    pers.getImage(),
                    sb.toString()));
        }

        return personageList;
    }


}