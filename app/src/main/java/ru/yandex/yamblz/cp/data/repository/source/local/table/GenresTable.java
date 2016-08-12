package ru.yandex.yamblz.cp.data.repository.source.local.table;

/**
 * Created by platon on 11.08.2016.
 */
public class GenresTable
{
    public static final String TABLE_NAME = "genres";
    public static final String COLUMN_GENRE_ID = "id";
    public static final String COLUMN_GENRE_NAME = "genre_name";

    private GenresTable()
    {
        throw new IllegalStateException("No instances please");
    }

    public static String getCreateTableQuery()
    {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_GENRE_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_GENRE_NAME + " TEXT NOT NULL ); ";
    }

    public static String getDeleteTableQuery()
    {
        return "DROP TABLE " + TABLE_NAME;
    }
}
