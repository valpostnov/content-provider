package ru.yandex.yamblz.cp.data.source.local;

import android.content.ContentValues;
import android.database.Cursor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import ru.yandex.yamblz.cp.BuildConfig;
import ru.yandex.yamblz.cp.data.source.local.table.ArtistsTable;
import ru.yandex.yamblz.cp.data.source.local.table.GenresTable;

/**
 * Created by platon on 08.08.2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public final class DBManagerTest
{
    private DBManager dbManager;
    private ContentValues genreValues;
    private ContentValues artistValues;

    @Before
    public void init()
    {
        dbManager = new DBManager(new ArtistsDBOpenHelper(RuntimeEnvironment.application));
        genreValues = new ContentValues(1);
        genreValues.put(GenresTable.COLUMN_GENRE_NAME, "pop, rock");

        artistValues = new ContentValues(1);
        artistValues.put(ArtistsTable.COLUMN_ARTIST_ID, 0);
        artistValues.put(ArtistsTable.COLUMN_ARTIST_NAME, "Tove Lo");
        artistValues.put(GenresTable.COLUMN_GENRE_NAME, "pop, rock");
    }

    @Test
    public void testPutArtist()
    {
        dbManager.putArtist(artistValues);

        Cursor cursor = dbManager.getArtists(
                new String[] {"*"},
                null,
                null,
                null);

        Assert.assertEquals(1, cursor.getCount());
    }

    @Test
    public void testPutGenres()
    {
        dbManager.putGenre(genreValues);

        Cursor cursor = dbManager.getGenres(
                new String[] {"*"},
                null,
                null,
                null);

        Assert.assertEquals(2, cursor.getCount());
    }

    @Test
    public void testGetGenreId()
    {
        dbManager.putGenre(genreValues);
        int genreId = dbManager.getGenreId("rock");

        Assert.assertEquals(2, genreId);
    }

    @Test
    public void testDeleteArtist()
    {
        dbManager.putArtist(artistValues);

        dbManager.deleteArtist(ArtistsTable.COLUMN_ARTIST_NAME + "=?", new String[] { "Tove Lo" });

        Cursor cursor = dbManager.getArtists(
                new String[] { "*" },
                ArtistsTable.COLUMN_ARTIST_NAME + "=?",
                new String[] { "Tove Lo" },
                null);

        Assert.assertEquals(0, cursor.getCount());
    }
}
