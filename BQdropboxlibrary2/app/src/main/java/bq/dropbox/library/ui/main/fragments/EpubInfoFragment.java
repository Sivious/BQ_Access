package bq.dropbox.library.ui.main.fragments;

import bq.dropbox.library.model.EpubDetailedInfoItem;

/**
 * Created by Javier on 24/01/2016.
 */
public interface EpubInfoFragment {
    void setPath(String path);

    void setContent(EpubDetailedInfoItem item);
    void showError(String error);
}
