package ua.watersmith.githubrepoviewer.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ua.watersmith.githubrepoviewer.R;
import ua.watersmith.githubrepoviewer.entities.Contributor;

public class ContributorItemRecyclerViewAdapter extends RecyclerView.Adapter<ContributorItemRecyclerViewAdapter.ViewHolder> {

    private List<Contributor> mValues;

    public void setRepoData(List<Contributor> items){
        mValues = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contributoritem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mLogin.setText(holder.mItem.getLogin());

        Picasso.with(holder.itemView.getContext())
                .load(holder.mItem.getAvatarUrl())
                .into(holder.mAvatar);

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
        public TextView mLogin;
        public ImageView mAvatar;
        public Contributor mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mLogin = (TextView) view.findViewById(R.id.tv_contributor);
            mAvatar = (ImageView) view.findViewById(R.id.imageView);
        }

    }
}