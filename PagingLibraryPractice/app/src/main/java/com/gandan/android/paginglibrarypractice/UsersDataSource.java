package com.gandan.android.paginglibrarypractice;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by XPS on 2018-04-22.
 */

public class UsersDataSource extends ItemKeyedDataSource<Long, User> implements RedditService {


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<User> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<User> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<User> callback) {

    }

    @NonNull
    @Override
    public Long getKey(@NonNull User item) {
        return null;
    }

    @Override
    public Call<ListingResponse> getTop(String subReddit, String before, int limit) {
        return null;
    }
}
