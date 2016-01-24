package bq.dropbox.library.logic;

import bq.dropbox.library.model.EpubDetailedInfoItem;

/**
 * Created by Javier on 24/01/2016.
 */
public interface GetEpubDetailedInfoItemInteractor {
    void execute(String path, Callback callback);

    interface Callback {
        void OnSuccess(EpubDetailedInfoItem item);

        void OnFailure(String error);
    }
}
