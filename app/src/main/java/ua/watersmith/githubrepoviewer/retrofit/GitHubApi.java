package ua.watersmith.githubrepoviewer.retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import ua.watersmith.githubrepoviewer.entities.Repo;

/**
 * Created by WaterSmith on 26.09.2017.
 */

public interface GitHubApi {
    @GET("users/{user}/repos")
    Observable<Repo[]> getRepos(@Path("user") String user);
}
