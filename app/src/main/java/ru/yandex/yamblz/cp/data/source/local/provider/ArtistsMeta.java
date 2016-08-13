package ru.yandex.yamblz.cp.data.source.local.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.contentresolver.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio.contentresolver.operations.delete.DeleteResolver;
import com.pushtorefresh.storio.contentresolver.operations.get.DefaultGetResolver;
import com.pushtorefresh.storio.contentresolver.operations.get.GetResolver;
import com.pushtorefresh.storio.contentresolver.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.contentresolver.operations.put.PutResolver;
import com.pushtorefresh.storio.contentresolver.queries.DeleteQuery;
import com.pushtorefresh.storio.contentresolver.queries.InsertQuery;
import com.pushtorefresh.storio.contentresolver.queries.UpdateQuery;

import ru.yandex.yamblz.cp.data.entity.Artist;
import ru.yandex.yamblz.cp.data.entity.Cover;
import ru.yandex.yamblz.cp.data.source.local.table.GenresTable;

import static ru.yandex.yamblz.cp.data.source.local.table.ArtistsTable.*;


/**
 * Created by platon on 31.07.2016.
 */
public class ArtistsMeta
{
    public static final String URI_STRING = "content://" + ArtistsContentProvider.AUTHORITY + "/artists";
    public static final Uri CONTENT_URI = Uri.parse(URI_STRING);

    public static final PutResolver<Artist> PUT_RESOLVER = new DefaultPutResolver<Artist>()
    {
        @NonNull
        @Override
        protected InsertQuery mapToInsertQuery(@NonNull Artist object)
        {
            return InsertQuery.builder()
                    .uri(CONTENT_URI)
                    .build();
        }

        @NonNull
        @Override
        protected UpdateQuery mapToUpdateQuery(@NonNull Artist artist)
        {
            return UpdateQuery.builder()
                    .uri(CONTENT_URI)
                    .where(COLUMN_ARTIST_ID + " = ?")
                    .whereArgs(artist.getId())
                    .build();
        }

        @NonNull
        @Override
        protected ContentValues mapToContentValues(@NonNull Artist artist)
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put(COLUMN_ARTIST_ID, artist.getId());
            contentValues.put(COLUMN_ARTIST_NAME, artist.getName());
            contentValues.put(COLUMN_TRACKS, artist.getTracks());
            contentValues.put(COLUMN_ALBUMS, artist.getAlbums());
            contentValues.put(COLUMN_DESC, artist.getDesc());
            contentValues.put(COLUMN_COVER_SMALL, artist.getCover().getSmall());
            contentValues.put(COLUMN_COVER_BIG, artist.getCover().getCoverBig());
            contentValues.put(GenresTable.COLUMN_GENRE_NAME, artist.getGenres());

            return contentValues;
        }
    };

    public static final GetResolver<Artist> GET_RESOLVER = new DefaultGetResolver<Artist>()
    {
        @NonNull
        @Override
        public Artist mapFromCursor(@NonNull Cursor cursor)
        {
            return Artist.Builder()
                    .setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ARTIST_ID)))
                    .setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTIST_NAME)))
                    .setAlbums(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ALBUMS)))
                    .setTracks(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TRACKS)))
                    .setGenres(cursor.getString(cursor.getColumnIndexOrThrow(GenresTable.COLUMN_GENRE_NAME)).split(","))
                    .setDesc(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESC)))
                    .setCover(new Cover(
                                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COVER_SMALL)),
                                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COVER_BIG))))
                    .build();
        }
    };

    public static final DeleteResolver<Artist> DELETE_RESOLVER = new DefaultDeleteResolver<Artist>()
    {
        @NonNull
        @Override
        protected DeleteQuery mapToDeleteQuery(@NonNull Artist artist)
        {
            return DeleteQuery.builder()
                    .uri(CONTENT_URI)
                    .where(COLUMN_ARTIST_ID + " = ?")
                    .whereArgs(artist.getId())
                    .build();
        }
    };
}
