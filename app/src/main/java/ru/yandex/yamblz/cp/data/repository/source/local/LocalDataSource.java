package ru.yandex.yamblz.cp.data.repository.source.local;

import android.content.ContentResolver;

import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.DeleteQuery;
import com.pushtorefresh.storio.contentresolver.queries.Query;

import java.util.List;

import ru.yandex.yamblz.cp.Injection;
import ru.yandex.yamblz.cp.data.entity.Artist;
import ru.yandex.yamblz.cp.data.repository.DataSource;
import ru.yandex.yamblz.cp.data.repository.source.local.provider.ArtistsMeta;
import rx.Observable;

import static ru.yandex.yamblz.cp.data.repository.source.local.provider.ArtistsMeta.CONTENT_URI;

/**
 * Created by platon on 31.07.2016.
 */
public class LocalDataSource implements DataSource
{
    private StorIOContentResolver storIOContentResolver;

    public LocalDataSource(StorIOContentResolver storIOContentResolver)
    {
        this.storIOContentResolver = storIOContentResolver;
    }

    @Override
    public Observable<List<Artist>> artists()
    {
        return storIOContentResolver
                .get()
                .listOfObjects(Artist.class)
                .withQuery(Query.builder()
                        .uri(CONTENT_URI)
                        .build())
                .prepare()
                .asRxObservable();
    }

    @Override
    public void put(List<Artist> artists)
    {
        storIOContentResolver
                .put()
                .objects(artists)
                .prepare()
                .executeAsBlocking();
    }

    @Override
    public void delete()
    {
        storIOContentResolver
                .delete()
                .byQuery(DeleteQuery.builder()
                        .uri(CONTENT_URI)
                        .build())
                .prepare()
                .executeAsBlocking();
    }

    @Override
    public int size()
    {
        return storIOContentResolver
                .get()
                .numberOfResults()
                .withQuery(Query.builder()
                          .uri(CONTENT_URI)
                          .build())
                .prepare()
                .executeAsBlocking();
    }
}
