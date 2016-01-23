package bq.dropbox.library.logic;

import java.util.ArrayList;

/**
 * Created by Javier on 23/01/2016.
 */
public interface GetDropboxFiles {
    void execute(Callback callback);
    void returnList(ArrayList<com.dropbox.client2.DropboxAPI.Entry> files);

    interface Callback {
        void OnSuccess(ArrayList<com.dropbox.client2.DropboxAPI.Entry> files);
        void OnFailure(String error);
    }
}
