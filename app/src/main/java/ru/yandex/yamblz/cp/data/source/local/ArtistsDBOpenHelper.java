package ru.yandex.yamblz.cp.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.yandex.yamblz.cp.data.source.local.table.ArtistsGenresTable;
import ru.yandex.yamblz.cp.data.source.local.table.ArtistsTable;
import ru.yandex.yamblz.cp.data.source.local.table.GenresTable;
import ru.yandex.yamblz.cp.data.source.local.view.ArtistsView;

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
        db.execSQL(GenresTable.getCreateTableQuery());
        db.execSQL(ArtistsGenresTable.getCreateTableQuery());
        db.execSQL(ArtistsView.getCreateView());
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
        db.setForeignKeyConstraintsEnabled(true);
        db.enableWriteAheadLogging();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL(ArtistsView.getDeleteViewQuery());
        db.execSQL(ArtistsGenresTable.getDeleteTableQuery());
        db.execSQL(ArtistsTable.getDeleteTableQuery());
        db.execSQL(GenresTable.getDeleteTableQuery());
        onCreate(db);
    }
}
