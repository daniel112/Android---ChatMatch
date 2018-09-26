package com.chatmatch.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.chatmatch.R;

public class VideoActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    String videoPath;
    VideoView videoView;
    int currentPosition = 0;

    private static final String PLAYBACK_TIME = "play_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }

        // Handle Video
        videoView = findViewById(R.id.videoViewHowItWorks);
        videoPath = "android.resource://" + getPackageName() + "/" + R.raw.howitworks;
        videoView.setVideoURI(Uri.parse(videoPath));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.setOnCompletionListener(this);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (currentPosition > 0) {
                    videoView.seekTo(currentPosition);
                } else {
                    videoView.seekTo(1);
                }
                videoView.start();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ReleasePlayer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        videoView.seekTo(0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PLAYBACK_TIME, videoView.getCurrentPosition());
    }

    public void ReleasePlayer() {
        videoView.stopPlayback();
    }
}
