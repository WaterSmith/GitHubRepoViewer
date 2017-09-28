package ua.watersmith.githubrepoviewer.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ua.watersmith.githubrepoviewer.R;
import ua.watersmith.githubrepoviewer.entities.Repo;


public class RepoFragment extends Fragment {
    private static final String ARG_PARAM = "repo";

    private Repo mRepo;

    public RepoFragment() {
        // Required empty public constructor
    }

    public static RepoFragment newInstance(Repo repo) {
        RepoFragment fragment = new RepoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM,repo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRepo = (Repo) getArguments().getSerializable(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_repo, container, false);
        TextView repoHeader = (TextView) view.findViewById(R.id.repo_header);
        repoHeader.setText(mRepo.getName());
        TextView repoDescription = (TextView) view.findViewById(R.id.repo_description);
        repoDescription.setText(mRepo.getDescription());

        Button btnCommits = (Button) view.findViewById(R.id.btn_commits);
        Button btnContributors = (Button) view.findViewById(R.id.btn_contributors);
        btnCommits.setOnClickListener(v -> {
            FragmentActivity activity = getActivity();
            if (activity instanceof OnRepoFragmentInteractionListener){
                ((OnRepoFragmentInteractionListener) activity).onCommitsClick(mRepo.getName());
            }
        });

        btnContributors.setOnClickListener(v -> {
            FragmentActivity activity = getActivity();
            if (activity instanceof OnRepoFragmentInteractionListener){
                ((OnRepoFragmentInteractionListener) activity).onContributorsClick(mRepo.getName());
            }
        });

        return view;
    }

    interface OnRepoFragmentInteractionListener {
        void onCommitsClick(String repoName);
        void onContributorsClick(String repoName);
    }

}
