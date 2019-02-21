package com.example.adastraonetask.adapter;

import android.content.Context;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adastraonetask.R;
import com.example.adastraonetask.entities.Player;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder> {

    private List<Player> players;
    private int rowLayout;
    Context context;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.player_layout)
        LinearLayout playerLayout;
        @BindView(R.id.firstName)
        TextView firstName;
        @BindView(R.id.lastName)
        TextView lastName;
        @BindView(R.id.position)
        TextView position;
        @BindView(R.id.team)
        TextView team;

        public PlayerViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
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
        final PlayerViewHolder viewHolder = new PlayerViewHolder(view);

        return new PlayerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PlayerViewHolder holder, final int position) {
        holder.firstName.setText(players.get(position).getFirstName());
        holder.lastName.setText(players.get(position).getLastName());
        holder.position.setText(players.get(position).getPosition());
        holder.team.setText(players.get(position).getTeam().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemClickListener!=null)
                {
                    itemClickListener.onItemClick(view, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setOnItemClick(OnItemClickListener listener)
    {
        this.itemClickListener = listener;
    }

}
