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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSimpan, btnTampil;
    EditText name, note;
    String sNama;
    String sNim;
    Realm realm;
    RealmHelper realmHelper;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = getIntent();
//        intent.putExtra("key", "restart");
        setResult(RESULT_OK, intent);
        finish();
    }

    NoteModel noteModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi
        btnSimpan = findViewById(R.id.btnSimpan);
        btnTampil = findViewById(R.id.btnTampil);
        name = findViewById(R.id.nim);
        note = findViewById(R.id.nama);

        //Set up Realm
        Realm.init(MainActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        btnSimpan.setOnClickListener(this);
        btnTampil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSimpan){
            sNim = name.getText().toString();
            sNama = note.getText().toString();

            if (!sNim.equals("") && !sNama.isEmpty()){
                noteModel = new NoteModel();
                noteModel.setName(sNim);
                noteModel.setDetailnote(sNama);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(noteModel);

                Toast.makeText(MainActivity.this, "Berhasil Disimpan!", Toast.LENGTH_SHORT).show();

                name.setText("");
                note.setText("");
                Intent intent = getIntent();
//        intent.putExtra("key", "restart");
                setResult(RESULT_OK, intent);
                finish();
            }else {
                Toast.makeText(MainActivity.this, "Terdapat inputan yang kosong", Toast.LENGTH_SHORT).show();
            }
        }else if (v == btnTampil){
//            startActivity(new Intent(MainActivity.this, MahasiswaActivity.class));
            Intent intent = getIntent();
//        intent.putExtra("key", "restart");
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
