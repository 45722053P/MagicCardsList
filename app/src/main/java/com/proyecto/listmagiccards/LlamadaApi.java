package com.proyecto.listmagiccards;

import android.net.Uri;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alex on 20/10/2016.
 */

public class LlamadaApi {


        private final String BASE_URL = "https://api.magicthegathering.io/v1/";

        ArrayList<Cards> get100Cards() {
            Uri builtUri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .appendPath("cards")
                    .build();
            String url = builtUri.toString();

            return llamada(url);
        }
    @Nullable
    private ArrayList<Cards> llamada(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return procesaJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private ArrayList<Cards> procesaJson(String jsonResponse) {
        ArrayList<Cards> cartas = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonMovies = data.getJSONArray("cards");

            for (int i = 0; i < jsonMovies.length(); i++) {
                JSONObject jsonMovie = jsonMovies.getJSONObject(i);

                Cards card = new Cards();
                card.setName(jsonMovie.getString("name"));
                cartas.add(card);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;
    }
}
