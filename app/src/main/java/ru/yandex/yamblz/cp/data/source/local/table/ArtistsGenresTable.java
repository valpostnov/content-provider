package ru.yandex.yamblz.cp.data.source.local.table;

/**
 * Created by platon on 11.08.2016.
 */
public class ArtistsGenresTable
{
    public static final String TABLE_NAME = "artist_genre";
    public static final String COLUMN_ARTIST_ID = "artist_id";
    public static final String COLUMN_GENRE_ID = "genre_id";

    private ArtistsGenresTable()
    {
        throw new IllegalStateException("No instances please");
    }

    public static String getCreateTableQuery()
    {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ARTIST_ID + " INTEGER REFERENCES " +
                ArtistsTable.TABLE + "(" +
                ArtistsTable.COLUMN_ARTIST_ID + ") ON UPDATE CASCADE ON DELETE CASCADE," +
                COLUMN_GENRE_ID + " INTEGER REFERENCES " +
                GenresTable.TABLE + "(" +
                GenresTable.COLUMN_GENRE_ID + ") ON UPDATE CASCADE ON DELETE CASCADE );";
    }

    public static String getDeleteTableQuery()
    {
        return "DROP TABLE IF EXIST " + TABLE_NAME;
    }
}
