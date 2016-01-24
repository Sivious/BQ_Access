package bq.dropbox.library.ui.main;

import java.util.ArrayList;

import bq.dropbox.library.model.LibraryItem;

/**
 * Created by Javier on 24/01/2016.
 */
public interface MainView {
    void setFileList(ArrayList<LibraryItem> items);
    void showEpubInfo(LibraryItem item);
    void hideEpubInfo();
}
