package ru.yandex.yamblz.cp.data.entity;

/**
 * Created by platon on 11.08.2016.
 */
public class Genre
{
    private int id;

    private String name;

    public Genre(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (id != genre.id) return false;
        return name.equals(genre.name);
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
