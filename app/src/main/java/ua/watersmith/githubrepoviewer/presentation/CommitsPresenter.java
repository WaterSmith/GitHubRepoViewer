package ua.watersmith.githubrepoviewer.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.watersmith.githubrepoviewer.entities.CommitInfo;
import ua.watersmith.githubrepoviewer.retrofit.GitHubApiModule;

/**
 * Created by vodakov.s on 27.09.2017.
 */

@InjectViewState
public class CommitsPresenter extends MvpPresenter<CommitsView> {
    private List<CommitInfo> mListCommit;
    private String mRepoName;

    public CommitsPresenter(String mRepoName) {
        this.mRepoName = mRepoName;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Observable<CommitInfo[]> repoObservable = GitHubApiModule.getService().getCommits("square",mRepoName);
        repoObservable.map(Arrays::asList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext,this::onError,this::onComplete);
        getViewState().showProgress(true);
    }

    private void onNext(List<CommitInfo> commitInfos) {
        mListCommit = commitInfos;
    }

    private void onComplete() {
        getViewState().setCommitsData(mListCommit);
        getViewState().showProgress(false);
    }

    private void onError(Throwable throwable) {
        getViewState().showProgress(false);
    }

}
