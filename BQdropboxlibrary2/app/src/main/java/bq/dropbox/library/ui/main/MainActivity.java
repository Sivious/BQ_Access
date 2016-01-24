package bq.dropbox.library.ui.main;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;

import bq.dropbox.library.R;
import bq.dropbox.library.model.LibraryItem;
import bq.dropbox.library.ui.main.fragments.EpubInfoFragmentImpl;
import bq.dropbox.library.ui.main.fragments.LibraryGridFragmentImpl;
import bq.dropbox.library.ui.main.fragments.LibraryListFragmentImpl;
import bq.dropbox.library.ui.main.presenter.MainPresenterImpl;

/**
 * Created by Javier on 24/01/2016.
 */
public class MainActivity extends FragmentActivity implements MainView {
    private ArrayList<LibraryItem> items;
    private FloatingActionButton libraryView;
    private boolean viewGrid = true;
    private MainPresenterImpl presenter;
    private FrameLayout gridLayout;
    private FrameLayout listLayout;
    private FrameLayout infoDetailLayout;


    @Override
    public void setFileList(ArrayList<LibraryItem> items) {
        Collections.sort(items);
        this.items = items;

        libraryView.setClickable(true);
        showGridFragment();
    }

    @Override
    public void showEpubInfo(LibraryItem item) {
        EpubInfoFragmentImpl fragment = new EpubInfoFragmentImpl();
        fragment.setPath(item.getPath());

        FragmentTransaction infoFragmentTransaction = getSupportFragmentManager().beginTransaction();

        infoDetailLayout.setVisibility(View.VISIBLE);

        if (infoFragmentTransaction == null) {
            infoFragmentTransaction.add(R.id.main_epub_detail, fragment);
        } else {
            infoFragmentTransaction.replace(R.id.main_epub_detail, fragment);
        }

        infoFragmentTransaction.commitAllowingStateLoss();

    }

    @Override
    public void hideEpubInfo() {
        if (infoDetailLayout != null) {
            infoDetailLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        setView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getFiles();
    }

    private void setView() {
        gridLayout = (FrameLayout) findViewById(R.id.main_grid);
        listLayout = (FrameLayout) findViewById(R.id.main_list);
        libraryView = (FloatingActionButton) findViewById(R.id.main_fab);
        infoDetailLayout = (FrameLayout) findViewById(R.id.main_epub_detail);

        libraryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewGrid = !viewGrid;

                if (viewGrid) {
                    libraryView.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dropboxBQMagenta)));
                    hideListFragment();
                    showGridFragment();

                } else {
                    libraryView.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dropboxBQOrange)));
                    hideGridFragment();
                    showListFragment();
                }

            }
        });

        libraryView.setClickable(false);

        presenter = new MainPresenterImpl(getApplicationContext(), this);
    }

    private void showListFragment() {
        LibraryListFragmentImpl fragment = new LibraryListFragmentImpl();
        fragment.setFileList(items);

        FragmentTransaction listFragmentTransaction = getSupportFragmentManager().beginTransaction();

        listLayout.setVisibility(View.VISIBLE);

        if (listFragmentTransaction == null) {
            listFragmentTransaction.add(R.id.main_list, fragment);
        } else {
            listFragmentTransaction.replace(R.id.main_list, fragment);
        }

        listFragmentTransaction.commitAllowingStateLoss();
    }

    private void hideListFragment() {
        if (listLayout != null) {
            listLayout.setVisibility(View.GONE);
        }
    }

    private void showGridFragment() {
        LibraryGridFragmentImpl fragment = new LibraryGridFragmentImpl();
        fragment.setFileList(items);

        FragmentTransaction gridFragmentTransaction = getSupportFragmentManager().beginTransaction();

        gridLayout.setVisibility(View.VISIBLE);

        if (gridFragmentTransaction == null) {
            gridFragmentTransaction.add(R.id.main_grid, fragment);
        } else {
            gridFragmentTransaction.replace(R.id.main_grid, fragment);
        }

        gridFragmentTransaction.commitAllowingStateLoss();
    }

    private void hideGridFragment() {
        if (gridLayout != null) {
            gridLayout.setVisibility(View.GONE);
        }
    }
}
