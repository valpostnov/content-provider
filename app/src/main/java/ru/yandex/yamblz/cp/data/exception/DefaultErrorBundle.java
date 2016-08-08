package ru.yandex.yamblz.cp.data.exception;

/**
 * Created by platon on 08.08.2016.
 */
public class DefaultErrorBundle implements ErrorBundle
{
    private static final String DEFAULT_ERROR_MSG = "Unknown error";
    private final Throwable throwable;

    public DefaultErrorBundle(Throwable exception)
    {
        this.throwable = exception;
    }

    @Override
    public Throwable getException()
    {
        return throwable;
    }

    @Override
    public String getMessage()
    {
        return (throwable != null) ? throwable.getMessage() : DEFAULT_ERROR_MSG;
    }
}
