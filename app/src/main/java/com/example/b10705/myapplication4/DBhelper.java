package com.example.b10705.myapplication4;

import android.content.Context;

/**
 * Created by B10705 on 2017-11-22.
 */

public class DBhelper {
    public DBHelper(Context context) {
        super(context, UserContract.DB_Name, null, UserContract.DATABASE_VERSION);
    }
}
