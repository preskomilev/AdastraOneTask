package com.example.adastraonetask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adastraonetask.R;
import com.example.adastraonetask.entities.Item;
import com.example.adastraonetask.entities.Player;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder> {

    private List<Player> players;
    private int rowLayout;
    private Context context;


    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        LinearLayout playerLayout;
        TextView firstName;
        TextView lastName;
        TextView position;
        TextView team;


        public PlayerViewHolder(View v) {
            super(v);

            playerLayout = (LinearLayout) v.findViewById(R.id.player_layout);
            firstName = (TextView) v.findViewById(R.id.firstName);
            lastName = (TextView) v.findViewById(R.id.lastName);
            position = (TextView) v.findViewById(R.id.position);
            team = (TextView) v.findViewById(R.id.team);

        }
    }

    public PlayersAdapter(List<Player> players, int rowLayout, Context context) {
        this.players = players;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PlayersAdapter.PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PlayerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PlayerViewHolder holder, final int position) {
        holder.firstName.setText(players.get(position).getFirstName());
        holder.lastName.setText(players.get(position).getLastName());
        holder.position.setText(players.get(position).getPosition());
        holder.team.setText(players.get(position).getTeam().getName());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

}
