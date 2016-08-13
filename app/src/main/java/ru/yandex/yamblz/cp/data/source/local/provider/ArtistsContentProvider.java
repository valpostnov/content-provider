package ru.yandex.yamblz.cp.data.source.local.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import ru.yandex.yamblz.cp.data.source.local.ArtistsDBOpenHelper;
import ru.yandex.yamblz.cp.data.source.local.DBManager;

/**
 * Created by platon on 31.07.2016.
 */
public class ArtistsContentProvider extends ContentProvider
{
    public static final String AUTHORITY = "ru.yandex.yamblz.artists_provider";
    private static final String PATH_ARTISTS = "artists";
    private static final int CODE_ARTISTS = 1;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static
    {
        URI_MATCHER.addURI(AUTHORITY, PATH_ARTISTS, CODE_ARTISTS);
    }

    private DBManager dbManager;

    @Override
    public boolean onCreate()
    {
        dbManager = new DBManager(new ArtistsDBOpenHelper(getContext()));
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] columns, String where, String[] whereArgs, String sortOrder)
    {
        switch (URI_MATCHER.match(uri))
        {
            case CODE_ARTISTS:

                Cursor cursor = dbManager
                        .getArtists(
                                columns,
                                where,
                                whereArgs,
                                sortOrder
                              );

                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri)
    {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        final long insertedId;

        switch (URI_MATCHER.match(uri))
        {
            case CODE_ARTISTS:

                insertedId = dbManager.putArtist(values);
                break;

            default:
                return null;
        }

        if (insertedId != -1)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return ContentUris.withAppendedId(uri, insertedId);
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs)
    {
        final int numberOfRowsDeleted;

        switch (URI_MATCHER.match(uri))
        {
            case CODE_ARTISTS:

                numberOfRowsDeleted = dbManager.deleteArtist(where, whereArgs);
                break;

            default:
                return 0;
        }

        if (numberOfRowsDeleted > 0)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs)
    {
        return -1;
    }
}
