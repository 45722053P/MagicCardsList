package com.proyecto.listmagiccards;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.alexvasilkov.events.Events;

import java.util.ArrayList;

import static com.proyecto.listmagiccards.DataBaseManage.borrarCartas;
import static com.proyecto.listmagiccards.DataBaseManage.guardarCartas;

/**
 * Created by alex on 11/11/2016.
 */

public class RefreshBackground extends AsyncTask<Void,Void,Void> {

    private Context context;

    RefreshBackground(Context context){

        this.context = context;

    }



    //Este metodo se ejecutara antes como su nombre indica preExecute,y muestra el Dialog con nuestro mensaje anteriormente puesto.
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        Events.post("Empieza la descarga");

    }

    @Override
    protected Void doInBackground(Void... voids) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String color = preferences.getString("color",null);
        String rareza = preferences.getString("rareza",null);


        ArrayList<Cards> result;

        if(rareza.equals("") && color.equals("")) {

            result = LlamadaApi.getCards();

        } else if(rareza.toLowerCase().equals("basic land")){

            result = LlamadaApi.getRarity(rareza);

        } else if(rareza.toLowerCase().equals("common")){

            result = LlamadaApi.getRarity(rareza);

        } else if(rareza.toLowerCase().equals("uncommon")){

            result = LlamadaApi.getRarity(rareza);

        } else if(rareza.toLowerCase().equals("rare")){

            result = LlamadaApi.getRarity(rareza);

        } else if(rareza.toLowerCase().equals("mythic rare")){

            result = LlamadaApi.getRarity(rareza);

        } else if(rareza.toLowerCase().equals("special")){

            result = LlamadaApi.getRarity(rareza);

        } else {

            result = LlamadaApi.getColour(color);

        }


        Log.d("DEBUG", result != null ? result.toString() : null);


        borrarCartas(context);

        guardarCartas(result,context);

        return null;
    }

    //En este otro metodo es lo contrario una vez hecho el Dialog se va.
    @Override
    protected void onPostExecute(Void Void) {

        super.onPostExecute(Void);
        Events.post("Fin de la descarga");

    }
}

