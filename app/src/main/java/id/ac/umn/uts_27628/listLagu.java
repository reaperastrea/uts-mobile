package id.ac.umn.uts_27628;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class listLagu extends AppCompatActivity {
    RecyclerView rvDaftarLagu;
    SongAdapter mAdapter;
    LinkedList<SumberLagu> daftarLagu = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lagu);
        rvDaftarLagu = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new SongAdapter(this, daftarLagu);
        rvDaftarLagu.setAdapter(mAdapter);
        rvDaftarLagu.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        isiDaftarLagu();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.text_name) + "\n" + getString(R.string.text_id))
                .setTitle(getString(R.string.text_welcome))
                .setPositiveButton(getString(R.string.text_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { dialog.dismiss(); }
                });
        builder.create().show();
    }

    public void isiDaftarLagu(){
        daftarLagu.add(new SumberLagu("Atlantico Blue", "Various Artist",
                "android.resource://" + getPackageName() + "/" +  R.raw.atlantico_blue));
        daftarLagu.add(new SumberLagu("Centimeter", "Various Artist",
                "android.resource://" + getPackageName() + "/" +  R.raw.centimeter));
        daftarLagu.add(new SumberLagu("Doll Cage", "Various Artist",
                "android.resource://" + getPackageName() + "/" +  R.raw.doll_cage));
        daftarLagu.add(new SumberLagu("Hot Limit", "Various Artist",
                "android.resource://" + getPackageName() + "/" +  R.raw.hot_limit));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.text_menu1:
                Intent gotoProfile = new Intent(listLagu.this, Profile.class);
                startActivity(gotoProfile);
                break;

            case R.id.text_menu2:
                Intent gotoMain = new Intent(listLagu.this, MainActivity.class);
                startActivity(gotoMain);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}