package ru.yandex.yamblz.content_provider.data.repository;

import java.util.List;
import ru.yandex.yamblz.content_provider.data.entity.Artist;
import rx.Observable;

/**
 * Created by platon on 31.07.2016.
 */
public interface DataSource
{
    Observable<List<Artist>> artists();
    void put(List<Artist> artists);
    void delete();
    int size();
}