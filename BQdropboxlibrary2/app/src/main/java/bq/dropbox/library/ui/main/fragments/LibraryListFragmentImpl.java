package bq.dropbox.library.ui.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import bq.dropbox.library.R;
import bq.dropbox.library.model.LibraryItem;
import bq.dropbox.library.ui.main.MainActivity;
import bq.dropbox.library.ui.main.fragments.elements.CustomList;

/**
 * Created by Javier on 24/01/2016.
 */
public class LibraryListFragmentImpl extends Fragment implements LibraryListFragment {
    private ArrayList<LibraryItem> items;

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<Integer> imageId = new ArrayList<>();

    private View view;
    private ListView list;

    @Override
    public void setFileList(ArrayList<LibraryItem> items) {
        this.items = items;

        for (LibraryItem item : items) {
            names.add(item.getFileName());
            dates.add(item.getDataModified());
            imageId.add(R.drawable.icon_book);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.library_list_fragment, null);
        setList();

        return view;
    }

    private void setList() {
        CustomList adapter = new CustomList(this.getActivity().getApplicationContext(), this.names, this.dates, this.imageId);

        list = (ListView) view.findViewById(R.id.library_list_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ((MainActivity) getActivity()).showEpubInfo(items.get(position));
            }
        });
    }
}
