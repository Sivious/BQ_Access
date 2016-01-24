package bq.dropbox.library.framework.dropbox;

import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import bq.dropbox.library.logic.GetDropboxSingleFileInteractor;

/**
 * Created by Javier on 24/01/2016.
 */
public class GetDropboxEpubFile extends AsyncTask<Void, Void, Void> {

    private final GetDropboxSingleFileInteractor getDropboxSingleFileInteractor;
    private DropboxAPI dropboxApi;
    private String path;
    private String destinyPath;

    public GetDropboxEpubFile(DropboxAPI dropboxApi, String path, String destinyPath, GetDropboxSingleFileInteractor getDropboxSingleFileInteractor) {
        this.getDropboxSingleFileInteractor = getDropboxSingleFileInteractor;
        this.dropboxApi = dropboxApi;
        this.path = path;
        this.destinyPath = destinyPath;
    }

    @Override
    protected Void doInBackground(Void... params) {
        File file = new File(destinyPath);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            DropboxAPI.DropboxFileInfo info = dropboxApi.getFile(path, null, outputStream, null);

            outputStream.close();
        } catch (FileNotFoundException e) {
            Log.i("DbExampleLog", e.getMessage());
        } catch (DropboxException e) {
            Log.i("DbExampleLog", e.getMessage());
        } catch (IOException e) {
            Log.i("DbExampleLog", e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        getDropboxSingleFileInteractor.returnContent(destinyPath);
    }

    private String getFileName(String path) {
        int from = path.lastIndexOf("/");
        return path.substring(from, path.length());
    }
}
