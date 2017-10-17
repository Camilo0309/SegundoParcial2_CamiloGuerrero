package com.jonmid.segundoparcial;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jonmid.segundoparcial.Adapter.TeamAdapterCamiloGuerrero;
import com.jonmid.segundoparcial.Connection.Connection;
import com.jonmid.segundoparcial.Model.TeamModelCamiloGuerrero;
import com.jonmid.segundoparcial.Parser.TeamJsonCamiloGuerrero;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class TeamActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    TeamAdapterCamiloGuerrero teamAdapterCamiloGuerrero;
    List<TeamModelCamiloGuerrero> teamModelCamiloGuerreroList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        progressBar = (ProgressBar) findViewById(R.id.id_pb_progressbar);
        recyclerView = (RecyclerView) findViewById(R.id.id_rv_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadData();

    }


    //###########################################TAREA ASYNTASK#############################################################
    public class TaskCountry extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = Connection.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                teamModelCamiloGuerreroList = TeamJsonCamiloGuerrero.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();
            progressBar.setVisibility(View.GONE);

        }
    }

    public void processData(){
        teamAdapterCamiloGuerrero = new TeamAdapterCamiloGuerrero(teamModelCamiloGuerreroList, getApplicationContext());
        recyclerView.setAdapter(teamAdapterCamiloGuerrero);
    }
//#####################################################################################################

    public Boolean isOnLine(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }

    public void loadData(){
        if (isOnLine()){
            TaskCountry taskCountry = new TaskCountry();
            taskCountry.execute("http://api.football-data.org/v1/competitions/445/teams");
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }
}
