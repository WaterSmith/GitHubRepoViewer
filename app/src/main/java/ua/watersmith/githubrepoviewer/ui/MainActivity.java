package ua.watersmith.githubrepoviewer.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import ua.watersmith.githubrepoviewer.R;
import ua.watersmith.githubrepoviewer.entities.Repo;
import ua.watersmith.githubrepoviewer.presentation.MainPresenter;
import ua.watersmith.githubrepoviewer.presentation.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView, ReposFragment.OnListFragmentInteractionListener, RepoFragment.OnRepoFragmentInteractionListener {
    @InjectPresenter
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 1) {
            manager.popBackStackImmediate();
        }
        super.onBackPressed();
    }

    public void showFragment(Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    public void showRepos(ReposFragment reposFragment) {
        //setTitle("Square repo's");
        showFragment(reposFragment,"Repos");
    }

    @Override
    public void showRepoInfo(RepoFragment repoFragment) {
        //setTitle("Repo info");
        showFragment(repoFragment,"Repo");
    }

    @Override
    public void showCommits(CommitsFragment commitsFragment, String repoName) {
        //setTitle(repoName);
        showFragment(commitsFragment,"Commits");
    }

    @Override
    public void showContributors(ContributorsFragment contributorsFragment) {
        showFragment(contributorsFragment,"Contributors");
    }

    @Override
    public void onListFragmentInteraction(Repo item) {
        mMainPresenter.onRepoSelected(item);
    }

    @Override
    public void onCommitsClick(String repoName) {
        mMainPresenter.onCommitsClick(repoName);
    }

    @Override
    public void onContributorsClick(String repoName) {
        mMainPresenter.onContributorsClick(repoName);
    }
}
