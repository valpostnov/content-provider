package ru.yandex.yamblz.cp.data.source.local.view;

import ru.yandex.yamblz.cp.data.source.local.table.ArtistsGenresTable;
import ru.yandex.yamblz.cp.data.source.local.table.ArtistsTable;
import ru.yandex.yamblz.cp.data.source.local.table.GenresTable;

/**
 * Created by platon on 12.08.2016.
 */
public class ArtistsView
{
    public static final String VIEW_NAME = "artists_view";

    public static String getCreateView()
    {
        return "CREATE VIEW " + VIEW_NAME +
                " AS SELECT " +
                ArtistsTable.TABLE + "." + ArtistsTable.COLUMN_ARTIST_ID + ", " +
                ArtistsTable.TABLE + "." + ArtistsTable.COLUMN_ARTIST_NAME + ", " +
                "GROUP_CONCAT(" +
                GenresTable.TABLE + "." + GenresTable.COLUMN_GENRE_NAME +
                ", ', ') AS genre, " +
                ArtistsTable.TABLE + "." + ArtistsTable.COLUMN_ALBUMS + ", " +
                ArtistsTable.TABLE + "." + ArtistsTable.COLUMN_TRACKS + ", " +
                ArtistsTable.TABLE + "." + ArtistsTable.COLUMN_DESC + ", " +
                ArtistsTable.TABLE + "." + ArtistsTable.COLUMN_COVER_BIG + ", " +
                ArtistsTable.TABLE + "." + ArtistsTable.COLUMN_COVER_SMALL +
                " FROM " +
                ArtistsTable.TABLE +
                " LEFT JOIN " +
                ArtistsGenresTable.TABLE_NAME +
                " ON " +
                ArtistsTable.TABLE + "." + ArtistsTable.COLUMN_ARTIST_ID +
                " = " +
                ArtistsGenresTable.TABLE_NAME + "." + ArtistsGenresTable.COLUMN_ARTIST_ID +
                " LEFT JOIN " +
                GenresTable.TABLE +
                " ON " +
                ArtistsGenresTable.TABLE_NAME + "." + ArtistsGenresTable.COLUMN_GENRE_ID +
                " = " +
                GenresTable.TABLE + "." + GenresTable.COLUMN_GENRE_ID +
                " GROUP BY " +
                ArtistsGenresTable.TABLE_NAME + "." + ArtistsTable.COLUMN_ARTIST_ID;
    }

    public static String getDeleteViewQuery()
    {
        return "DROP VIEW IF EXIST " + VIEW_NAME;
    }
}
