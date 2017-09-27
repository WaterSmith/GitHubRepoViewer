package ua.watersmith.githubrepoviewer.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by WaterSmith on 26.09.2017.
 */

public class Repo implements Serializable{
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("contributors_url")
    @Expose
    private String contributorsUrl;
    @SerializedName("commits_url")
    @Expose
    private String commitsUrl;
    @SerializedName("stargazers_count")
    @Expose
    private Integer stargazersCount;
    @SerializedName("forks_count")
    @Expose
    private Integer forksCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }

}
