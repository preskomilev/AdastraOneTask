package com.example.adastraonetask.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.adastraonetask.R;
import com.example.adastraonetask.adapter.PlayersAdapter;
import com.example.adastraonetask.entities.Item;
import com.example.adastraonetask.entities.Player;
import com.example.adastraonetask.util.ApiService;
import com.example.adastraonetask.util.ApiUtils;
import com.example.adastraonetask.util.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.players_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = ApiUtils.getAPIService();

        Call<Item> call = apiService.getPlayers();
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                int statusCode = response.code();
                List<Player> players = response.body().getData();
                recyclerView.setAdapter(new PlayersAdapter(players, R.layout.list_item_player, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }
}
