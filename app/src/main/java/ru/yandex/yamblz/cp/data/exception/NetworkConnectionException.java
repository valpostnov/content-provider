package ru.yandex.yamblz.cp.data.exception;

/**
 * Created by platon on 09.08.2016.
 */
public class NetworkConnectionException extends Exception
{
    private static final String DEFAULT_ERROR_MSG = "Отсутсвует подключение к сети";

    public NetworkConnectionException()
    {
        super(DEFAULT_ERROR_MSG);
    }

    public NetworkConnectionException(String message)
    {
        super(message);
    }

    public NetworkConnectionException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NetworkConnectionException(Throwable cause)
    {
        super(cause);
    }
}
