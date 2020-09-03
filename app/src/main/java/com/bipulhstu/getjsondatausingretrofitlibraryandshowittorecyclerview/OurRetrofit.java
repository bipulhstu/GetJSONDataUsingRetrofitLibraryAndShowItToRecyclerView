package com.bipulhstu.getjsondatausingretrofitlibraryandshowittorecyclerview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OurRetrofit {
    @GET("e52ZYwMq")
    Call<List<DemoData>> getDataSet();

}
