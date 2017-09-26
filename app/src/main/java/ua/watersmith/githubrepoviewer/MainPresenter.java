package ua.watersmith.githubrepoviewer;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import ua.watersmith.githubrepoviewer.entities.Repo;
import ua.watersmith.githubrepoviewer.retrofit.GitHubApiModule;

/**
 * Created by WaterSmith on 26.09.2017.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private Object onNext;

    @Override
    protected void onFirstViewAttach() {
        ReposFragment reposFragment = new ReposFragment();
        getViewState().showRepos(reposFragment);
        super.onFirstViewAttach();
    }


}
