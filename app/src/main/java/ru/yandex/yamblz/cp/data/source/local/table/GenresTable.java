package ru.yandex.yamblz.cp.data.source.local.table;

/**
 * Created by platon on 11.08.2016.
 */
public class GenresTable
{
    public static final String TABLE = "genres";
    public static final String COLUMN_GENRE_ID = "id";
    public static final String COLUMN_GENRE_NAME = "genre";

    private GenresTable()
    {
        throw new IllegalStateException("No instances please");
    }

    public static String getCreateTableQuery()
    {
        return "CREATE TABLE " + TABLE + " (" +
                COLUMN_GENRE_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_GENRE_NAME + " TEXT NOT NULL ); ";
    }

    public static String getDeleteTableQuery()
    {
        return "DROP TABLE IF EXIST " + TABLE;
    }
}
