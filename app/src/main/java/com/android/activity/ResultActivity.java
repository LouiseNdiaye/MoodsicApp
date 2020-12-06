package com.android.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.database.PlaylistRepository;
import com.android.database.PlaylistViewModel;
import com.android.database.myDatabase;
import com.android.model.Playlist;
import com.android.model.Results;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import com.android.model.myPlaylist;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Image;
import com.spotify.protocol.types.Track;

import java.io.IOException;

public class ResultActivity extends AppCompatActivity {

    private PlaylistViewModel playlistViewModel;
    private TextView textView, artist, music;
    private ImageView image;
    private Button spotify;
    private static final String CLIENT_ID = "fbb3196823cc41b1ae2d5ebcbad58eac";
    private static final String REDIRECT_URI = "https://com.android.mooday/callback";
    private SpotifyAppRemote mSpotifyAppRemote;
    private Results answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        spotify = findViewById(R.id.spotify);
        spotify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i;
                try {
                    i = getPackageManager().getLaunchIntentForPackage("com.spotify.music");
                    if (i == null)
                        throw new PackageManager.NameNotFoundException();
                    i.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(i);
                    ResultActivity.this.finish();
                } catch (PackageManager.NameNotFoundException e) {
                    Log.d("TEST", "Impossible to open Spotify");
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        try {
                            connected();
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("ResultActivity", throwable.getMessage(), throwable);
                    }
                });
    }

    private void connected() throws IOException, JSONException {

        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("ResultActivity", track.name + " by " + track.artist.name);
                        artist = findViewById(R.id.artist);
                        artist.setText(track.name);
                        music = findViewById(R.id.music);
                        music.setText(track.artist.name);
                        image = findViewById(R.id.image);
                        mSpotifyAppRemote
                                .getImagesApi()
                                .getImage(track.imageUri, Image.Dimension.LARGE)
                                .setResultCallback(
                                        bitmap -> {
                                            image.setImageBitmap(bitmap);
                                        });
                    }
                });

        Intent i = getIntent();
        answer = (Results) i.getSerializableExtra("result");
        textView = findViewById(R.id.namePlaylist);

        Integer id = PlaylistRepository.getIDPlaylist(answer);
        playlistViewModel = new ViewModelProvider(this).get(PlaylistViewModel.class);
        playlistViewModel.deleteAllPlaylists();

        playlistViewModel.insert(new Playlist(1, "Feel-Good Indie", "spotify:playlist:37i9dQZF1DX2sUQwD7tbmL"));
        playlistViewModel.insert(new Playlist(2, "La vie est belle", "spotify:playlist:37i9dQZF1DXdrln2UyZD7F"));
        playlistViewModel.insert(new Playlist(3, "Have a Great Day!", "spotify:playlist:37i9dQZF1DX2sUQwD7tbmL"));
        playlistViewModel.insert(new Playlist(4, "Garde la pêche","spotify:playlist:37i9dQZF1DX8685vIIepKh"));
        playlistViewModel.insert(new Playlist(5, "Feel-Good Classics", "spotify:playlist:37i9dQZF1DWVinJBuv0P4z"));
        playlistViewModel.insert(new Playlist(6, "Feel-Good Indie", "spotify:playlist:37i9dQZF1DX2sUQwD7tbmL"));
        playlistViewModel.insert(new Playlist(7, "Flashback", "spotify:playlist:37i9dQZF1DWXncK9DGeLh7"));
        playlistViewModel.insert(new Playlist(8, "Rock Classics","spotify:playlist:37i9dQZF1DWXRqgorJj26U"));

        playlistViewModel.getAllPlaylists().observe(this, new Observer<List<Playlist>>() {
            @Override
            public void onChanged(List<Playlist> playlists) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date dateToday = new Date();
                String date = formatter.format(dateToday);
                if (!playlists.isEmpty()) {
                    for (Playlist n : playlists) {
                        if (n.getKey()==id){
                            textView.setText(n.getName());
                            mSpotifyAppRemote.getPlayerApi().play(n.getUrl());
                            myPlaylist history = new myPlaylist(null, date, n.getName());
                            myDatabase.getDatabase(getApplicationContext()).historyDAO().insert(history);
                        }
                    }
                } else {
                    textView.setText("Not available");
                }
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }


}