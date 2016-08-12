package ru.yandex.yamblz.cp.data.repository.source.local.db;

import android.database.Cursor;

import java.util.List;

import ru.yandex.yamblz.cp.data.entity.Artist;
import ru.yandex.yamblz.cp.data.entity.ArtistWithGenre;
import ru.yandex.yamblz.cp.data.entity.Genre;

/**
 * Created by platon on 12.08.2016.
 */
public interface DBManager
{
    Cursor getArtists(String[] columns, String selection, String[] selectionArgs, String sortOrder);
    Cursor getGenres(String[] columns, String selection, String[] selectionArgs, String sortOrder);
    Cursor getArtistsWithGenres(String[] columns, String selection, String[] selectionArgs, String sortOrder);

    void putArtists(List<Artist> artists);
    void putGenres(List<Genre> genres);
    void putArtistsWithGenres(List<Artist> artists);
}
