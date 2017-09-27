package ua.watersmith.githubrepoviewer.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ua.watersmith.githubrepoviewer.R;
import ua.watersmith.githubrepoviewer.entities.Repo;

public class RepoItemRecyclerViewAdapter extends RecyclerView.Adapter<RepoItemRecyclerViewAdapter.ViewHolder> {

    private List<Repo> mValues;
    private OnItemClickListener mListener;

    public void setOnListFragmentInteractionListener(OnItemClickListener listener){
        mListener = listener;
    }

    public void setRepoData(List<Repo> items){
        mValues = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_repoitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mRepoHeader.setText(mValues.get(position).getName());
        holder.mRepoStars.setText("Stars:"+mValues.get(position).getStargazersCount());
        holder.mRepoForks.setText("Forks:"+mValues.get(position).getForksCount());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClickItem(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues==null) {
            return 0;
        }
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  View mView;
        public  TextView mRepoHeader;
        public  TextView mRepoStars;
        public  TextView mRepoForks;
        public Repo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mRepoHeader = (TextView) view.findViewById(R.id.repo_header);
            mRepoStars = (TextView) view.findViewById(R.id.repo_stars);
            mRepoForks = (TextView) view.findViewById(R.id.repo_forks);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mRepoHeader.getText() + "'";
        }
    }

    public interface OnItemClickListener {
        void onClickItem(Repo item);
    }
}
