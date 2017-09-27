package ua.watersmith.githubrepoviewer.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ua.watersmith.githubrepoviewer.RepoFragment;
import ua.watersmith.githubrepoviewer.entities.Repo;
import ua.watersmith.githubrepoviewer.ui.ReposFragment;

/**
 * Created by WaterSmith on 26.09.2017.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    @Override
    protected void onFirstViewAttach() {
        ReposFragment reposFragment = new ReposFragment();
        getViewState().showRepos(reposFragment);
        super.onFirstViewAttach();
    }


    public void onRepoSelected(Repo item) {
        RepoFragment repoFragment = new RepoFragment();
        getViewState().showRepoInfo(repoFragment);
        int i = 1;
    }
}
