package com.momobites.www.speaknepali.Basic;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.momobites.www.speaknepali.NepaliWord;
import com.momobites.www.speaknepali.R;
import com.momobites.www.speaknepali.WordAdapter;

import java.util.ArrayList;

/**
 * Created by prash on 3/11/2017.
 */

public class Colors extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;
    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    // This listener gets triggered whenever the audio focus changes
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // When transient audio lapse - pause the app
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // When audio returns
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // When the audio is complete
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    // Runs when MediaPlayer is through running the audio
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<NepaliWord> words = new ArrayList<NepaliWord>();
        words.add(new NepaliWord(R.string.color_red, R.string.nepali_color_red, R.string.devnagari_color_red,
                R.drawable.color_red, R.raw.color_red));
        words.add(new NepaliWord(R.string.color_blue, R.string.nepali_color_blue, R.string.devnagari_color_blue,
                R.drawable.color_blue, R.raw.color_blue));
        words.add(new NepaliWord(R.string.color_green, R.string.nepali_color_green, R.string.devnagari_color_green,
                R.drawable.color_green, R.raw.color_green));
        words.add(new NepaliWord(R.string.color_black, R.string.nepali_color_black, R.string.devnagari_color_black,
                R.drawable.color_black, R.raw.color_black));
        words.add(new NepaliWord(R.string.color_white, R.string.nepali_color_white, R.string.devnagari_color_white,
                R.drawable.color_white, R.raw.color_white));
        words.add(new NepaliWord(R.string.color_gray, R.string.nepali_color_gray, R.string.devnagari_color_gray,
                R.drawable.color_gray, R.raw.color_gray));
        words.add(new NepaliWord(R.string.color_brown, R.string.nepali_color_brown, R.string.devnagari_color_brown,
                R.drawable.color_brown, R.raw.color_brown));
        words.add(new NepaliWord(R.string.color_orange, R.string.nepali_color_orange, R.string.devnagari_color_orange,
                R.drawable.color_orange, R.raw.color_orange));
        words.add(new NepaliWord(R.string.color_yellow, R.string.nepali_color_yellow, R.string.devnagari_color_yellow,
                R.drawable.color_yellow, R.raw.color_yellow));
        words.add(new NepaliWord(R.string.color_purple, R.string.nepali_color_purple, R.string.devnagari_color_purple,
                R.drawable.color_purple, R.raw.color_purple));

        // Create WordAdapter and then link a color to it
        WordAdapter adapter = new WordAdapter(this, words);
        // Create ListView
        ListView listView = (ListView) findViewById(R.id.list);
        // Make the list use the adapter created above
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                // Get the  object at the given position the user clicked on
                NepaliWord word = words.get(position);
                // Request audio focus
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.
                    mMediaPlayer = MediaPlayer.create(Colors.this, word.getAudioResourceId());
                    // Start the audio file
                    mMediaPlayer.start();
                    // Setup a listener on the media player
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources.
        releaseMediaPlayer();
    }

    // Clean Media Player Resource
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }


}
