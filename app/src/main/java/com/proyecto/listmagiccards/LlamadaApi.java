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

        ArrayList<Cards> getRarity(String rareza) {
            Uri builtUri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .appendPath("cards")
                    .appendQueryParameter("rarity",rareza)
                    .build();
            String url = builtUri.toString();

            return llamada(url);
        }

    ArrayList<Cards> getColour(String color) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("cards")
                .appendQueryParameter("colors",color)
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
            JSONArray jsonCard = data.getJSONArray("cards");

            for (int i = 0; i < jsonCard.length(); i++) {
                JSONObject jsonCards = jsonCard.getJSONObject(i);

                Cards card = new Cards();
                card.setName(jsonCards.getString("name"));
                card.setColor(jsonCards.getString("colors"));
                card.setType(jsonCards.getString("type"));
                card.setRarity(jsonCards.getString("rarity"));
                card.setImageUrl(jsonCards.getString("imageUrl"));
                cartas.add(card);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;
    }
}
