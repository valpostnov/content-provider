package ru.yandex.yamblz.cp.data.repository.source.local.view;

import ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsGenresTable;
import ru.yandex.yamblz.cp.data.repository.source.local.table.ArtistsTable;
import ru.yandex.yamblz.cp.data.repository.source.local.table.GenresTable;

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
                ArtistsTable.TABLE_NAME + "." + ArtistsTable.COLUMN_ARTIST_ID + ", " +
                ArtistsTable.TABLE_NAME + "." + ArtistsTable.COLUMN_ARTIST_NAME + ", " +
                "group_concat(" + GenresTable.TABLE_NAME + "." + GenresTable.COLUMN_GENRE_NAME + ", ','), " +
                ArtistsTable.TABLE_NAME + "." + ArtistsTable.COLUMN_ALBUMS + ", " +
                ArtistsTable.TABLE_NAME + "." + ArtistsTable.COLUMN_TRACKS + ", " +
                ArtistsTable.TABLE_NAME + "." + ArtistsTable.COLUMN_DESC + ", " +
                ArtistsTable.TABLE_NAME + "." + ArtistsTable.COLUMN_COVER_BIG + ", " +
                ArtistsTable.TABLE_NAME + "." + ArtistsTable.COLUMN_COVER_SMALL +
                " FROM " +
                ArtistsTable.TABLE_NAME +
                " LEFT JOIN " +
                ArtistsGenresTable.TABLE_NAME +
                " ON " +
                ArtistsTable.TABLE_NAME + "." + ArtistsTable.COLUMN_ARTIST_ID +
                " = " +
                ArtistsGenresTable.TABLE_NAME + "." + ArtistsGenresTable.COLUMN_ARTIST_ID +
                " LEFT JOIN " +
                GenresTable.TABLE_NAME +
                " ON " +
                ArtistsGenresTable.TABLE_NAME + "." + ArtistsGenresTable.COLUMN_GENRE_ID +
                " = " +
                GenresTable.TABLE_NAME + "." + GenresTable.COLUMN_GENRE_ID +
                " GROUP BY " +
                ArtistsGenresTable.TABLE_NAME + "." + ArtistsTable.COLUMN_ARTIST_ID;
    }

    public static String getDeleteViewQuery()
    {
        return "DROP VIEW " + VIEW_NAME;
    }
}
