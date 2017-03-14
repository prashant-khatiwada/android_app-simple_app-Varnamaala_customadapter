package com.momobites.www.speaknepali;

/**
 * Created by prash on 3/11/2017.
 */

public class NepaliLetter {

    /** String resource IDs */
    private int mNepaliTranslationId;
    private int mAudioResourceId;
    private int mDevnagariiD;

    /** Image resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Letter object with no Image Handler
     * @param devnagariResourceId is the string resource ID for the word in Devnagari Script that the
     *                             user is already familiar with English
     * @param nepaliTranslationId is the string resource Id for the word in the Nepali language
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    public NepaliLetter(int nepaliTranslationId, int devnagariResourceId, int audioResourceId) {

        mNepaliTranslationId = nepaliTranslationId;
        mDevnagariiD = devnagariResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Create a new Letter object with Image
     * @param devnagariResourceId is the string resource ID for the word in Devnagari Script that the
     *                             user is already familiar with English
     * @param nepaliTranslationId is the string resource Id for the word in the Nepali language
     * @param imageResourceId is the drawable resource ID for the image associated with the word
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    public NepaliLetter(int nepaliTranslationId, int devnagariResourceId, int imageResourceId,
                        int audioResourceId) {

        mNepaliTranslationId = nepaliTranslationId;
        mDevnagariiD = devnagariResourceId;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    // Getters Method
    public int getNepaliTranslationId() {
        return mNepaliTranslationId;
    }
    public int getDevnagariId() {
        return mDevnagariiD;
    }
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    // Image specific
    public int getImageResourceId() {
        return mImageResourceId;
    }
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}
