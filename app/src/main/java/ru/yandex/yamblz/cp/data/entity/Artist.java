package ru.yandex.yamblz.cp.data.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Artist implements Serializable
{
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("genres")
    private String[] mGenres;

    @SerializedName("tracks")
    private int mTracks;

    @SerializedName("albums")
    private int mAlbums;

    @SerializedName("description")
    private String mDesc;

    @SerializedName("cover")
    private Cover mCover;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getGenres() {

        StringBuilder genres = new StringBuilder();

        if (mGenres.length > 1)
        {
            genres.append(mGenres[0]);
            for (int i = 1; i < mGenres.length; i++)
            {
                genres.append(", ");
                genres.append(mGenres[i]);
            }
        }
        else if (mGenres.length == 0)
        {
            genres.append("unknown");
        }
        else
        {
            genres.append(mGenres[0]);
        }

        return genres.toString();
    }
    public int getTracks() {
        return mTracks;
    }

    public int getAlbums() {
        return mAlbums;
    }

    public String getDesc() {
        return mDesc;
    }

    public Cover getCover() {
        return mCover;
    }

    public static Builder Builder()
    {
        return new Artist().new Builder();
    }

    public class Builder
    {

        public Builder setId(int id) {
            mId = id;
            return this;
        }

        public Builder setName(String name) {
            mName = name;
            return this;
        }

        public Builder setGenres(String[] genres) {
            mGenres = genres;
            return this;
        }

        public Builder setTracks(int tracks) {
            mTracks = tracks;
            return this;
        }

        public Builder setAlbums(int albums) {
            mAlbums = albums;
            return this;
        }

        public Builder setDesc(String desc) {
            mDesc = desc;
            return this;
        }

        public Builder setCover(Cover cover) {
            mCover = cover;
            return this;
        }

        public Artist build() {
            return Artist.this;
        }
    }
}
