package ru.yandex.yamblz.content_provider.data.entity.mapper;

/**
 * Created by platon on 06.08.2016.
 */
public interface TypeMapper<From, To>
{
    To map(From source);
}
