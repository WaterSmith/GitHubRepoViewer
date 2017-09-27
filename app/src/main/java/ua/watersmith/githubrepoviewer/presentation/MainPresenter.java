package ua.watersmith.githubrepoviewer.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ua.watersmith.githubrepoviewer.entities.Commit;
import ua.watersmith.githubrepoviewer.ui.CommitsFragment;
import ua.watersmith.githubrepoviewer.ui.ContributorsFragment;
import ua.watersmith.githubrepoviewer.ui.RepoFragment;
import ua.watersmith.githubrepoviewer.entities.Repo;
import ua.watersmith.githubrepoviewer.ui.ReposFragment;

/**
 * Created by WaterSmith on 26.09.2017.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        ReposFragment reposFragment = new ReposFragment();
        getViewState().showRepos(reposFragment);
     }


    public void onRepoSelected(Repo item) {
        RepoFragment repoFragment = RepoFragment.newInstance(item);
        getViewState().showRepoInfo(repoFragment);
    }

    public void onCommitsClick(String repoName){
        CommitsFragment commitsFragment = CommitsFragment.newInstance(repoName);
        getViewState().showCommits(commitsFragment, repoName);
    }

    public void onContributorsClick(String repoName){
        ContributorsFragment contributorsFragment = ContributorsFragment.newInstance(repoName);
        getViewState().showContributors(contributorsFragment);
    }
}
