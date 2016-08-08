package ru.yandex.yamblz.content_provider;

import android.app.Application;

import ru.yandex.yamblz.content_provider.data.repository.ArtistsRepositoryImpl;
import ru.yandex.yamblz.content_provider.data.repository.source.local.LocalDataSource;
import ru.yandex.yamblz.content_provider.data.repository.source.remote.RemoteDataSource;

/**
 * Created by platon on 02.08.2016.
 */
public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        ArtistsRepositoryImpl.init(new LocalDataSource(getContentResolver()), new RemoteDataSource());
    }
}
