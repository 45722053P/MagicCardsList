package com.proyecto.listmagiccards;

import android.net.Uri;

import java.io.IOException;

/**
 * Created by alex on 20/10/2016.
 */

public class LlalamdaApi {
        private final String BASE_URL = "https://api.magicthegathering.io/v1/";

        String get100Cards() {
            Uri builtUri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .appendPath("cards")
                    .build();
            String url = builtUri.toString();

            try {
                String JsonResponse = HttpUtils.get(url);
                return JsonResponse;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

}
