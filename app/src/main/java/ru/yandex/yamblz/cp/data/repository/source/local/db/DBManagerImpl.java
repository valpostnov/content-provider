package ru.yandex.yamblz.cp.data.repository.source.local.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import ru.yandex.yamblz.cp.data.entity.Artist;
import ru.yandex.yamblz.cp.data.entity.ArtistWithGenre;
import ru.yandex.yamblz.cp.data.entity.Genre;
import ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsGenresTable;
import ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable;
import ru.yandex.yamblz.cp.data.repository.source.local.table.GenresTable;
import ru.yandex.yamblz.cp.data.repository.source.local.view.ArtistsView;

import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_ALBUMS;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_ARTIST_ID;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_ARTIST_NAME;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_DESC;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_TRACKS;

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
    public Cursor getArtistsWithGenres(String[] columns, String selection, String[] selectionArgs, String sortOrder)
    {
        Cursor cursor = sqLiteOpenHelper
                .getReadableDatabase()
                .query(
                        ArtistsGenresTable.TABLE_NAME,
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
    public void putArtists(List<Artist> artists)
    {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        for (Artist artist : artists)
        {
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_ARTIST_ID, artist.getId());
            cv.put(COLUMN_ARTIST_NAME, artist.getName());
            cv.put(COLUMN_TRACKS, artist.getTracks());
            cv.put(COLUMN_ALBUMS, artist.getAlbums());
            cv.put(COLUMN_DESC, artist.getDesc());

            db.insert(ArtistsTable.TABLE_NAME, null, cv);
        }
    }

    @Override
    public void putGenres(List<Genre> genres)
    {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        for (Genre genre : genres)
        {
            ContentValues cv = new ContentValues();

            cv.put(GenresTable.COLUMN_GENRE_ID, genre.getId());
            cv.put(GenresTable.COLUMN_GENRE_NAME, genre.getName());

            db.insert(GenresTable.TABLE_NAME, null, cv);
        }
    }

    @Override
    public void putArtistsWithGenres(List<Artist> artists)
    {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        for (Artist a : artists)
        {
            for (String genre : a.getGenres())
            {
                ContentValues cv = new ContentValues();

                cv.put(ArtistsGenresTable.COLUMN_ARTIST_ID, a.getId());
                cv.put(ArtistsGenresTable.COLUMN_GENRE_ID, getGenreId(genre));

                db.insert(ArtistsGenresTable.TABLE_NAME, null, cv);
            }
        }
    }

    private int getGenreId(String genre)
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
}
