package bq.dropbox.library.ui.librarygrid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import bq.dropbox.library.R;
import bq.dropbox.library.model.LibraryItem;
import bq.dropbox.library.ui.librarygrid.elements.CustomGrid;
import bq.dropbox.library.ui.librarygrid.presenter.LibraryGridPresenter;
import bq.dropbox.library.ui.librarygrid.presenter.LibraryGridPresenterImpl;

/**
 * Created by Javier on 23/01/2016.
 */
public class LibraryGridActivity extends Activity implements LibraryGridView {
    private ArrayList<LibraryItem> items;
    private LibraryGridPresenter presenter;

    GridView list;
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
        CustomGrid adapter = new CustomGrid(LibraryGridActivity.this, this.names, this.dates, this.imageId);

        list = (GridView) findViewById(R.id.library_grid_grid);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(LibraryGridActivity.this, "You Clicked at " + names.get(+position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        setContentView(R.layout.library_grid_activity);
    }

    private void setView() {
        names = new ArrayList<>();
        dates = new ArrayList<>();
        imageId = new ArrayList<>();

        presenter = new LibraryGridPresenterImpl(getApplicationContext(), this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getFiles();
    }
}
