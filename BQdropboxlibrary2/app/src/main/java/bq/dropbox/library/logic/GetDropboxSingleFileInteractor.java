package bq.dropbox.library.logic;

/**
 * Created by Javier on 24/01/2016.
 */
public interface GetDropboxSingleFileInteractor {
    void execute(Callback callback);
    void returnContent(String epubFileDownloaded);

    interface Callback {
        void OnSuccess(String content);
        void OnFailure(String error);
    }
}
