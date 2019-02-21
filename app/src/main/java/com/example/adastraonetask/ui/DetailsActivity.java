package com.example.adastraonetask.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.example.adastraonetask.R;
import com.example.adastraonetask.entities.Team;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.abbreviation)
    TextView abbreviation;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.conference)
    TextView conference;
    @BindView(R.id.division)
    TextView division;
    @BindView(R.id.full_name)
    TextView fullName;
    @BindView(R.id.name)
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Gson teamObj = new Gson();
        String target = getIntent().getStringExtra("teamObj");
        Team team = teamObj.fromJson(target, Team.class);

        abbreviation.setText(team.getAbbreviation());
        city.setText(team.getCity());
        conference.setText(team.getConference());
        fullName.setText(team.getFullName());
        division.setText(team.getDivision());
        name.setText(team.getName());

    }

}
