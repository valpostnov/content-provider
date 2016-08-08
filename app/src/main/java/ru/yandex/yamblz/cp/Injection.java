package ru.yandex.yamblz.cp;

import java.util.List;

import ru.yandex.yamblz.cp.dashboard.DashboardPresenterImpl;
import ru.yandex.yamblz.cp.dashboard.interfaces.DashboardPresenter;
import ru.yandex.yamblz.cp.data.entity.Artist;
import ru.yandex.yamblz.cp.data.entity.mapper.ArtistToStringMapper;
import ru.yandex.yamblz.cp.data.entity.mapper.TypeMapper;
import ru.yandex.yamblz.cp.data.repository.DataSource;
import ru.yandex.yamblz.cp.data.repository.ArtistsRepository;
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
