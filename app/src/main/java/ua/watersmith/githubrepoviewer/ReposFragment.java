package ua.watersmith.githubrepoviewer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpFragment;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.watersmith.githubrepoviewer.entities.Repo;
import ua.watersmith.githubrepoviewer.retrofit.GitHubApiModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ReposFragment extends MvpFragment implements ReposView {

    private OnListFragmentInteractionListener mListener;
    private RepoItemRecyclerViewAdapter mRepoItemRecyclerViewAdapter;

    public ReposFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void onNext(List<Repo> repos) {
        setReposData(repos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repoitem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            mRepoItemRecyclerViewAdapter = new RepoItemRecyclerViewAdapter();
            mRepoItemRecyclerViewAdapter.setOnListFragmentInteractionListener(mListener);
            recyclerView.setAdapter(mRepoItemRecyclerViewAdapter);

            Observable<Repo[]> repoObservable = GitHubApiModule.getService().getRepos("square");
            repoObservable.map(repo -> Arrays.asList(repo)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::onNext,this::onError,this::onComplete);

        }
        return view;
    }

    private void onComplete() {

    }

    private void onError(Throwable throwable) {
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
            if (mRepoItemRecyclerViewAdapter!=null) {
                mRepoItemRecyclerViewAdapter.setOnListFragmentInteractionListener(mListener);
            }
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setReposData(List<Repo> items) {
        mRepoItemRecyclerViewAdapter.setRepoData(items);
    }

    @Override
    public void showProgress(boolean visible) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Repo item);
    }
}
