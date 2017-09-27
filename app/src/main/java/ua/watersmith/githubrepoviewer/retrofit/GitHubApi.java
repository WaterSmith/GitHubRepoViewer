package ua.watersmith.githubrepoviewer.retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import ua.watersmith.githubrepoviewer.entities.CommitInfo;
import ua.watersmith.githubrepoviewer.entities.Contributor;
import ua.watersmith.githubrepoviewer.entities.Repo;

/**
 * Created by WaterSmith on 26.09.2017.
 */

public interface GitHubApi {
    @GET("users/{user}/repos")
    Observable<Repo[]> getRepos(@Path("user") String user);

    @GET("repos/{user}/{repo}/commits")
    Observable<CommitInfo[]> getCommits(@Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/contributors")
    Observable<Contributor[]> getContributors(@Path("user") String user, @Path("repo") String repo);
}
