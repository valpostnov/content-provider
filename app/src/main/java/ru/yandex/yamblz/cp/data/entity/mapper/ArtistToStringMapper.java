package ru.yandex.yamblz.cp.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import ru.yandex.yamblz.cp.data.entity.Artist;

/**
 * Created by platon on 06.08.2016.
 */
public class ArtistToStringMapper implements TypeMapper<List<Artist>, List<String>>
{
    @Override
    public List<String> map(List<Artist> source)
    {
        List<String> content = new ArrayList<>();
        for (Artist a: source)
        {
            content.add(a.getName());
        }
        return content;
    }
}
