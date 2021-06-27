package com.nguliktime.learnrealm;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NoteListActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    NoteListAdapter noteListAdapter;
    List<NoteModel> noteModels;
    FloatingActionButton FAB;
    TextView tvnodata;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Toast.makeText(this, "mlebu mrene", Toast.LENGTH_SHORT).show();
//        mahasiswaAdapter.notifyDataSetChanged();
        show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        tvnodata = findViewById(R.id.tvnodata);
        FAB = findViewById(R.id.FAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteListActivity.this,
                        MainActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        noteModels = new ArrayList<>();

        noteModels = realmHelper.getAllMahasiswa();

        show();
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        mahasiswaAdapter.notifyDataSetChanged();
//        show();
//    }

    public void show() {
        if (noteModels.size() > 0) {
            noteListAdapter = new NoteListAdapter(this, noteModels, new NoteListAdapter.Callback() {
                @Override
                public void onClick(int position) {
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("id", noteModels.get(position).getId().toString());
                    intent.putExtra("name", noteModels.get(position).getName().toString());
                    intent.putExtra("note", noteModels.get(position).getDetailnote());
//                    v.getContext().startActivity(intent);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            });
            recyclerView.setAdapter(noteListAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            tvnodata.setVisibility(View.GONE);
            noteListAdapter.notifyDataSetChanged();
        } else {
            recyclerView.setVisibility(View.GONE);
            tvnodata.setVisibility(View.VISIBLE);
        }
    }
}
