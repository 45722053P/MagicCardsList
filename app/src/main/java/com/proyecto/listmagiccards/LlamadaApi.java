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


        private static String BASE_URL = "https://api.magicthegathering.io/v1";

        static ArrayList<Cards> getRarity(String rareza) {
            Uri builtUri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .appendPath("cards")
                    .appendQueryParameter("rarity",rareza)
                    .build();
            String url = builtUri.toString();

            return llamada(url);
        }

    static ArrayList<Cards> getColour(String color) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("cards")
                .appendQueryParameter("colors",color)
                .build();
        String url = builtUri.toString();

        return llamada(url);
    }

    static ArrayList<Cards> getCards() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("cards")
                .build();
        String url = builtUri.toString();

        return llamada(url);
    }



    @Nullable
    private static ArrayList<Cards> llamada(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return procesaJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static ArrayList<Cards> procesaJson(String jsonResponse) {
        ArrayList<Cards> cartas = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCartas = data.getJSONArray("cards");

            for (int i = 0; i < jsonCartas.length(); i++) {
                JSONObject jsonCarta = jsonCartas.getJSONObject(i);

                Cards card = new Cards();
                card.setName(jsonCarta.getString("name"));

                if(jsonCarta.has("colors")) {

                    card.setColor(jsonCarta.getString("colors"));

                }else {

                    card.setColor("Sin Color");

                }

                card.setType(jsonCarta.getString("type"));
                card.setRarity(jsonCarta.getString("rarity"));
                card.setImageUrl(jsonCarta.getString("imageUrl"));

                if(jsonCarta.has("flavor")) {
                    card.setDescripcion(jsonCarta.getString("flavor"));
                }else{
                    card.setDescripcion("Sin Descripcion");
                }




                cartas.add(card);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;
    }
}
