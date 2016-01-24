package bq.dropbox.library.ui.navigator;

import android.app.Activity;
import android.content.Intent;

import bq.dropbox.library.ui.main.MainActivity;

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

    public void openMain(){
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }
}
