package ru.yandex.yamblz.content_provider.data.repository;

import java.util.List;

import ru.yandex.yamblz.content_provider.data.entity.Artist;
import rx.Observable;

/**
 * Created by platon on 02.08.2016.
 */
public class ArtistsRepositoryImpl implements DataSource
{
    private static ArtistsRepositoryImpl sInstance;
    private DataSource localDataSource;
    private DataSource remoteDataSource;

    public static void init(DataSource local, DataSource remote)
    {
        if (sInstance == null)
        {
            sInstance = new ArtistsRepositoryImpl(local, remote);
        }
    }

    public static ArtistsRepositoryImpl getRepository()
    {
        return sInstance;
    }

    private ArtistsRepositoryImpl(DataSource local, DataSource remote)
    {
        remoteDataSource = remote;
        localDataSource = local;
    }

    @Override
    public Observable<List<Artist>> artists()
    {
        if (localDataSource.size() != 0) return localDataSource.artists();
        return remoteDataSource.artists().doOnNext(this::put);
    }

    @Override
    public void put(List<Artist> artists)
    {
        localDataSource.put(artists);
    }

    @Override
    public void delete()
    {
        localDataSource.delete();
    }

    @Override
    public int size()
    {
        return localDataSource.size();
    }
}
