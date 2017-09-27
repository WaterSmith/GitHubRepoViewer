package ua.watersmith.githubrepoviewer.presentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ua.watersmith.githubrepoviewer.ui.CommitsFragment;
import ua.watersmith.githubrepoviewer.ui.ContributorsFragment;
import ua.watersmith.githubrepoviewer.ui.RepoFragment;
import ua.watersmith.githubrepoviewer.ui.ReposFragment;

/**
 * Created by WaterSmith on 26.09.2017.
 */
@StateStrategyType(SkipStrategy.class)
public interface MainView extends MvpView {
    void showRepos(ReposFragment reposFragment);
    void showRepoInfo(RepoFragment repoFragment);
    void showCommits(CommitsFragment commitsFragment, String repoName);
    void showContributors(ContributorsFragment contributorsFragment);
}
