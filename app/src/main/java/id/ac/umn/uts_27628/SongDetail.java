package id.ac.umn.uts_27628;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class SongDetail extends AppCompatActivity {
    private TextView etJudul;
    private ImageButton skipPrevious, playpause, skipNext;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private int posisi;
    private ArrayList<SumberLagu> listSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_song);
        etJudul = (TextView) findViewById(R.id.etJudul);
        skipNext = findViewById(R.id.skip_Next);
        skipPrevious = findViewById(R.id.skip_Previous);
        playpause = findViewById(R.id.playpause);
        seekBar = findViewById(R.id.seekBar);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        listSong = (ArrayList<SumberLagu>) bundle.getSerializable("DetilLagu");
        posisi = (int) bundle.getSerializable("Position");
        playpause.setBackgroundResource(R.drawable.ic_baseline_pause_24);

        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    playpause.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                    seekBar.removeCallbacks(runnable);
                } else {
                    mediaPlayer.start();
                    playpause.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                    songSeekBar();
                }
            }
        });

        skipNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posisi ++;
                if(posisi > listSong.size() - 1){
                    posisi --;
                } else {
                    songControl();
                }
            }
        });

        skipPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posisi --;
                if(posisi < 0){
                    posisi ++;
                } else {
                    songControl();
                }
            }
        });

        songControl();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){ mediaPlayer.seekTo(progress); }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    private void songControl(){
        try {
            mediaPlayer.release();
        } catch (Exception e){
            Log.i("wat", "dis");
        }
        SumberLagu arrSong = listSong.get(posisi);
        etJudul.setText(arrSong.getJudul());
        mediaPlayer = MediaPlayer.create(this, Uri.parse(arrSong.getUri()));
        playpause.setBackgroundResource(R.drawable.ic_baseline_pause_24);
        mediaPlayer.start();
        seekBar.setMax(mediaPlayer.getDuration());
        songSeekBar();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mediaPlayer.stop();
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void songSeekBar(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        seekBar.postDelayed(runnable, 100);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            songSeekBar();
        }
    };
}
