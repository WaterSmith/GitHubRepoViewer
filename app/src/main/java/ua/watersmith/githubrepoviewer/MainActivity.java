package ua.watersmith.githubrepoviewer;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {
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
    public void showRepoInfo() {

    }

    @Override
    public void showCommits() {

    }

    @Override
    public void showContributors() {

    }
}
