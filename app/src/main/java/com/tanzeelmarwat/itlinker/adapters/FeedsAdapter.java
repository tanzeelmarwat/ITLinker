package com.tanzeelmarwat.itlinker.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tanzeelmarwat.itlinker.R;
import com.tanzeelmarwat.itlinker.listeners.OnListItemClickListener;
import com.tanzeelmarwat.itlinker.models.Feed;
import com.tanzeelmarwat.itlinker.network.API;
import com.tanzeelmarwat.itlinker.utils.Utility;

import java.util.HashMap;

public class FeedsAdapter extends RecyclerView
        .Adapter<FeedsAdapter
        .DataObjectHolder> {
    String TAG = "FeedsAdapter";
    private Context mContext;
    private HashMap<Integer, Feed> feeds;
    private OnListItemClickListener onMenuItemClickListener;


    public FeedsAdapter(Context mContext, HashMap<Integer, Feed> feeds, OnListItemClickListener onMenuItemClickListener) {
        this.mContext = mContext;
        this.feeds = feeds;
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView tvName, tvFeed, tvLike, tvComment;
        ImageView ivProfile, ivFeed;
        LinearLayout llItem;

        public DataObjectHolder(View itemView) {
            super(itemView);

            llItem = itemView.findViewById(R.id.llItem);
            tvName = itemView.findViewById(R.id.tvName);
            tvFeed = itemView.findViewById(R.id.tvFeed);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            ivFeed = itemView.findViewById(R.id.ivFeed);
            tvLike = itemView.findViewById(R.id.tvLike);
            tvComment = itemView.findViewById(R.id.tvComment);

            tvLike.setTypeface(Utility.applyFontAwesome(mContext));
            tvComment.setTypeface(Utility.applyFontAwesome(mContext));

            tvLike.setOnClickListener(this);
            tvComment.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tvLike :
                    case R.id.tvComment :
                        onMenuItemClickListener.onViewClick(view);
                    break;

                default:
                    onMenuItemClickListener.onListItemClick(getAdapterPosition());
                    break;
            }
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        Feed feed = feeds.get(position);
        holder.tvFeed.setText(feed.getText());
        holder.tvName.setText(feed.getUserName());
        Picasso.get().load(API.IMAGE_URL + feed.getUserImage())
                .placeholder(R.mipmap.ic_avatar)
                .error(R.mipmap.ic_avatar)
                .into(holder.ivProfile);
        if(feed.getFeedImage() != null && !feed.getFeedImage().equalsIgnoreCase("")) {
            holder.ivFeed.setVisibility(View.VISIBLE);
            Picasso.get().load(API.IMAGE_URL + feed.getFeedImage())
                    .placeholder(R.mipmap.placeholder)
                    .error(R.mipmap.placeholder)
                    .into(holder.ivFeed);
        } else {
            holder.ivFeed.setVisibility(View.GONE);
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        float marginInDP = 20f;
        int marginInPx = Utility.convertDpToPixel(marginInDP);
        if(position < (getItemCount() - 1)) {
            layoutParams.setMargins(0, marginInPx,0,0);
            holder.llItem.setLayoutParams(layoutParams);
        } else {
            layoutParams.setMargins(0, marginInPx,0, marginInPx);
            holder.llItem.setLayoutParams(layoutParams);
        }
    }


    @Override
    public int getItemCount() {
        return feeds.size();
    }
}
