package ru.yandex.yamblz.content_provider.data.exception;

/**
 * Created by platon on 08.08.2016.
 */
public interface ErrorBundle
{
    Throwable getException();

    String getMessage();
}
