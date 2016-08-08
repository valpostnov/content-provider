package ru.yandex.yamblz.content_provider;

import java.util.List;

import ru.yandex.yamblz.content_provider.dashboard.DashboardPresenterImpl;
import ru.yandex.yamblz.content_provider.dashboard.interfaces.DashboardPresenter;
import ru.yandex.yamblz.content_provider.data.entity.Artist;
import ru.yandex.yamblz.content_provider.data.entity.mapper.ArtistToStringMapper;
import ru.yandex.yamblz.content_provider.data.entity.mapper.TypeMapper;
import ru.yandex.yamblz.content_provider.data.repository.DataSource;
import ru.yandex.yamblz.content_provider.data.repository.ArtistsRepository;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by platon on 05.08.2016.
 */
public class Injection
{
    public static DashboardPresenter providePresenter(DataSource d, CompositeSubscription s,
                                                      TypeMapper<List<Artist>, List<String>> m)
    {
        return new DashboardPresenterImpl(d, s, m);
    }

    public static DataSource provideDataSource()
    {
        return ArtistsRepository.getRepository();
    }

    public static CompositeSubscription provideCompositeSubscription()
    {
        return new CompositeSubscription();
    }

    public static TypeMapper<List<Artist>, List<String>> provideMapper()
    {
        return new ArtistToStringMapper();
    }
}
