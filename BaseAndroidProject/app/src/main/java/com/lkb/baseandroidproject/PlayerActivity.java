package com.lkb.baseandroidproject;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
public class PlayerActivity extends AppCompatActivity {
    private static final String ARG_VIDEO = "video";

    public static Intent createIntent(Context context, String videoUrl) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(ARG_VIDEO, videoUrl);
        return intent;
    }

    PlayerView mPlayerView;

    private SimpleExoPlayer mPlayer;
    private String mVideoUrl;

    private long mCurrentMillis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayerView = findViewById(R.id.full_screen_video);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loadArgs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startPlayer();
    }

    @Override
    protected void onPause() {
        release();
        super.onPause();
    }

    private void loadArgs() {
        if (getIntent() == null) {
            finish();
        }
        mVideoUrl = "assets:///heart_attack.mp3";
    }

    private void startPlayer() {
        if (mPlayer != null) {
            return;
        }
        mPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(this),
                new DefaultTrackSelector());
        mPlayerView.setPlayer(mPlayer);

        DefaultDataSourceFactory dataSourceFactory =
                new DefaultDataSourceFactory(this, Util.getUserAgent(this, "player"));
        ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mVideoUrl));

        boolean isResuming = mCurrentMillis != 0;
        mPlayer.prepare(extractorMediaSource, isResuming, false);
        mPlayer.setPlayWhenReady(true);
        if (isResuming) {
            mPlayer.seekTo(mCurrentMillis);
        }

    }

    private void release() {
        if (mPlayer == null) {
            return;
        }
        mCurrentMillis = mPlayer.getCurrentPosition();
        mPlayer.release();
        mPlayer = null;
    }
}
