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

public class Phrases extends AppCompatActivity {

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
        words.add(new NepaliWord(R.string.phrase_where_are_you_going,
                R.string.nepali_phrase_where_are_you_going,
                R.string.devnagari_phrase_where_are_you_going,
                R.raw.number_one));
        words.add(new NepaliWord(R.string.phrase_what_is_your_name,
                R.string.nepali_phrase_what_is_your_name,
                R.string.devnagari_phrase_what_is_your_name,
                R.raw.number_one));
        words.add(new NepaliWord(R.string.phrase_my_name_is,
                R.string.nepali_phrase_my_name_is,
                R.string.devnagari_phrase_my_name_is,
                R.raw.number_one));
        words.add(new NepaliWord(R.string.phrase_how_are_you_feeling,
                R.string.nepali_phrase_how_are_you_feeling,
                R.string.devnagari_phrase_how_are_you_feeling,
                R.raw.number_one));
        words.add(new NepaliWord(R.string.phrase_im_feeling_good,
                R.string.nepali_phrase_im_feeling_good,
                R.string.devnagari_phrase_im_feeling_good,
                R.raw.number_one));
        words.add(new NepaliWord(R.string.phrase_are_you_coming,
                R.string.nepali_phrase_are_you_coming,
                R.string.devnagari_phrase_are_you_coming,
                R.raw.number_one));
        words.add(new NepaliWord(R.string.phrase_yes_im_coming,
                R.string.nepali_phrase_yes_im_coming,
                R.string.devnagari_phrase_yes_im_coming,
                R.raw.number_one));
        words.add(new NepaliWord(R.string.phrase_im_coming,
                R.string.nepali_phrase_im_coming,
                R.string.devnagari_phrase_im_coming,
                R.raw.number_one));
        words.add(new NepaliWord(R.string.phrase_lets_go,
                R.string.nepali_phrase_lets_go,
                R.string.devnagari_phrase_lets_go,
                R.raw.number_one));
        words.add(new NepaliWord(R.string.phrase_come_here,
                R.string.nepali_phrase_come_here,
                R.string.devnagari_phrase_come_here,
                R.raw.number_one));

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
                    mMediaPlayer = MediaPlayer.create(Phrases.this, word.getAudioResourceId());
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
