package com.nguliktime.learnrealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etnamenote, etdetailnote;
    String name, detail;
    Integer id;
    Button btn_ubah, btn_hapus;
    RealmHelper realmHelper;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set up
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        // Inisialisasi
        etnamenote = findViewById(R.id.etnamenote);
        etdetailnote = findViewById(R.id.etdetailnote);
        btn_ubah = findViewById(R.id.btnUpdate);
        btn_hapus = findViewById(R.id.btnHapus);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        name = getIntent().getStringExtra("name");
        detail = getIntent().getStringExtra("note");

        etnamenote.setText(name);
        etdetailnote.setText(detail);

        btn_hapus.setOnClickListener(this);
        btn_ubah.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_ubah){
            realmHelper.update(id, etnamenote.getText().toString(), etdetailnote.getText().toString());
            Toast.makeText(DetailActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
            etnamenote.setText("");
            etdetailnote.setText("");
//            finish();
            Intent intent = getIntent();
//        intent.putExtra("key", "restart");
            setResult(RESULT_OK, intent);
            finish();
        }else if (v == btn_hapus) {
            realmHelper.delete(id);
            Toast.makeText(DetailActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
//        intent.putExtra("key", "restart");
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}