package ua.watersmith.githubrepoviewer.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import ua.watersmith.githubrepoviewer.R;
import ua.watersmith.githubrepoviewer.entities.Repo;
import ua.watersmith.githubrepoviewer.presentation.ReposPresenter;
import ua.watersmith.githubrepoviewer.presentation.ReposView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ReposFragment extends MvpFragment implements ReposView, RepoItemRecyclerViewAdapter.OnItemClickListener {
    @InjectPresenter
    ReposPresenter mReposPresenter;

    private OnListFragmentInteractionListener mListener;
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
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
            if (mRepoItemRecyclerViewAdapter!=null) {
                mRepoItemRecyclerViewAdapter.setOnListFragmentInteractionListener(mListener);
            }
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        */
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
        ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.list);
        if (visible) {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClickItem(Repo item) {
        Context view = getView().getContext();
        if (view instanceof OnListFragmentInteractionListener) {
            ((OnListFragmentInteractionListener) view).onListFragmentInteraction(item);
        }
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
