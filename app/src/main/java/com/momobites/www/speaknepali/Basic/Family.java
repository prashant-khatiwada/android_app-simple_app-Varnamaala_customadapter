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

public class Family extends AppCompatActivity {
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
        words.add(new NepaliWord(R.string.family_father, R.string.nepali_family_father, R.string.devnagari_family_father,
                R.drawable.family_father, R.raw.family_father));
        words.add(new NepaliWord(R.string.family_mother, R.string.nepali_family_mother, R.string.devnagari_family_mother,
                R.drawable.family_mother, R.raw.family_mother));
        words.add(new NepaliWord(R.string.family_husband, R.string.nepali_family_husband, R.string.devnagari_family_husband,
                R.drawable.family_husband, R.raw.family_husband));
        words.add(new NepaliWord(R.string.family_wife, R.string.family_wife, R.string.devnagari_family_wife,
                R.drawable.family_wife, R.raw.family_wife));
        words.add(new NepaliWord(R.string.family_son, R.string.nepali_family_son, R.string.devnagari_family_son,
                R.drawable.family_son, R.raw.family_son));
        words.add(new NepaliWord(R.string.family_daughter, R.string.nepali_family_daughter, R.string.devnagari_family_daughter,
                R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new NepaliWord(R.string.family_older_brother, R.string.nepali_family_older_brother, R.string.devnagari_family_older_brother,
                R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new NepaliWord(R.string.family_older_sister, R.string.nepali_family_older_sister, R.string.devnagari_family_older_sister,
                R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new NepaliWord(R.string.family_younger_brother, R.string.nepali_family_younger_brother, R.string.devnagari_family_younger_brother,
                R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new NepaliWord(R.string.family_younger_sister, R.string.nepali_family_younger_sister, R.string.devnagari_family_younger_sister,
                R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new NepaliWord(R.string.family_grandfather, R.string.nepali_family_grandfather, R.string.devnagari_family_grandfather,
                R.drawable.family_grandfather, R.raw.family_grandfather));
        words.add(new NepaliWord(R.string.family_grandmother, R.string.nepali_family_grandmother, R.string.devnagari_family_grandmother,
                R.drawable.family_grandmother, R.raw.number_ten));

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
                    mMediaPlayer = MediaPlayer.create(Family.this, word.getAudioResourceId());
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
