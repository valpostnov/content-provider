package ru.yandex.yamblz.cp;

import android.content.ContentResolver;

import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;

import java.util.List;

import ru.yandex.yamblz.cp.data.entity.Artist;
import ru.yandex.yamblz.cp.data.entity.mapper.ArtistToStringMapper;
import ru.yandex.yamblz.cp.data.entity.mapper.TypeMapper;
import ru.yandex.yamblz.cp.data.source.local.provider.ArtistsMeta;

/**
 * Created by platon on 05.08.2016.
 */
public class Injection
{
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
