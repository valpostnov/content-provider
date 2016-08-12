package ru.yandex.yamblz.cp.data.entity;

/**
 * Created by platon on 11.08.2016.
 */
public class ArtistWithGenre
{
    private final Artist artist;

    private final Genre genre;

    public ArtistWithGenre(Artist artist, Genre genre)
    {
        this.artist = artist;
        this.genre = genre;
    }

    public Artist artist()
    {
        return artist;
    }

    public Genre genre()
    {
        return genre;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistWithGenre that = (ArtistWithGenre) o;

        if (!artist.equals(that.artist)) return false;
        return genre.equals(that.genre);

    }

    @Override
    public int hashCode()
    {
        int result = artist.hashCode();
        result = 31 * result + genre.hashCode();
        return result;
    }
}
