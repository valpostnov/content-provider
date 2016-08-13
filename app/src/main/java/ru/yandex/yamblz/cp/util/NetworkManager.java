package ru.yandex.yamblz.cp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by platon on 11.05.2016.
 */
public class NetworkManager implements INetworkManager
{
    private static NetworkManager sInstance;
    private Context context;

    public static void init(Context context)
    {
        sInstance = new NetworkManager(context);
    }

    public static NetworkManager getManager()
    {
        return sInstance;
    }

    private NetworkManager(Context context)
    {
        this.context = context;
    }

    @Override
    public boolean networkIsAvailable()
    {
        ConnectivityManager connectivity
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivity.getActiveNetworkInfo();

        return  (activeInfo != null && activeInfo.isConnected());
    }

    @Override
    public boolean cellularIsAvailable()
    {
        return false;
    }

    @Override
    public boolean wifiIsAvailable()
    {
        return false;
    }
}
