package com.example.adastraonetask.util;

import com.example.adastraonetask.entities.Item;
import com.example.adastraonetask.entities.Player;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("players")
    Call<Item> getPlayers();

}
