package com.example.b10705.myapplication4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements TitlesFragment.OnTitleSelectedListener {

    private final int REQUEST_CODE_READ_CONTACTS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_READ_CONTACTS);
        } else
            getContacts();
    }

    @Override
    public void onTitleSelected(int i) {
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setSelection(i);
            getSupportFragmentManager().beginTransaction().replace(R.id.details, detailsFragment).commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("index", i);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts();
            } else {
                Toast.makeText(getApplicationContext(), "READ_CONTACTS 접근 권한이 필요합니다", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getContacts() {
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.Name,
                ContactsContract.CommonDataKinds.Phone.Address,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        String selectionClause = ContactsContract.CommonDataKinds.Phone.TYPE + " = ? ";

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                selectionClause,
                selectionArgs,
                null);

        String[] contactsColumns = { // 쿼리결과인 Cursor 객체로부터 출력할 열들
                ContactsContract.CommonDataKinds.Phone.Address,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        int[] contactsListItems = { // 열의 값을 출력할 뷰 ID (layout/item.xml 내)
                R.id.address,
                R.id.phone
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.item,
                c,
                contactsColumns,
                contactsListItems,
                0);

        ListView lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);

    }

}

