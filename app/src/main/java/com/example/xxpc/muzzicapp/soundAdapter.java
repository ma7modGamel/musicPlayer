package com.example.xxpc.muzzicapp;


import android.content.Context;
import android.content.Intent;
import android.media.CamcorderProfile;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class soundAdapter extends RecyclerView.Adapter<soundAdapter.MyHolder> {
    ArrayList<modelSound> modelSoundArrayList;
    Context context;

    public soundAdapter(ArrayList<modelSound> modelSoundArrayList, Context context) {
        this.modelSoundArrayList = modelSoundArrayList;
        this.context = context;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        modelSound sound = modelSoundArrayList.get(position);
        holder.tv_artist.setText(sound.getArtist());
        holder.tv_name_song.setText(sound.getNameSound());
    }

    @Override
    public int getItemCount() {
        return modelSoundArrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_name_song;
        TextView tv_artist;
        ImageButton imageButton;
        LinearLayout layout;
        Intent intent;

        public MyHolder(final View itemView) {
            super(itemView);
            tv_name_song = itemView.findViewById(R.id.name_song);
            tv_artist = itemView.findViewById(R.id.artist);
            imageButton = itemView.findViewById(R.id.img_show_menu);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(view.getContext(), TwoActivity.class);
                    int adapterPosition = getAdapterPosition();
                    modelSound sound = modelSoundArrayList.get(adapterPosition);
                    intent.putExtra("allinfo", sound);
                    view.getContext().startActivity(intent);
                }
            });

        }
    }
}
