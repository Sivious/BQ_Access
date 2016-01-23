package bq.dropbox.library.ui.librarylist.elements;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bq.dropbox.library.R;

/**
 * Created by Javier on 23/01/2016.
 */
public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> names;
    private final ArrayList<String> dates;
    private final ArrayList<Integer> images;

    public CustomList(Activity context, ArrayList<String> names, ArrayList<String> dates, ArrayList<Integer> images) {
        super(context, R.layout.library_list, names);
        this.context = context;
        this.names = names;
        this.dates = dates;
        this.images = images;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.library_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.firstLine);
        TextView txtSubTitle = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(names.get(position));
        txtSubTitle.setText(dates.get(position));
        imageView.setImageResource(images.get(position));
        return rowView;
    }
}
