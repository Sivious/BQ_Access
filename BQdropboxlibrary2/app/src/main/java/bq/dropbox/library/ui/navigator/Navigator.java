package bq.dropbox.library.ui.navigator;

import android.app.Activity;
import android.content.Intent;

import bq.dropbox.library.ui.librarygrid.LibraryGridActivity;
import bq.dropbox.library.ui.librarylist.LibraryListActivity;

/**
 * Created by Javier on 23/01/2016.
 */
public class Navigator {
    private final Activity context;

    public Navigator(Activity context) {
        this.context = context;
    }

    private void startActivity(Intent intent) {
        context.startActivity(intent);
    }

    public void openLibraryList(){
        Intent intent = new Intent(context, LibraryListActivity.class);
        startActivity(intent);
    }

    public void openLibraryGrid(){
        Intent intent = new Intent(context, LibraryGridActivity.class);
        startActivity(intent);
    }

    /*public void openTermsAndConditionsWeb() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.TERMS_CONDITIONS_URL));
        startActivity(browserIntent);
    }*/
}
