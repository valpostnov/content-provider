package ru.yandex.yamblz.cp.data.source.local.table;

/**
 * Created by platon on 31.07.2016.
 */
public class ArtistsTable
{
    public static final String TABLE = "artists";
    public static final String COLUMN_ARTIST_ID = "artist_id";
    public static final String COLUMN_ARTIST_NAME = "artist_name";
    public static final String COLUMN_TRACKS = "tracks";
    public static final String COLUMN_ALBUMS = "albums";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_COVER_SMALL = "cover_small";
    public static final String COLUMN_COVER_BIG = "cover_big";

    private ArtistsTable()
    {
        throw new IllegalStateException("No instances please");
    }

    public static String getCreateTableQuery()
    {
        return "CREATE TABLE " + TABLE + " (" +
                COLUMN_ARTIST_ID + " INTEGER NOT NULL PRIMARY KEY, " +
                COLUMN_ARTIST_NAME + " TEXT NOT NULL, " +
                COLUMN_TRACKS + " INTEGER, " +
                COLUMN_ALBUMS + " INTEGER, " +
                COLUMN_DESC + " TEXT, " +
                COLUMN_COVER_SMALL + " TEXT, " +
                COLUMN_COVER_BIG + " TEXT ); ";
    }

    public static String getDeleteTableQuery()
    {
        return "DROP TABLE IF EXIST " + TABLE;
    }
}
