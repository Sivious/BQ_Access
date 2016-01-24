package bq.dropbox.library.model;

/**
 * Created by Javier on 23/01/2016.
 */
public class LibraryItem implements Comparable<LibraryItem>{
    private String fileName;
    private String dataModified;
    private String path;

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

    public LibraryItem(String fileName, String dataModified, String path) {
        this.fileName = fileName;
        this.dataModified = dataModified;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int compareTo(LibraryItem another) {
        int result = 0;

        if (this.fileName.compareTo(another.fileName) < 0){
            result = -1;
        }else if(this.fileName.compareTo(another.fileName) > 0){
            result = 1;
        }

        return result;
    }
}
