package bq.dropbox.library.logic;

import bq.dropbox.library.framework.epub.GetEpubInfo;
import bq.dropbox.library.framework.epub.GetEpubInfoImpl;
import bq.dropbox.library.model.EpubDetailedInfoItem;

/**
 * Created by Javier on 24/01/2016.
 */
public class GetEpubDetailedInfoItemInteractorImpl implements GetEpubDetailedInfoItemInteractor {
    GetEpubInfo getEpubInfo;

    @Override
    public void execute(String path, final Callback callback) {
        getEpubInfo = new GetEpubInfoImpl();

        getEpubInfo.readInfo(path, new GetEpubInfo.Callback() {
            @Override
            public void OnSuccess(EpubDetailedInfoItem item) {
                callback.OnSuccess(item);
            }

            @Override
            public void OnFailure(String error) {
                callback.OnFailure(error);
            }
        });
    }
}
