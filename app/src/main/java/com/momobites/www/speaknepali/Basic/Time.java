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

public class Time extends AppCompatActivity {

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
        words.add(new NepaliWord(R.string.time_today,
                R.string.nepali_time_today,
                R.string.devnagari_time_today,
                R.raw.time_today));
        words.add(new NepaliWord(R.string.time_tomorrow,
                R.string.nepali_time_tomorrow,
                R.string.devnagari_time_tomorrow,
                R.raw.time_tomorrow));
        words.add(new NepaliWord(R.string.time_yesterday,
                R.string.nepali_time_yesterday,
                R.string.devnagari_time_yesterday,
                R.raw.time_yesterday));
        words.add(new NepaliWord(R.string.time_morning,
                R.string.nepali_time_morning,
                R.string.devnagari_time_morning,
                R.raw.time_morning));
        words.add(new NepaliWord(R.string.time_afternoon,
                R.string.nepali_time_afternoon,
                R.string.devnagari_time_afternoon,
                R.raw.time_afternoon));
        words.add(new NepaliWord(R.string.time_night,
                R.string.nepali_time_night,
                R.string.devnagari_time_night,
                R.raw.time_night));
        words.add(new NepaliWord(R.string.time_week,
                R.string.nepali_time_week,
                R.string.devnagari_time_week,
                R.raw.time_week));
        words.add(new NepaliWord(R.string.time_day,
                R.string.nepali_time_day,
                R.string.devnagari_time_day,
                R.raw.time_day));
        words.add(new NepaliWord(R.string.time_month,
                R.string.nepali_time_month,
                R.string.devnagari_time_month,
                R.raw.time_month));
        words.add(new NepaliWord(R.string.time_year,
                R.string.nepali_time_year,
                R.string.devnagari_time_year,
                R.raw.time_year));
        // Weekdays
        words.add(new NepaliWord(R.string.time_sunday,
                R.string.nepali_time_sunday,
                R.string.devnagari_time_sunday,
                R.raw.time_sunday));
        words.add(new NepaliWord(R.string.time_monday,
                R.string.nepali_time_monday,
                R.string.devnagari_time_monday,
                R.raw.time_monday));
        words.add(new NepaliWord(R.string.time_tuesday,
                R.string.nepali_time_tuesday,
                R.string.devnagari_time_tuesday,
                R.raw.number_one));
        words.add(new NepaliWord(R.string.time_wednesday,
                R.string.nepali_time_wednesday,
                R.string.devnagari_time_wednesday,
                R.raw.time_today));
        words.add(new NepaliWord(R.string.time_thursday,
                R.string.nepali_time_thursday,
                R.string.devnagari_time_thursday,
                R.raw.time_today));
        words.add(new NepaliWord(R.string.time_friday,
                R.string.nepali_time_friday,
                R.string.devnagari_time_friday,
                R.raw.time_today));
        words.add(new NepaliWord(R.string.time_saturday,
                R.string.nepali_time_saturday,
                R.string.devnagari_time_saturday,
                R.raw.time_today));

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
                    mMediaPlayer = MediaPlayer.create(Time.this, word.getAudioResourceId());
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
