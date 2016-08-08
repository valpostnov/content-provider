package ru.yandex.yamblz.cp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cover implements Serializable
{
    @SerializedName("small")
    private String mCoverSmall;

    @SerializedName("big")
    private String mCoverBig;

    public Cover(String small, String big)
    {
        mCoverSmall = small;
        mCoverBig = big;
    }

    public String getSmall() {
        return mCoverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.mCoverSmall = coverSmall;
    }

    public String getCoverBig() {
        return mCoverBig;
    }

    public void setCoverBig(String coverBig) {
        this.mCoverBig = coverBig;
    }
}
