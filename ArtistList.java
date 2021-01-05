package com.example.slip_admin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ArtistList extends ArrayAdapter {
    private Activity context;
    private List<Artist> artistList;

    public ArtistList(Activity context,List<Artist> artistList){
        super(context,R.layout.list_layout,artistList);
        this.context = context;
        this.artistList = artistList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);


        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewRating);
        TextView email = (TextView)listViewItem.findViewById(R.id.email);
        TextView address = (TextView)listViewItem.findViewById(R.id.address);
        TextView Cost = (TextView)listViewItem.findViewById(R.id.cost);
        TextView Time = (TextView) listViewItem.findViewById(R.id.time);

        Artist artist = artistList.get(position);
        textViewName.setText("Address: "+artist.getLocation());
        textViewGenre.setText("Request ID: "+artist.getRequestId());
        Cost.setText("Cost: "+ artist.getCost());
        Time.setText("Time Taken "+artist.getTime());


        address.setText("Coordinates: "+artist.getCoordinates());
        email.setText("Cars Count/: "+artist.getCars());


        return listViewItem;


    }
}
