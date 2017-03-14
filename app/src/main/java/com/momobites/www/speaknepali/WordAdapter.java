package com.momobites.www.speaknepali;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by prash on 3/11/2017.
 */

public class WordAdapter extends ArrayAdapter<NepaliWord> {

    /**
     * Create a new {@link WordAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param words is the list of {@link NepaliWord}s to be displayed.
     */
    public WordAdapter(Context context, ArrayList<NepaliWord> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        // Get the {@link NepaliWord} object located at this position in the list
        NepaliWord currentWord = getItem(position);
        // Find the TextView in the list_item.xml layout with the corresponding TextView
        TextView nepaliTextView = (TextView) listItemView.findViewById(R.id.nepali_text_view);
        TextView devnagariTextView = (TextView) listItemView.findViewById(R.id.devnagari_text_view);
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);


        // Get the object and set Text/Image on it
        nepaliTextView.setText(currentWord.getNepaliTranslationId());
        devnagariTextView.setText(currentWord.getDevnagariId());
        defaultTextView.setText(currentWord.getDefaultTranslationId());

        // For Image
        // Check if an image is provided for this word or not
        if (currentWord.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentWord.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }

        // Return the whole list item
        return listItemView;
    }

}
