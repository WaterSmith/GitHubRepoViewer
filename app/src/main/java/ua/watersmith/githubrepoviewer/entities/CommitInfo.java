package ua.watersmith.githubrepoviewer.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by WaterSmith on 27.09.2017.
 */

public class CommitInfo {
    @SerializedName("commit")
    @Expose
    private Commit commit;

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }
}
