package bq.dropbox.library.ui.main.fragments.presenters;

import android.content.Context;
import android.content.SharedPreferences;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;

import bq.dropbox.library.common.Constants;
import bq.dropbox.library.logic.GetDropboxSingleFileInteractor;
import bq.dropbox.library.logic.GetDropboxSingleFileInteractorImpl;
import bq.dropbox.library.logic.GetEpubDetailedInfoItemInteractor;
import bq.dropbox.library.logic.GetEpubDetailedInfoItemInteractorImpl;
import bq.dropbox.library.model.EpubDetailedInfoItem;
import bq.dropbox.library.ui.main.fragments.EpubInfoFragmentImpl;

/**
 * Created by Javier on 24/01/2016.
 */
public class EpubInfoPresenterImpl implements EpubInfoPresenter {
    private final Context context;
    private final GetDropboxSingleFileInteractor getDropboxSingleFileInteractor;
    private DropboxAPI dropboxApi;
    private String path;
    private EpubInfoFragmentImpl view;
    private String epubFilePath;
    private GetEpubDetailedInfoItemInteractor getEpubDetailedInfoItemInteractor;

    private static final String ACCESS_KEY = Constants.DROPBOX_APP_KEY;
    private static final String ACCESS_SECRET = Constants.DROPBOX_APP_SECRET;
    private static final String DROPBOX_NAME = Constants.ACCOUNT_PREFS_NAME;

    public EpubInfoPresenterImpl(Context context, EpubInfoFragmentImpl view, String path) {
        this.context = context;
        this.view = view;
        this.path = path;

        AndroidAuthSession session = getDropboxSession();

        dropboxApi = new DropboxAPI(session);
        getDropboxSingleFileInteractor = new GetDropboxSingleFileInteractorImpl(dropboxApi, path, context.getExternalCacheDir().getPath() + path);
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
    public void getFileFromDropbox() {
        getDropboxSingleFileInteractor.execute(new GetDropboxSingleFileInteractor.Callback() {
            @Override
            public void OnSuccess(String content) {
                epubFilePath = content;
                readEpubData(epubFilePath);
            }

            @Override
            public void OnFailure(String error) {

            }
        });
    }

    private void readEpubData(String epubFilePath) {
        getEpubDetailedInfoItemInteractor = new GetEpubDetailedInfoItemInteractorImpl();

        getEpubDetailedInfoItemInteractor.execute(epubFilePath, new GetEpubDetailedInfoItemInteractor.Callback() {
            @Override
            public void OnSuccess(EpubDetailedInfoItem item) {
                view.setContent(item);
            }

            @Override
            public void OnFailure(String error) {
                view.showError(error);
            }
        });
    }

}
