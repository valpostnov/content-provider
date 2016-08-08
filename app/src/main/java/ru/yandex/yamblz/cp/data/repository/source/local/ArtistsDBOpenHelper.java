package ru.yandex.yamblz.cp.data.repository.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable;

/**
 * Created by platon on 31.07.2016.
 */
public class ArtistsDBOpenHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "artists.db";
    private static final int VERSION = 1;

    public ArtistsDBOpenHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(ArtistsTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
}
