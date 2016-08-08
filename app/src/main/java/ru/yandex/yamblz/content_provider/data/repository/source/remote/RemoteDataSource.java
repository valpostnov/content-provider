package ru.yandex.yamblz.content_provider.data.repository.source.remote;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.yandex.yamblz.content_provider.api.ArtistsApi;
import ru.yandex.yamblz.content_provider.data.entity.Artist;
import ru.yandex.yamblz.content_provider.data.repository.DataSource;
import rx.Observable;

/**
 * Created by platon on 02.08.2016.
 */
public class RemoteDataSource implements DataSource
{
    private static final String ENDPOINT = "http://download.cdn.yandex.net/";
    private ArtistsApi api;

    public RemoteDataSource()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api = retrofit.create(ArtistsApi.class);
    }

    @Override
    public Observable<List<Artist>> artists()
    {
        return api.getArtists();
    }

    @Override
    public void put(List<Artist> artists) {}

    @Override
    public void delete() {}

    @Override
    public int size()
    {
        return 0;
    }
}
