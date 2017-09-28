package ua.watersmith.githubrepoviewer.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import ua.watersmith.githubrepoviewer.R;
import ua.watersmith.githubrepoviewer.entities.Repo;
import ua.watersmith.githubrepoviewer.presentation.ReposPresenter;
import ua.watersmith.githubrepoviewer.presentation.ReposView;
import ua.watersmith.githubrepoviewer.ui.adapters.RepoItemRecyclerViewAdapter;


public class ReposFragment extends MvpAppCompatFragment
        implements ReposView, RepoItemRecyclerViewAdapter.OnItemClickListener {

    @InjectPresenter
    ReposPresenter mReposPresenter;

    private RepoItemRecyclerViewAdapter mRepoItemRecyclerViewAdapter;

    public ReposFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repoitem_list, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        mRepoItemRecyclerViewAdapter = new RepoItemRecyclerViewAdapter();
        mRepoItemRecyclerViewAdapter.setOnListFragmentInteractionListener(this);

        recyclerView.setAdapter(mRepoItemRecyclerViewAdapter);

        return view;
    }

    @Override
    public void setReposData(List<Repo> items) {
        mRepoItemRecyclerViewAdapter.setRepoData(items);
    }

    @Override
    public void showProgress(boolean visible) {
        View view = getView();
        if (view!=null) {
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
            if (visible) {
                recyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClickItem(Repo item) {
        Activity activity = getActivity();
        if (activity instanceof OnListFragmentInteractionListener) {
            ((OnListFragmentInteractionListener) activity).onListFragmentInteraction(item);
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Repo item);
    }
}
