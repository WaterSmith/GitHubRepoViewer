package ua.watersmith.githubrepoviewer;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by WaterSmith on 26.09.2017.
 */
@StateStrategyType(SingleStateStrategy.class)
public interface MainView extends MvpView {
    void showRepos(ReposFragment reposFragment);
    void showRepoInfo();
    void showCommits();
    void showContributors();
}
