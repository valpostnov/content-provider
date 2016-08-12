package ru.yandex.yamblz.cp.data.repository.source.local.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import ru.yandex.yamblz.cp.data.entity.Artist;
import ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsGenresTable;
import ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable;
import ru.yandex.yamblz.cp.data.repository.source.local.table.GenresTable;
import ru.yandex.yamblz.cp.data.repository.source.local.view.ArtistsView;

import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_ARTIST_ID;

/**
 * Created by platon on 12.08.2016.
 */
public class DBManagerImpl implements DBManager
{
    private final SQLiteOpenHelper sqLiteOpenHelper;

    public DBManagerImpl(SQLiteOpenHelper openHelper)
    {
        sqLiteOpenHelper = openHelper;
    }

    @Override
    public Cursor getArtists(String[] columns, String selection, String[] selectionArgs, String sortOrder)
    {
        Cursor cursor = sqLiteOpenHelper
                .getReadableDatabase()
                .query(
                        ArtistsView.VIEW_NAME,
                        columns,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

        if (cursor != null) cursor.moveToFirst();

        return cursor;
    }

    @Override
    public Cursor getGenres(String[] columns, String selection, String[] selectionArgs, String sortOrder)
    {
        Cursor cursor = sqLiteOpenHelper
                .getReadableDatabase()
                .query(
                        GenresTable.TABLE_NAME,
                        columns,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

        if (cursor != null) cursor.moveToFirst();

        return cursor;
    }

    @Override
    public long putArtist(ContentValues cv)
    {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        putGenre(cv);
        putArtistGenre(cv);

        cv.remove(GenresTable.COLUMN_GENRE_NAME);
        return db.insert(ArtistsTable.TABLE_NAME, null, cv);
    }

    @Override
    public void putGenre(ContentValues artistContentValues)
    {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        String[] genres = artistContentValues.getAsString(GenresTable.COLUMN_GENRE_NAME).split(",");

        for (String genre : genres)
        {
            ContentValues genreContentValues = new ContentValues();
            genreContentValues.put(GenresTable.COLUMN_GENRE_NAME, genre);

            db.insert(GenresTable.TABLE_NAME, null, genreContentValues);
        }
    }

    @Override
    public int deleteArtist(String selection, String[] selectionArgs)
    {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        return db.delete(ArtistsTable.TABLE_NAME, selection, selectionArgs);
    }

    public int getGenreId(String genre)
    {
        Cursor cursor = sqLiteOpenHelper
                .getReadableDatabase()
                .query(
                        GenresTable.TABLE_NAME,
                        new String[] { GenresTable.COLUMN_GENRE_ID },
                        GenresTable.COLUMN_GENRE_NAME + " =?",
                        new String[] { genre },
                        null,
                        null,
                        null);

        if (cursor != null) cursor.moveToFirst();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(GenresTable.COLUMN_GENRE_ID));
        cursor.close();

        return id;
    }

    private void putArtistGenre(ContentValues artistContentValues)
    {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        String[] genres = artistContentValues.getAsString(GenresTable.COLUMN_GENRE_NAME).split(",");
        int artistId = artistContentValues.getAsInteger(COLUMN_ARTIST_ID);

        for (String genre : genres)
        {
            ContentValues genreContentValues = new ContentValues();

            genreContentValues.put(ArtistsGenresTable.COLUMN_ARTIST_ID, artistId);
            genreContentValues.put(ArtistsGenresTable.COLUMN_GENRE_ID, getGenreId(genre));

            db.insert(ArtistsGenresTable.TABLE_NAME, null, genreContentValues);
        }
    }
}
