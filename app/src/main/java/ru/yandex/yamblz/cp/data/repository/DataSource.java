package ru.yandex.yamblz.cp.data.repository;

import java.util.List;
import ru.yandex.yamblz.cp.data.entity.Artist;
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