package bq.dropbox.library.ui.librarygrid.elements;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bq.dropbox.library.R;

/**
 * Created by Javier on 23/01/2016.
 */
public class CustomGrid extends BaseAdapter {
    private final Activity context;
    private final ArrayList<String> names;
    private final ArrayList<String> dates;
    private final ArrayList<Integer> images;

    public CustomGrid(Activity context, ArrayList<String> names, ArrayList<String> dates, ArrayList<Integer> images) {
        this.context = context;
        this.names = names;
        this.dates = dates;
        this.images = images;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.library_grid, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.grid_firstLine);
        TextView txtSubTitle = (TextView) rowView.findViewById(R.id.grid_secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.grid_icon);

        txtTitle.setText(names.get(position));
        txtSubTitle.setText(dates.get(position));
        imageView.setImageResource(images.get(position));
        return rowView;
    }
}
