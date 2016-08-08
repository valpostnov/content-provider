package ru.yandex.yamblz.cp.data.repository.source.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import ru.yandex.yamblz.cp.BuildConfig;
import ru.yandex.yamblz.cp.data.entity.Artist;

import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_ALBUMS;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_ARTIST_ID;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_ARTIST_NAME;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_COVER_BIG;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_COVER_SMALL;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_DESC;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_GENRES;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.COLUMN_TRACKS;
import static ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable.TABLE_NAME;

/**
 * Created by platon on 08.08.2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public final class DbOpenHelperTest
{
    @Test
    public void testInsertArtist()
    {
        SQLiteDatabase db = new ArtistsDBOpenHelper(RuntimeEnvironment.application).getWritableDatabase();

        //проверяем создание бд
        Assert.assertNotNull(db);

        db.insert(TABLE_NAME, null, makeCV());
        int count = db.rawQuery("select * from " + TABLE_NAME, null).getCount();

        //проверяем вставку одного артиста
        Assert.assertEquals(1, count);

        Cursor cursor = db.rawQuery("select name from " + TABLE_NAME + " where name = 'VA'", null);
        cursor.moveToNext();
        String artistName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ARTIST_NAME));
        cursor.close();

        //проверяем, что вставленный артист есть в бд
        Assert.assertEquals("VA", artistName);
    }

    private ContentValues makeCV()
    {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ARTIST_ID, 0);
        cv.put(COLUMN_ARTIST_NAME, "VA");
        cv.put(COLUMN_TRACKS, 100);
        cv.put(COLUMN_ALBUMS, 30);
        cv.put(COLUMN_DESC, "desc");
        cv.put(COLUMN_GENRES, "rap");
        cv.put(COLUMN_COVER_SMALL, "small");
        cv.put(COLUMN_COVER_BIG, "big");

        return cv;
    }
}
