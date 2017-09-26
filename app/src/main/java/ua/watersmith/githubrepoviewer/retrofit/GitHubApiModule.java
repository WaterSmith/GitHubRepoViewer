package ua.watersmith.githubrepoviewer.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WaterSmith on 27.09.2017.
 */

public class GitHubApiModule {
    private static GitHubApi sService;

    private GitHubApiModule() {
    }

    public static GitHubApi getService() {
        if (sService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sService = retrofit.create(GitHubApi.class);
        }
        return sService;
    }
}
