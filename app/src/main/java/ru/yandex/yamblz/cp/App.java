package ru.yandex.yamblz.cp;

import android.app.Application;
import android.content.Context;

import ru.yandex.yamblz.cp.data.source.ArtistsRepository;
import ru.yandex.yamblz.cp.data.source.DataSource;
import ru.yandex.yamblz.cp.data.source.local.LocalDataSource;
import ru.yandex.yamblz.cp.data.source.remote.RemoteDataSource;
import ru.yandex.yamblz.cp.util.NetworkManager;

/**
 * Created by platon on 02.08.2016.
 */
public class App extends Application
{
    private DataSource artistRepository;

    public static App from(Context context)
    {
        return (App) context.getApplicationContext();
    }

    public DataSource getRepository()
    {
        return artistRepository;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        NetworkManager.init(this);
        DataSource local = new LocalDataSource(Injection.provideStoreIOContentResolver(getContentResolver()));
        artistRepository = new ArtistsRepository(local, new RemoteDataSource());
    }
}
