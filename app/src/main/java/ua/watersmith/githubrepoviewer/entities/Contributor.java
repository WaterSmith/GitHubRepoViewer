package ua.watersmith.githubrepoviewer.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by WaterSmith on 28.09.2017.
 */

public class Contributor {
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
