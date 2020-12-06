package com.android.database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.activity.R;
import com.android.model.myPlaylist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class myPlaylistAdapter extends RecyclerView.Adapter<myPlaylistAdapter.ViewHolder> implements Filterable {

    String date;
    List<myPlaylist> myPlaylistsList;
    List<myPlaylist> myPlaylistsListAll;

    public myPlaylistAdapter(List<myPlaylist> myPlaylists, String date){
        this.myPlaylistsList = myPlaylists;
        this.myPlaylistsListAll = new ArrayList<myPlaylist> (myPlaylists);
        this.date = date;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.playlist_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.playlist.setText(myPlaylistsList.get(position).getPlaylist());
    }

    public String getDate(String date){
        return date;
    }

    public int getItemCount() {
        return myPlaylistsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<myPlaylist> filteredList = new ArrayList<>();

            for(myPlaylist myplaylist : myPlaylistsListAll){
                if (myplaylist.getDate().equals(date)){
                    filteredList.add(myplaylist);
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            myPlaylistsList.clear();
            myPlaylistsList.addAll((Collection<? extends myPlaylist>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView playlist;
        ViewHolder(View itemView) {
            super(itemView);
            playlist = itemView.findViewById(R.id.playlist);
        }
    }

}
