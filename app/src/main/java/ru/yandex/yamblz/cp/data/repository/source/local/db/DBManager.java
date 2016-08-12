package ru.yandex.yamblz.cp.data.repository.source.local.db;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;
import java.util.Set;

import ru.yandex.yamblz.cp.data.entity.Artist;

/**
 * Created by platon on 12.08.2016.
 */
public interface DBManager
{
    Cursor getArtists(String[] columns, String selection, String[] selectionArgs, String sortOrder);
    Cursor getGenres(String[] columns, String selection, String[] selectionArgs, String sortOrder);
    Cursor getArtistsGenres(String[] columns, String selection, String[] selectionArgs, String sortOrder);

    long putArtist(ContentValues cv);
    long putGenre(ContentValues cv);
    int deleteArtist(String selection, String[] selectionArgs);
    void putArtists(List<Artist> artists);
    void putGenres(Set<String> genres);
    void putArtistsGenres(List<Artist> artists);
}
