package bq.dropbox.library.model;

/**
 * Created by Javier on 23/01/2016.
 */
public class LibraryItem {
    private String fileName;
    private String dataModified;

    public String getDataModified() {
        return dataModified;
    }

    public void setDataModified(String dataModified) {
        this.dataModified = dataModified;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LibraryItem(String fileName, String dataModified) {
        this.fileName = fileName;
        this.dataModified = dataModified;
    }

}
