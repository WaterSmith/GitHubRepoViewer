package ua.watersmith.githubrepoviewer.presentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ua.watersmith.githubrepoviewer.entities.Commit;
import ua.watersmith.githubrepoviewer.entities.CommitInfo;
import ua.watersmith.githubrepoviewer.entities.Repo;

/**
 * Created by WaterSmith on 26.09.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface CommitsView extends MvpView {
    void setCommitsData(List<CommitInfo> items);
    void showProgress(boolean visible);
}
