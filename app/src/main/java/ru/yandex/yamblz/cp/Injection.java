package ru.yandex.yamblz.cp;

import android.content.ContentResolver;

import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;

import java.util.List;

import ru.yandex.yamblz.cp.dashboard.DashboardPresenterImpl;
import ru.yandex.yamblz.cp.dashboard.interfaces.DashboardPresenter;
import ru.yandex.yamblz.cp.data.entity.Artist;
import ru.yandex.yamblz.cp.data.entity.mapper.ArtistToStringMapper;
import ru.yandex.yamblz.cp.data.entity.mapper.TypeMapper;
import ru.yandex.yamblz.cp.data.repository.DataSource;
import ru.yandex.yamblz.cp.data.repository.ArtistsRepository;
import ru.yandex.yamblz.cp.data.repository.source.local.LocalDataSource;
import ru.yandex.yamblz.cp.data.repository.source.local.provider.ArtistsMeta;
import ru.yandex.yamblz.cp.data.repository.source.remote.RemoteDataSource;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by platon on 05.08.2016.
 */
public class Injection
{
    public static DataSource provideRepository(DataSource local, DataSource remote)
    {
        return new ArtistsRepository(local, remote);
    }

    public static DataSource provideLocalDataSource(ContentResolver contentResolver)
    {
        return new LocalDataSource(contentResolver);
    }

    public static DataSource provideRemoteDataSource()
    {
        return new RemoteDataSource();
    }

    public static TypeMapper<List<Artist>, List<String>> provideMapper()
    {
        return new ArtistToStringMapper();
    }

    public static StorIOContentResolver provideStoreIOContentResolver(ContentResolver contentResolver)
    {
        return DefaultStorIOContentResolver.builder()
                .contentResolver(contentResolver)
                .addTypeMapping(Artist.class, ContentResolverTypeMapping.<Artist>builder()
                        .putResolver(ArtistsMeta.PUT_RESOLVER)
                        .getResolver(ArtistsMeta.GET_RESOLVER)
                        .deleteResolver(ArtistsMeta.DELETE_RESOLVER).build())
                .build();
    }
}
