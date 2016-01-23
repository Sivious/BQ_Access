package bq.dropbox.library.ui.librarylist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import bq.dropbox.library.R;
import bq.dropbox.library.model.LibraryItem;
import bq.dropbox.library.ui.librarylist.elements.CustomList;
import bq.dropbox.library.ui.librarylist.presenter.LibraryListPresenter;
import bq.dropbox.library.ui.librarylist.presenter.LibraryListPresenterImpl;

/**
 * Created by Javier on 23/01/2016.
 */
public class LibraryListActivity extends Activity implements LibraryListView {
    private LibraryListPresenter presenter;
    private ArrayList<LibraryItem> items;

    ListView list;
    ArrayList<String> names;
    ArrayList<String> dates;
    ArrayList<Integer> imageId;


    @Override
    public void setFileList(ArrayList<LibraryItem> items) {
        this.items = items;

        for (LibraryItem item : items) {
            names.add(item.getFileName());
            dates.add(item.getDataModified());
            imageId.add(R.drawable.icon_book);
        }

        setList();
    }

    private void setList() {
        CustomList adapter = new CustomList(LibraryListActivity.this, this.names, this.dates, this.imageId);

        list = (ListView) findViewById(R.id.library_list_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(LibraryListActivity.this, "You Clicked at " + names.get(+position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();

        setContentView(R.layout.library_list_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getFiles();
    }

    private void setView() {
        presenter = new LibraryListPresenterImpl(getApplicationContext(), this);
        names = new ArrayList<>();
        dates = new ArrayList<>();
        imageId = new ArrayList<>();
    }
}
