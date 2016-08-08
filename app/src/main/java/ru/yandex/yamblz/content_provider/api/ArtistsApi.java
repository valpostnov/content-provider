package ru.yandex.yamblz.content_provider.api;


import java.util.List;

import retrofit2.http.GET;
import ru.yandex.yamblz.content_provider.data.entity.Artist;
import rx.Observable;

/**
 * Created by postnov on 12.04.2016.
 */
public interface ArtistsApi
{
    @GET("mobilization-2016/artists.json")
    Observable<List<Artist>> getArtists();
}
