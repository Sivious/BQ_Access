package bq.dropbox.library.ui.main.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;

import java.util.ArrayList;

import bq.dropbox.library.common.Constants;
import bq.dropbox.library.logic.GetDropboxFilesInteractor;
import bq.dropbox.library.logic.GetDropboxFilesInteractorImpl;
import bq.dropbox.library.model.LibraryItem;
import bq.dropbox.library.ui.main.MainActivity;

/**
 * Created by Javier on 23/01/2016.
 */
public class MainPresenterImpl implements MainPresenter {
    private final Context context;
    private final MainActivity view;
    private DropboxAPI dropboxApi;
    private GetDropboxFilesInteractor getDropboxFilesInteractor;

    private static final String ACCESS_KEY = Constants.DROPBOX_APP_KEY;
    private static final String ACCESS_SECRET = Constants.DROPBOX_APP_SECRET;
    private static final String DROPBOX_NAME = Constants.ACCOUNT_PREFS_NAME;

    public MainPresenterImpl(Context context, MainActivity view) {
        this.context = context;
        this.view = view;

        AndroidAuthSession session = getDropboxSession();

        dropboxApi = new DropboxAPI(session);
        getDropboxFilesInteractor = new GetDropboxFilesInteractorImpl(dropboxApi, Constants.ROOT_PATH);
    }


    private AndroidAuthSession getDropboxSession() {
        AppKeyPair appKeyPair = new AppKeyPair(Constants.DROPBOX_APP_KEY, Constants.DROPBOX_APP_SECRET);
        AndroidAuthSession session;
        String[] stored = getKeys();

        if (stored != null) {
            AccessTokenPair accessToken = new AccessTokenPair(stored[0], stored[1]);
            session = new AndroidAuthSession(appKeyPair, Constants.ACCESS_TYPE, accessToken);
        } else {
            session = new AndroidAuthSession(appKeyPair, Constants.ACCESS_TYPE);
        }

        return session;
    }

    private String[] getKeys() {

        SharedPreferences prefs = context.getSharedPreferences(DROPBOX_NAME, 0);
        String key = prefs.getString(ACCESS_KEY, null);
        String secret = prefs.getString(ACCESS_SECRET, null);

        String[] arrayList = {key, secret};

        return arrayList;
    }

    @Override
    public void getFiles() {
        getDropboxFilesInteractor.execute(new GetDropboxFilesInteractor.Callback() {
            @Override
            public void OnSuccess(ArrayList<DropboxAPI.Entry> files) {
                view.setFileList(covertToLibraryData(files));
            }

            @Override
            public void OnFailure(String error) {
            }
        });
    }

    private ArrayList<LibraryItem> covertToLibraryData(ArrayList<DropboxAPI.Entry> files) {
        ArrayList<LibraryItem> items = new ArrayList<>();
        for (DropboxAPI.Entry file : files) {
            String name = file.fileName();
            String dateModified = file.clientMtime;
            String path = file.path;
            LibraryItem item = new LibraryItem(name, dateModified, path);
            items.add(item);
        }

        return items;
    }
}
