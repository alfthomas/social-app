package net.alfthomas.android.socialapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OverviewActivity extends Activity {

    SharedPreferences preferences;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Button button = (Button) findViewById(R.id.Button01);
        button.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                String username = preferences.getString(getString(R.string.user_name_key), "n/a");
                String password = preferences.getString(getString(R.string.password_key), "n/a");
                showPrefs(username, password);
            }

        });
        
        Button buttonChangePreferences = (Button) findViewById(R.id.Button02);
        buttonChangePreferences.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                updatePreferenceValue();
            }

        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferences:
                Intent intent = new Intent(OverviewActivity.this, MyPreferencesActivity.class);
                startActivity(intent);
                Toast.makeText(OverviewActivity.this, "Enter your user credentials.",
                                Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return true;
    }
    
    private void showPrefs(String username, String password) {
        Toast.makeText(OverviewActivity.this, 
                        "Input: " + username + " and password: " + password,
                        Toast.LENGTH_LONG).show();
        
    }
    
    private void updatePreferenceValue() {
        Editor edit = preferences.edit();
        String username = preferences.getString(getString(R.string.user_name_key), "n/a");
        StringBuffer buffer = new StringBuffer();
        for (int i = username.length() - 1; i >= 0; i--) {
            buffer.append(username.charAt(i));
        }
        edit.putString(getString(R.string.user_name_key), buffer.toString());
        edit.commit();
        Toast.makeText(OverviewActivity.this,
                        "Reverted string sequence of user name.",
                        Toast.LENGTH_LONG).show();
    }
}