package com.example.adastraonetask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.adastraonetask.R;
import com.example.adastraonetask.adapter.PlayersAdapter;
import com.example.adastraonetask.entities.Item;
import com.example.adastraonetask.entities.Player;
import com.example.adastraonetask.entities.Team;
import com.example.adastraonetask.util.ApiService;
import com.example.adastraonetask.util.ApiUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.players_recycler_view)
    RecyclerView recyclerView;

    private PlayersAdapter playerAdapter;
    List<Player> players;

    public MainActivity getThis() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = ApiUtils.getAPIService();

        Call<Item> call = apiService.getPlayers();
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                int statusCode = response.code();
                players = new ArrayList<>();
                players = response.body().getData();

                playerAdapter = new PlayersAdapter(players, R.layout.list_item_player, getApplicationContext());

                recyclerView.setAdapter(playerAdapter);
                setOnItemListener();
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }

    public void setOnItemListener() {
        if (playerAdapter != null) {
            playerAdapter.setOnItemClick(new PlayersAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    Player player = players.get(position);
                    Team team = player.getTeam();

                    Gson teamObj = new Gson();
                    String target = teamObj.toJson(team);

                    Intent intent = new Intent(getThis(), DetailsActivity.class);
                    intent.putExtra("teamObj", target);
                    startActivity(intent);

                }
            });
        }
    }

}
