package bq.dropbox.library.framework.dropbox;

import android.os.AsyncTask;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;

import java.util.ArrayList;

import bq.dropbox.library.logic.GetDropboxFilesImpl;

/**
 * Created by Javier on 23/01/2016.
 */
public class DropboxEpubListFiles extends AsyncTask<Void, Void, Void> {
    private final GetDropboxFilesImpl getDropboxFiles;
    private DropboxAPI dropboxApi;
    private String path;
    private ArrayList<com.dropbox.client2.DropboxAPI.Entry> files;
    private final String fileExtension = "EPUB";

    public DropboxEpubListFiles(DropboxAPI dropboxApi, String path, GetDropboxFilesImpl getDropboxFiles) {
        this.dropboxApi = dropboxApi;
        this.path = path;
        this.getDropboxFiles = getDropboxFiles;
    }

    @Override
    protected Void doInBackground(Void... params) {
        files = new ArrayList<com.dropbox.client2.DropboxAPI.Entry>();

        try {
            Entry metadata = dropboxApi.metadata(path, 1000, null, true, null);

            for (Entry entry : metadata.contents) {
                if (!entry.isDir && isEpub(entry)) {
                    files.add(entry);
                }
            }
        } catch (DropboxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isEpub(Entry entry) {
        return entry.fileName().toUpperCase().endsWith(fileExtension);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        getDropboxFiles.returnList(files);
    }
}
