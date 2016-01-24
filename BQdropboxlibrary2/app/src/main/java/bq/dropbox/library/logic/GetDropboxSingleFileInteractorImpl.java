package bq.dropbox.library.logic;

import com.dropbox.client2.DropboxAPI;

import bq.dropbox.library.framework.dropbox.GetDropboxEpubFile;

/**
 * Created by Javier on 24/01/2016.
 */
public class GetDropboxSingleFileInteractorImpl implements GetDropboxSingleFileInteractor {

    private DropboxAPI dropboxAPI;
    private String path;
    private String destinyPath;
    private GetDropboxEpubFile getDropboxEpubFile;
    private Callback callback;

    public GetDropboxSingleFileInteractorImpl(DropboxAPI dropboxAPI, String path, String destinyPath) {
        this.dropboxAPI = dropboxAPI;
        this.path = path;
        this.destinyPath = destinyPath;
    }

    @Override
    public void execute(Callback callback) {
        this.callback = callback;

        getDropboxEpubFile = new GetDropboxEpubFile(dropboxAPI, path, destinyPath, this);
        getDropboxEpubFile.execute();
    }

    @Override
    public void returnContent(String content) {
        callback.OnSuccess(content);
    }
}
