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
import ua.watersmith.githubrepoviewer.entities.Contributor;
import ua.watersmith.githubrepoviewer.entities.Repo;
import ua.watersmith.githubrepoviewer.presentation.ContributorsPresenter;
import ua.watersmith.githubrepoviewer.presentation.ContributorsView;
import ua.watersmith.githubrepoviewer.ui.adapters.ContributorItemRecyclerViewAdapter;


public class ContributorsFragment extends MvpAppCompatFragment implements ContributorsView {
    @InjectPresenter
    ContributorsPresenter mCommitsPresenter;

    private ContributorItemRecyclerViewAdapter mContributorItemRecyclerViewAdapter;

    public ContributorsFragment() {
    }

    public static ContributorsFragment newInstance(String repoName) {
        ContributorsFragment fragment = new ContributorsFragment();
        Bundle args = new Bundle();
        args.putString("repoName",repoName);
        fragment.setArguments(args);
        return fragment;
    }

    @ProvidePresenter
    ContributorsPresenter provideContributorsPresenter() {
        String repoName = getArguments().getString("repoName");
        return new ContributorsPresenter(repoName);
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

        mContributorItemRecyclerViewAdapter = new ContributorItemRecyclerViewAdapter();

        recyclerView.setAdapter(mContributorItemRecyclerViewAdapter);

        return view;
    }

    @Override
    public void setContributorsData(List<Contributor> items) {
        mContributorItemRecyclerViewAdapter.setRepoData(items);
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
