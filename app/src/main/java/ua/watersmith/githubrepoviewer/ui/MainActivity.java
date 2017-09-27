package ua.watersmith.githubrepoviewer.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import ua.watersmith.githubrepoviewer.R;
import ua.watersmith.githubrepoviewer.RepoFragment;
import ua.watersmith.githubrepoviewer.entities.Repo;
import ua.watersmith.githubrepoviewer.presentation.MainPresenter;
import ua.watersmith.githubrepoviewer.presentation.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView, ReposFragment.OnListFragmentInteractionListener {
    @InjectPresenter
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showFragment(Fragment fragment, String tag){
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    public void showRepos(ReposFragment reposFragment) {
        showFragment(reposFragment,"Repos");
    }

    @Override
    public void showRepoInfo(RepoFragment repoFragment) {
        showFragment(repoFragment,"Repo");
    }

    @Override
    public void showCommits() {

    }

    @Override
    public void showContributors() {

    }

    @Override
    public void onListFragmentInteraction(Repo item) {
        mMainPresenter.onRepoSelected(item);
    }
}
