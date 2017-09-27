package ua.watersmith.githubrepoviewer.presentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ua.watersmith.githubrepoviewer.RepoFragment;
import ua.watersmith.githubrepoviewer.ui.ReposFragment;

/**
 * Created by WaterSmith on 26.09.2017.
 */
@StateStrategyType(SkipStrategy.class)
public interface MainView extends MvpView {
    void showRepos(ReposFragment reposFragment);
    void showRepoInfo(RepoFragment repoFragment);
    void showCommits();
    void showContributors();
}
