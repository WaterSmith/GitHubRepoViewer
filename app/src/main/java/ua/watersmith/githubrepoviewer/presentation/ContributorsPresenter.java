package ua.watersmith.githubrepoviewer.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.watersmith.githubrepoviewer.entities.CommitInfo;
import ua.watersmith.githubrepoviewer.entities.Contributor;
import ua.watersmith.githubrepoviewer.retrofit.GitHubApiModule;

/**
 * Created by vodakov.s on 27.09.2017.
 */

@InjectViewState
public class ContributorsPresenter extends MvpPresenter<ContributorsView> {
    private List<Contributor> mListContributor;
    private String mRepoName;

    public ContributorsPresenter(String mRepoName) {
        this.mRepoName = mRepoName;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Observable<Contributor[]> repoObservable = GitHubApiModule.getService().getContributors("square",mRepoName);
        repoObservable.map(commits -> Arrays.asList(commits)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onNext,this::onError,this::onComplete);
        getViewState().showProgress(true);
    }

    private void onNext(List<Contributor> contributors) {
        mListContributor = contributors;
    }


    private void onComplete() {
        getViewState().setContributorsData(mListContributor);
        getViewState().showProgress(false);
    }

    private void onError(Throwable throwable) {
        getViewState().showProgress(false);
    }

}
