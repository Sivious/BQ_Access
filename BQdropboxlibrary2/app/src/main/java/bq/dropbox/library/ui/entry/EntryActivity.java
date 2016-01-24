package bq.dropbox.library.ui.entry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.TokenPair;

import bq.dropbox.library.R;
import bq.dropbox.library.common.Constants;
import bq.dropbox.library.ui.navigator.Navigator;

public class EntryActivity extends AppCompatActivity {

    private static final String TAG = "EntryActivity";

    private static final String ACCESS_KEY = Constants.DROPBOX_APP_KEY;
    private static final String ACCESS_SECRET = Constants.DROPBOX_APP_SECRET;
    private static final String DROPBOX_NAME = Constants.ACCOUNT_PREFS_NAME;

    private DropboxAPI<AndroidAuthSession> dropbox;
    private boolean isLoggedIn;
    private Button login;
    private Navigator navigator;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_activity);

        login = (Button) findViewById(R.id.auth_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoggedIn) {
                    dropbox.getSession().unlink();
                    loggedIn(false);
                } else {
                    dropbox.getSession().startAuthentication(EntryActivity.this);
                }
            }
        });

        loggedIn(false);

        AndroidAuthSession session;
        AppKeyPair pair = new AppKeyPair(ACCESS_KEY, ACCESS_SECRET);

        SharedPreferences prefs = getSharedPreferences(DROPBOX_NAME, 0);
        String key = prefs.getString(ACCESS_KEY, null);
        String secret = prefs.getString(ACCESS_SECRET, null);

        if (key != null && secret != null) {
            AccessTokenPair token = new AccessTokenPair(key, secret);
            session = new AndroidAuthSession(pair, Session.AccessType.APP_FOLDER, token);
        } else {
            session = new AndroidAuthSession(pair, AccessType.APP_FOLDER);
        }

        dropbox = new DropboxAPI<AndroidAuthSession>(session);

        navigator = new Navigator(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AndroidAuthSession session = dropbox.getSession();
        if (session.authenticationSuccessful()) {
            try {
                session.finishAuthentication();

                TokenPair tokens = session.getAccessTokenPair();
                SharedPreferences prefs = getSharedPreferences(DROPBOX_NAME, 0);
                Editor editor = prefs.edit();
                editor.putString(ACCESS_KEY, tokens.key);
                editor.putString(ACCESS_SECRET, tokens.secret);
                editor.commit();

                loggedIn(true);
            } catch (IllegalStateException e) {
                Toast.makeText(this, "Error during Dropbox authentication",
                        Toast.LENGTH_SHORT).show();
            }

            navigator.openMain();
        }
    }

    public void loggedIn(boolean isLogged) {
        isLoggedIn = isLogged;
        login.setText(isLogged ? "Logout" : "Login");
    }

}
