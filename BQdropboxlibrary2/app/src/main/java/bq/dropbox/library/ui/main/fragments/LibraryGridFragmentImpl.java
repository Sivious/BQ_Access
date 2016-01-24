package bq.dropbox.library.ui.main.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import bq.dropbox.library.R;
import bq.dropbox.library.model.LibraryItem;
import bq.dropbox.library.ui.main.fragments.elements.CustomGrid;

/**
 * Created by Javier on 24/01/2016.
 */
public class LibraryGridFragmentImpl extends Fragment implements LibraryGridFragment {
    private ArrayList<LibraryItem> items;

    private GridView list;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<Integer> imageId = new ArrayList<>();

    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.library_grid_fragment, null);
        setGrid();

        return view;
    }

    private void setGrid() {
        CustomGrid adapter = new CustomGrid(this.getActivity().getApplicationContext(), this.names, this.dates, this.imageId);

        list = (GridView) view.findViewById(R.id.library_grid_grid);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getContext(), "You Clicked at " + names.get(+position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void setFileList(ArrayList<LibraryItem> items) {
        this.items = items;

        for (LibraryItem item : items) {
            names.add(item.getFileName());
            dates.add(item.getDataModified());
            imageId.add(R.drawable.icon_book);
        }
    }

}
