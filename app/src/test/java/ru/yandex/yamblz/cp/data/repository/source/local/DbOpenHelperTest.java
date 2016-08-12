package ru.yandex.yamblz.cp.data.repository.source.local;

import android.database.Cursor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import ru.yandex.yamblz.cp.BuildConfig;
import ru.yandex.yamblz.cp.data.entity.Artist;
import ru.yandex.yamblz.cp.data.entity.ArtistWithGenre;
import ru.yandex.yamblz.cp.data.entity.Genre;
import ru.yandex.yamblz.cp.data.repository.source.local.db.DBManager;
import ru.yandex.yamblz.cp.data.repository.source.local.db.DBManagerImpl;
import ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsGenresTable;
import ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable;
import ru.yandex.yamblz.cp.data.repository.source.local.table.GenresTable;

/**
 * Created by platon on 08.08.2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public final class DbOpenHelperTest
{
    private DBManager dbManager;

    @Before
    public void init()
    {
        dbManager = new DBManagerImpl(new ArtistsDBOpenHelper(RuntimeEnvironment.application));
    }

    @Test
    public void testPutArtists()
    {
        dbManager.putArtists(createArtists());
        dbManager.putGenres(createGenres());
        dbManager.putArtistsWithGenres(createArtists());

        Cursor cursor = dbManager.getArtists(
                new String[] {"*"},
                null,
                null,
                null);

        Assert.assertEquals(2, cursor.getCount());
    }

    @Test
    public void testPutArtistsWithGenres()
    {
        dbManager.putArtists(createArtists());
        dbManager.putGenres(createGenres());
        dbManager.putArtistsWithGenres(createArtists());

        Cursor cursor = dbManager.getArtistsWithGenres(
                new String[] {"*"},
                null,
                null,
                null);

        Assert.assertEquals(3, cursor.getCount());
    }

    @Test
    public void testPutGenres()
    {
        dbManager.putGenres(createGenres());

        Cursor cursor = dbManager.getGenres(
                new String[] {"*"},
                null,
                null,
                null);

        Assert.assertEquals(3, cursor.getCount());
    }


    private List<Genre> createGenres()
    {
        return Arrays.asList(
                new Genre(0, "pop"),
                new Genre(1, "rock"),
                new Genre(2, "jazz"));
    }

    private List<Artist> createArtists()
    {
        String[] genres1 = {"pop"};
        String[] genres2 = {"rock", "jazz"};

        return Arrays.asList(
                Artist.Builder().setId(0).setName("Tove Lo").setGenres(genres1).build(),
                Artist.Builder().setId(1).setName("Reflex").setGenres(genres2).build());
    }
}
