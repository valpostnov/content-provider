package ru.yandex.yamblz.cp;

import android.app.Application;

import ru.yandex.yamblz.cp.data.repository.ArtistsRepository;
import ru.yandex.yamblz.cp.data.repository.source.local.LocalDataSource;
import ru.yandex.yamblz.cp.data.repository.source.remote.RemoteDataSource;
import ru.yandex.yamblz.cp.util.NetworkManager;

/**
 * Created by platon on 02.08.2016.
 */
public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        NetworkManager.init(getApplicationContext());
    }
}
