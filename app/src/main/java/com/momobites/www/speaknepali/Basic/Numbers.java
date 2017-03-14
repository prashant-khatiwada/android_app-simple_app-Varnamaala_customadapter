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

public class Numbers extends AppCompatActivity {
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
        words.add(new NepaliWord(R.string.number_one, R.string.nepali_number_one, R.string.devnagari_number_one,
                R.drawable.number_one, R.raw.number_one));
        words.add(new NepaliWord(R.string.number_two, R.string.nepali_number_two, R.string.devnagari_number_two,
                R.drawable.number_two, R.raw.number_two));
        words.add(new NepaliWord(R.string.number_three, R.string.nepali_number_three, R.string.devnagari_number_three,
                R.drawable.number_three, R.raw.number_three));
        words.add(new NepaliWord(R.string.number_four, R.string.nepali_number_four, R.string.devnagari_number_four,
                R.drawable.number_four, R.raw.number_four));
        words.add(new NepaliWord(R.string.number_five, R.string.nepali_number_five, R.string.devnagari_number_five,
                R.drawable.number_five, R.raw.number_five));
        words.add(new NepaliWord(R.string.number_six, R.string.nepali_number_six, R.string.devnagari_number_six,
                R.drawable.number_six, R.raw.number_six));
        words.add(new NepaliWord(R.string.number_seven, R.string.nepali_number_seven, R.string.devnagari_number_seven,
                R.drawable.number_seven, R.raw.number_seven));
        words.add(new NepaliWord(R.string.number_eight, R.string.nepali_number_eight, R.string.devnagari_number_eight,
                R.drawable.number_eight, R.raw.number_eight));
        words.add(new NepaliWord(R.string.number_nine, R.string.nepali_number_nine, R.string.devnagari_number_nine,
                R.drawable.number_nine, R.raw.number_nine));
        words.add(new NepaliWord(R.string.number_ten, R.string.nepali_number_ten, R.string.devnagari_number_ten,
                R.drawable.number_ten, R.raw.number_ten));

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
                    mMediaPlayer = MediaPlayer.create(Numbers.this, word.getAudioResourceId());
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
