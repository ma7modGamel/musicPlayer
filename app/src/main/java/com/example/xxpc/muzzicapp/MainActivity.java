package com.example.xxpc.muzzicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleviewid);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        int[] soundArray = {R.raw.n, R.raw.nnn, R.raw.nnnn, R.raw.n, R.raw.nnn, R.raw.nnnn,R.raw.n, R.raw.nnn, R.raw.nnnn, R.raw.n, R.raw.nnn, R.raw.nnnn};
        String[] names = {"Song1", "Rock", "Music", "Rang", "Topas", "nnnn","Song1", "Rock", "Music", "Rang", "Topas", "nnnn"};
        String[] artistArray = {"Rian", "shakira", "Maichel", "Nanii", "Samoha", "zzz","Rian", "shakira", "Maichel", "Nanii", "Samoha", "zzz"};
        ArrayList<modelSound> modelSoundArrayList = new ArrayList<>();
        for (int i = 0; i < artistArray.length; i++) {
            modelSoundArrayList.add(new modelSound(soundArray[i], names[i], artistArray[i]));
        }
        recyclerView.setAdapter(new soundAdapter(modelSoundArrayList, this));


    }
}
