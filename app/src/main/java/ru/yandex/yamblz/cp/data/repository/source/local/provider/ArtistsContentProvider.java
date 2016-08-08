package ru.yandex.yamblz.cp.data.repository.source.local.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import ru.yandex.yamblz.cp.data.repository.source.local.ArtistsDBOpenHelper;

import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.TABLE_NAME;

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

    private SQLiteOpenHelper sqLiteOpenHelper;

    @Override
    public boolean onCreate()
    {
        sqLiteOpenHelper = new ArtistsDBOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        switch (URI_MATCHER.match(uri))
        {
            case CODE_ARTISTS:

                Cursor cursor = sqLiteOpenHelper
                        .getReadableDatabase()
                        .query(
                                TABLE_NAME,
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
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
                insertedId = sqLiteOpenHelper
                        .getWritableDatabase()
                        .insert(
                                TABLE_NAME,
                                null,
                                values
                        );
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
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        final int numberOfRowsDeleted;

        switch (URI_MATCHER.match(uri))
        {
            case CODE_ARTISTS:
                numberOfRowsDeleted = sqLiteOpenHelper
                        .getWritableDatabase()
                        .delete(
                                TABLE_NAME,
                                selection,
                                selectionArgs
                        );
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
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        final int numberOfRowsAffected;

        switch (URI_MATCHER.match(uri))
        {
            case CODE_ARTISTS:
                numberOfRowsAffected = sqLiteOpenHelper
                        .getWritableDatabase()
                        .update(
                                TABLE_NAME,
                                values,
                                selection,
                                selectionArgs
                        );
                break;

            default:
                return 0;
        }

        if (numberOfRowsAffected > 0)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsAffected;
    }
}
