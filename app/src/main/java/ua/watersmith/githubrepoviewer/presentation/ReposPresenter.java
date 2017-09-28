package ua.watersmith.githubrepoviewer.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.watersmith.githubrepoviewer.entities.Repo;
import ua.watersmith.githubrepoviewer.retrofit.GitHubApiModule;

/**
 * Created by vodakov.s on 27.09.2017.
 */

@InjectViewState
public class ReposPresenter extends MvpPresenter<ReposView> {
    List<Repo> mListRepo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Observable<Repo[]> repoObservable = GitHubApiModule.getService().getRepos("square");
        repoObservable.map(Arrays::asList).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onNext,this::onError,this::onComplete);
        getViewState().showProgress(true);
    }

    private void onComplete() {
        getViewState().setReposData(mListRepo);
        getViewState().showProgress(false);
    }

    private void onError(Throwable throwable) {
        getViewState().showProgress(false);
    }

    private void onNext(List<Repo> repos) {
        mListRepo = repos;
    }
}
