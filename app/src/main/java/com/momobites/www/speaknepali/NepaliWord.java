package com.momobites.www.speaknepali;

/**
 * Created by prash on 3/11/2017.
 */

public class NepaliWord {

    /** String resource IDs */
    private int mDefaultTranslationId;
    private int mNepaliTranslationId;
    private int mAudioResourceId;
    private int mDevnagariiD;

    /** Image resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object with no Image Handler
     * @param defaultTranslationId is the string resource ID for the word in a language that the
     *                             user is already familiar with English
     * @param nepaliTranslationId is the string resource Id for the word in the Nepali language
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    public NepaliWord(int defaultTranslationId, int nepaliTranslationId, int devnagariResourceId, int audioResourceId) {
        mDefaultTranslationId = defaultTranslationId;
        mNepaliTranslationId = nepaliTranslationId;
        mDevnagariiD = devnagariResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Create a new Word object with Image
     * @param defaultTranslationId is the string resource ID for the word in a language that the
     *                             user is already familiar with English
     * @param nepaliTranslationId is the string resource Id for the word in the Nepali language
     * @param imageResourceId is the drawable resource ID for the image associated with the word
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    public NepaliWord(int defaultTranslationId, int nepaliTranslationId, int devnagariResourceId, int imageResourceId,
                int audioResourceId) {
        mDefaultTranslationId = defaultTranslationId;
        mNepaliTranslationId = nepaliTranslationId;
        mDevnagariiD = devnagariResourceId;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    // Getters Method
    public int getDefaultTranslationId() {
        return mDefaultTranslationId;
    }
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
