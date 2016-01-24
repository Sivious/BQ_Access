package bq.dropbox.library.framework.epub;

import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import bq.dropbox.library.model.EpubDetailedInfoItem;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;

/**
 * Created by Javier on 24/01/2016.
 */
public class GetEpubInfoImpl implements  GetEpubInfo{
    private EpubDetailedInfoItem item;

    @Override
    public void readInfo(String path, Callback callback) {
        EpubReader epubReader = new EpubReader();

        try {
            InputStream file = new FileInputStream(path);
            Book book = epubReader.readEpub(file);

            item = new EpubDetailedInfoItem(BitmapFactory.decodeStream(book.getCoverImage().getInputStream()), book.getTitle());

            callback.OnSuccess(item);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            callback.OnFailure(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            callback.OnFailure(e.getMessage());
        }

    }
}
