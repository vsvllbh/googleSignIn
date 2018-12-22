package com.example.vsvll.googlesignin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends YouTubeBaseActivity {

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener youTubeInitializationResult;

    ImageView DisplayImage;
    TextView Name, Email;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        DisplayImage = findViewById(R.id.DisplayPic);
        Name = findViewById(R.id.Name);
        Email = findViewById(R.id.Email);
        youTubePlayerView = findViewById(R.id.youtubePlayer);

        FirebaseUser user = mAuth.getCurrentUser();
        Name.setText(user.getDisplayName());
        Email.setText(user.getEmail());

        youTubeInitializationResult = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo("U1clv_PBqxg");

            }


            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, MainActivity.class));
        }

    }

    public void play(View view) {

        youTubePlayerView.initialize("AIzaSyC1zuY8lLZ3xDjGvrbN7SNcWEJxJEE1YiI", youTubeInitializationResult);

    }
}
