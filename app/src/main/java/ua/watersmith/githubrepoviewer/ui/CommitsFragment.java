package ua.watersmith.githubrepoviewer.ui;

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
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;


import ua.watersmith.githubrepoviewer.R;
import ua.watersmith.githubrepoviewer.entities.CommitInfo;
import ua.watersmith.githubrepoviewer.entities.Repo;
import ua.watersmith.githubrepoviewer.presentation.CommitsPresenter;
import ua.watersmith.githubrepoviewer.presentation.CommitsView;
import ua.watersmith.githubrepoviewer.ui.adapters.CommitItemRecyclerViewAdapter;


public class CommitsFragment extends MvpAppCompatFragment implements CommitsView {
    @InjectPresenter
    CommitsPresenter mCommitsPresenter;

    private CommitItemRecyclerViewAdapter mCommitItemRecyclerViewAdapter;

    public CommitsFragment() {
    }

    public static CommitsFragment newInstance(String repoName) {
        CommitsFragment fragment = new CommitsFragment();
        Bundle args = new Bundle();
        args.putString("repoName",repoName);
        fragment.setArguments(args);
        return fragment;
    }

    @ProvidePresenter
    CommitsPresenter provideCommitsPresenter() {
        String repoName = getArguments().getString("repoName");
        return new CommitsPresenter(repoName);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commititem_list, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        mCommitItemRecyclerViewAdapter = new CommitItemRecyclerViewAdapter();

        recyclerView.setAdapter(mCommitItemRecyclerViewAdapter);

        return view;
    }

    @Override
    public void setCommitsData(List<CommitInfo> items) {
        mCommitItemRecyclerViewAdapter.setRepoData(items);
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

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Repo item);
    }
}
