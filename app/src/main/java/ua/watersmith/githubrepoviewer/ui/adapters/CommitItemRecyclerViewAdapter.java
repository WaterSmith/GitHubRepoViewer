package ua.watersmith.githubrepoviewer.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ua.watersmith.githubrepoviewer.R;
import ua.watersmith.githubrepoviewer.entities.Commit;
import ua.watersmith.githubrepoviewer.entities.CommitInfo;

public class CommitItemRecyclerViewAdapter extends RecyclerView.Adapter<CommitItemRecyclerViewAdapter.ViewHolder> {

    private List<CommitInfo> mValues;

    public void setRepoData(List<CommitInfo> items){
        mValues = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_commititem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position).getCommit();
        holder.mCommitDate.setText(holder.mItem.getCommitter().getDate());
        holder.mCommitMessage.setText(holder.mItem.getMessage());
    }

    @Override
    public int getItemCount() {
        if (mValues==null) {
            return 0;
        }
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mCommitDate;
        public TextView mCommitMessage;
        public Commit mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCommitDate = (TextView) view.findViewById(R.id.commit_date);
            mCommitMessage = (TextView) view.findViewById(R.id.commit_message);
        }

    }
}
