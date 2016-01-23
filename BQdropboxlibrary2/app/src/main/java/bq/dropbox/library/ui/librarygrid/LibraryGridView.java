package bq.dropbox.library.ui.librarygrid;

import java.util.ArrayList;

import bq.dropbox.library.model.LibraryItem;

/**
 * Created by Javier on 23/01/2016.
 */
public interface LibraryGridView {
    void setFileList(ArrayList<LibraryItem> items);
}
