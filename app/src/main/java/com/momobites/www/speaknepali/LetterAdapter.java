package com.momobites.www.speaknepali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by prash on 3/11/2017.
 */

public class LetterAdapter extends ArrayAdapter<NepaliLetter> {

    /**
     * Create a new {@link LetterAdapter} object.
     *  @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param letters is the list of {@link NepaliLetter}s to be displayed.
     */
    public LetterAdapter(Context context, ArrayList<NepaliLetter> letters) {
        super(context, 0, letters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_letters, parent, false);
        }

        // Get the {@link NepaliLetter} object located at this position in the list
        NepaliLetter currentWord = getItem(position);
        // Find the TextView in the list_item.xml layout with the corresponding TextView
        TextView nepaliTextView = (TextView) listItemView.findViewById(R.id.nepali_text_view);
        TextView devnagariTextView = (TextView) listItemView.findViewById(R.id.devnagari_text_view);
        Button exampleButton = (Button) listItemView.findViewById(R.id.button_example);

        // Get the object and set Text/Image on it
        nepaliTextView.setText(currentWord.getNepaliTranslationId());
        devnagariTextView.setText(currentWord.getDevnagariId());


        // Return the whole list item
        return listItemView;
    }

}
