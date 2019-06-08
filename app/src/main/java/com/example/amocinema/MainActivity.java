package com.example.amocinema;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    ImageView filme;
    private ArrayList<Filme> filmes;
    private String order = "popular";
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        filmes =new ArrayList<>();
        RecyclerView rvAmoFilmes = findViewById(R.id.rv_amo_cinema);

        GridLayoutManager grid = new GridLayoutManager(this,2);
        rvAmoFilmes.setLayoutManager(grid);
        rvAmoFilmes.setHasFixedSize(true);

        adapter=new MovieAdapter(this,filmes);
        rvAmoFilmes.setAdapter(adapter);
        adapter.setmOnItemClickLister(new OnItemClickListener() {
            @Override
            public void OnItemCLick(int pos) {
                Intent intent = new Intent(MainActivity.this, Item_clicado_amo_filmes.class);
                intent.putExtra("filme",filmes.get(pos));
                startActivity(intent);
               // Toast.makeText(MainActivity.this, "item "+ pos + "clicado", Toast.LENGTH_SHORT).show();
            }
        });
        carregaFilmes();
    }

    private void carregaFilmes() {
        URL url =NetworkUtils.buildURI(order);
        new MoviesQueryTask().execute(url);
    }

    class MoviesQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(URL... param) {

            URL searchUrl = param[0];
            String jsonMovies = "";
            try {
                jsonMovies = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                 e.printStackTrace();
            }

            return jsonMovies;

        }
        @Override
        protected void onPostExecute(String jsonMovies) {
            super.onPostExecute(jsonMovies);

            if (!jsonMovies.isEmpty() && !jsonMovies.equals("")) {
                filmes = getMovies(jsonMovies);
                filmes = getMovies(jsonMovies);
                adapter.setMovies(filmes);
                adapter.notifyDataSetChanged();

            }

        }
    }

    private ArrayList<Filme> getMovies(String jsonString) {

        ArrayList<Filme> movies = new ArrayList<>();

        try {
            JSONObject pageJson = new JSONObject(jsonString);
            JSONArray resultsJSON = pageJson.getJSONArray("results");


            JSONObject movieJson;

            for (int i=0; i<resultsJSON.length();i++){
                movieJson = new JSONObject(resultsJSON.getString(i));
                movies.add(new Filme(movieJson));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }

    }

