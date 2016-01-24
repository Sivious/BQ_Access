package bq.dropbox.library.logic;

import com.dropbox.client2.DropboxAPI;
import java.util.ArrayList;
import bq.dropbox.library.framework.dropbox.DropboxEpubListFiles;

/**
 * Created by Javier on 23/01/2016.
 */
public class GetDropboxFilesInteractorImpl implements GetDropboxFilesInteractor {
    private DropboxAPI dropboxAPI;
    private String path;
    private DropboxEpubListFiles dropboxEpubListFiles;
    private Callback callback;

    public GetDropboxFilesInteractorImpl(DropboxAPI dropboxAPI, String path) {
        this.dropboxAPI = dropboxAPI;
        this.path = path;
    }

    @Override
    public void execute(Callback callback) {
        this.callback = callback;

        dropboxEpubListFiles = new DropboxEpubListFiles(dropboxAPI, path, this);
        dropboxEpubListFiles.execute();
    }

    @Override
    public void returnList(ArrayList<com.dropbox.client2.DropboxAPI.Entry> files) {
        callback.OnSuccess(files);
    }

}
