package com.amrashrafexample.contentproviders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchContact();
    }
    public void fetchContact()
    {
        ArrayList<String> contact = new ArrayList<>();
        ContentResolver resolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String selection = null;
        String [] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
        String[] selectionArgs = null;
        String sortOrder = null;

        Cursor cursor = resolver.query(uri,projection,selection,selectionArgs,sortOrder);

        while(cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

           contact.add(name + "\n" + num);
        }

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,contact));
    }
}
