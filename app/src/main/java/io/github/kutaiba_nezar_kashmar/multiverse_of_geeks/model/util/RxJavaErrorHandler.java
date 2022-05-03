package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.util;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.HttpException;

public class RxJavaErrorHandler
{
  //TODO: to use this in the ViewModel use the below example
  /*
  * .subscribe(new Flowable-----{@Override methods generated})
  * inside the onError add the following
  *if(t instanceof HttpException)
  * {
                        HttpException httpException = (HttpException) t;

                        if(httpException.code() == 400)
                            Log.d(TAG, "onError: BAD REQUEST");
                        else if(httpException.code() == 401)
                            Log.d(TAG, "onError: NOT AUTHORIZED");
                        else if(httpException.code() == 403)
                            Log.d(TAG, "onError: FORBIDDEN");
                        else if(httpException.code() == 404)
                            Log.d(TAG, "onError: NOT FOUND");
                        else if(httpException.code() == 500)
                            Log.d(TAG, "onError: INTERNAL SERVER ERROR");
                        else if(httpException.code() == 502)
                            Log.d(TAG, "onError: BAD GATEWAY");

                    }
                }
                * or cal this class code
  * */
  public static void handleError(HttpException httpException)
  {
    if (httpException.code() == 400)
    {
      Log.i("Cast HTTP error: 400 ", "BAD REQUEST");
    }
    else if (httpException.code() == 401)
    {
      Log.i("Cast HTTP error: 401 ", "NOT AUTHORIZED");
    }
    else if (httpException.code() == 403)
    {
      Log.i("Cast HTTP error: 403 ", "FORBIDDEN");
    }
    else if (httpException.code() == 404)
    {
      Log.i("Cast HTTP error: 404 ", "NOT FOUND");
    }
    else if (httpException.code() == 500)
    {
      Log.i("Cast HTTP error: 500 ", "INTERNAL SERVER ERROR");
    }
    else if (httpException.code() == 502)
    {
      Log.i("Cast HTTP error: 502 ", "BAD GATEWAY");
    }
  }
}
