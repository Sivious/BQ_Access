package bq.dropbox.library.ui.main.fragments;

import java.util.ArrayList;

import bq.dropbox.library.model.LibraryItem;

/**
 * Created by Javier on 23/01/2016.
 */
public interface LibraryGridFragment {
    void setFileList(ArrayList<LibraryItem> items);
}
