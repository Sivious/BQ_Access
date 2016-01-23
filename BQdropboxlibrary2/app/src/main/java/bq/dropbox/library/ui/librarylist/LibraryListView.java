package bq.dropbox.library.ui.librarylist;

import java.util.ArrayList;

import bq.dropbox.library.model.LibraryItem;

/**
 * Created by Javier on 23/01/2016.
 */
public interface LibraryListView {
    void setFileList(ArrayList<LibraryItem> items);
}
