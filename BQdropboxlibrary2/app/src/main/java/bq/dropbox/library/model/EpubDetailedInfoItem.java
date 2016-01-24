package bq.dropbox.library.model;

import android.graphics.Bitmap;

/**
 * Created by Javier on 24/01/2016.
 */
public class EpubDetailedInfoItem {
    private Bitmap coverImage;
    private String title;

    public EpubDetailedInfoItem(Bitmap coverImage, String title) {
        this.coverImage = coverImage;
        this.title = title;
    }

    public Bitmap getCoverImage() {
        return coverImage;
    }

    public String getTitle() {
        return title;
    }
}
