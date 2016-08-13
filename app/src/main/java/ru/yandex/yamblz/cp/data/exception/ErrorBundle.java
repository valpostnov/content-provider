package ru.yandex.yamblz.cp.data.exception;

/**
 * Created by platon on 08.08.2016.
 */
public interface ErrorBundle
{
    Throwable getException();

    String getMessage();
}
