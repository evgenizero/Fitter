package net.impl;

import repository.UserRepository;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by evgeniy on 1/9/16.
 */
public abstract class DefaultImpl implements UserRepository{
    private static final String ROOT_URL = "http://192.168.1.:8000";
    protected static Retrofit retrofit;

    public DefaultImpl() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

    }

}
