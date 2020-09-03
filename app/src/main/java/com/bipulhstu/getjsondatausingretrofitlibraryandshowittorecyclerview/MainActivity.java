package com.bipulhstu.getjsondatausingretrofitlibraryandshowittorecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pastebin.com/raw/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OurRetrofit ourRetrofit = retrofit.create(OurRetrofit.class);

        Call<List<DemoData>> listCall = ourRetrofit.getDataSet();

        listCall.enqueue(new Callback<List<DemoData>>() {
            @Override
            public void onResponse(Call<List<DemoData>> call, Response<List<DemoData>> response) {
                showIt(response.body());
            }

            @Override
            public void onFailure(Call<List<DemoData>> call, Throwable t) {

            }
        });


    }

    private void showIt(final List<DemoData> response) {

        HeroAdapter heroAdapter = new HeroAdapter(response, getApplicationContext(), new MyClick() {
            @Override
            public void onClickMe(View view, int position) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                //intent.addFlags(FLAG_ACTIVITY_NEW_TASK);

                //Send data to DetailsActivity using putExtra
                intent.putExtra("img", response.get(position).getImg());
                intent.putExtra("name", response.get(position).getName());
                intent.putExtra("description", response.get(position).getDescription());

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(heroAdapter);
    }
}