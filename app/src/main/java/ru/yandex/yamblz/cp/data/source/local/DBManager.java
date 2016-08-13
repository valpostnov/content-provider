package ru.yandex.yamblz.cp.data.source.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.yandex.yamblz.cp.data.source.local.table.ArtistsGenresTable;
import ru.yandex.yamblz.cp.data.source.local.table.ArtistsTable;
import ru.yandex.yamblz.cp.data.source.local.table.GenresTable;

import static ru.yandex.yamblz.cp.data.source.local.table.ArtistsTable.COLUMN_ARTIST_ID;
import static ru.yandex.yamblz.cp.data.source.local.table.GenresTable.COLUMN_GENRE_ID;
import static ru.yandex.yamblz.cp.data.source.local.table.GenresTable.COLUMN_GENRE_NAME;
import static ru.yandex.yamblz.cp.data.source.local.view.ArtistsView.VIEW_NAME;

/**
 * Created by platon on 12.08.2016.
 */
public class DBManager
{
    private final SQLiteDatabase readableDB;
    private final SQLiteDatabase writableDB;

    public DBManager(SQLiteOpenHelper openHelper)
    {
        readableDB = openHelper.getReadableDatabase();
        writableDB = openHelper.getWritableDatabase();
    }

    public Cursor getArtists(String[] columns, String where, String[] whereArgs, String sortOrder)
    {
        Cursor cursor = readableDB.query(
                VIEW_NAME,
                columns,
                where,
                whereArgs,
                null,
                null,
                sortOrder);

        if (cursor != null) cursor.moveToFirst();

        return cursor;
    }

    public Cursor getGenres(String[] columns, String where, String[] whereArgs, String sortOrder)
    {
        Cursor cursor = readableDB.query(
                GenresTable.TABLE,
                columns,
                where,
                whereArgs,
                null,
                null,
                sortOrder);

        if (cursor != null) cursor.moveToFirst();

        return cursor;
    }

    public long putArtist(ContentValues values)
    {
        ContentValues copyValues = new ContentValues(values);
        copyValues.remove(COLUMN_GENRE_NAME);

        long id = readableDB.insert(ArtistsTable.TABLE, null, copyValues);

        putGenre(values);
        putArtistGenre(values);

        return id;
    }

    public void putGenre(ContentValues values)
    {
        String[] genres = values.getAsString(COLUMN_GENRE_NAME).split(",");

        for (String genre : genres)
        {
            ContentValues genreValues = new ContentValues();
            genreValues.put(COLUMN_GENRE_NAME, genre.trim());

            writableDB.insert(GenresTable.TABLE, null, genreValues);
        }
    }

    public int deleteArtist(String where, String[] whereArgs)
    {
        return writableDB.delete(ArtistsTable.TABLE, where, whereArgs);
    }

    public int getGenreId(String genre)
    {
        Cursor cursor = readableDB.query(
                GenresTable.TABLE,
                new String[] { "*" },
                COLUMN_GENRE_NAME + " =?",
                new String[] { genre },
                null,
                null,
                null);

        if (cursor != null) cursor.moveToFirst();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GENRE_ID));
        cursor.close();

        return id;
    }

    private void putArtistGenre(ContentValues values)
    {
        String[] genres = values.getAsString(COLUMN_GENRE_NAME).split(",");
        int artistId = values.getAsInteger(COLUMN_ARTIST_ID);

        for (String genre : genres)
        {
            ContentValues genreValues = new ContentValues();

            genreValues.put(ArtistsGenresTable.COLUMN_ARTIST_ID, artistId);
            genreValues.put(ArtistsGenresTable.COLUMN_GENRE_ID, getGenreId(genre.trim()));

            writableDB.insert(ArtistsGenresTable.TABLE_NAME, null, genreValues);
        }
    }
}
