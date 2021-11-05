package com.example.addplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
VideoView vid;
//String url = "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_20mb.mp4";
//
//String fixedUrl = url.replaceAll(" ", "%20");

ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String videopath = Environment.getExternalStorageDirectory().getPath()+"/video/index.mp4";

        setContentView(R.layout.activity_main);
        vid = (VideoView)findViewById(R.id.video);
        pd = new ProgressDialog( MainActivity.this);
        pd.setMessage("Loading...");
        pd.show();
       // Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.index);
        //Uri uri = Uri.fromFile(videopath);
        //vid.setVideoURI(uri);
        vid.setVideoPath(videopath);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1 );
        }
        else {
            vid.start();
        }
        vid.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pd.dismiss();
                mp.setLooping(true);
            }
        });
    }
}