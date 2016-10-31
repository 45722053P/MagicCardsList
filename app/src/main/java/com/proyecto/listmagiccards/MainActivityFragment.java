package com.proyecto.listmagiccards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Cards> items;
    private CardAdapter adapter;


    public MainActivityFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View FragmentView =  inflater.inflate(R.layout.fragment_main, container, false);

        ListView listaCartas = (ListView) FragmentView.findViewById(R.id.listCads);


        items = new ArrayList<>();
        adapter = new CardAdapter(
                getContext(),
                R.layout.card_row,
                items
        );

        listaCartas.setAdapter(adapter);

        listaCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Aqui creamos un objeto carta para saber cual va a ser su posicion en el list view.
                Cards carta = (Cards)adapterView.getItemAtPosition(i);

                //Creamos el intent para que podamos abrir el details con la carta seleccionada.
                Intent intent = new Intent(getContext(),DetailsActivity.class);

                intent.putExtra("carta",carta);

                startActivity(intent);


            }
        });


        return FragmentView;
    }
    //Estos metodos son para las opciones del menu del fragment.
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cartas_refresh, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {

        super.onStart();
        refresh();

    }

    //En este metodo colocaremos la accion que hara el refresh que sera hacer una llamada a la api en segundo plano con el Asynctask.
    private void refresh() {

        refreshBackground refresh = new refreshBackground();
        refresh.execute();

    }

    private class refreshBackground extends AsyncTask<Void, Void, ArrayList<Cards>> {
        @Override
        protected ArrayList<Cards> doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

            String color = preferences.getString("color",null);
            String rareza = preferences.getString("rareza",null);


            LlamadaApi api = new LlamadaApi();

            ArrayList<Cards> result;

            if(rareza == null && color == null) {

                result = api.getCards();

            } else if(rareza.toLowerCase().equals("basic land")){

                result = api.getRarity(rareza);
            } else if(rareza.toLowerCase().equals("common")){

                result = api.getRarity(rareza);

            } else if(rareza.toLowerCase().equals("uncommon")){

                result = api.getRarity(rareza);

            } else if(rareza.toLowerCase().equals("rare")){

                result = api.getRarity(rareza);

            } else if(rareza.toLowerCase().equals("mythic rare")){

                result = api.getRarity(rareza);

            } else if(rareza.toLowerCase().equals("special")){

                result = api.getRarity(rareza);

            } else {

                result = api.getColour(color);

            }


            Log.d("DEBUG", result != null ? result.toString() : null);

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Cards> cartas) {

            adapter.clear();
            for (Cards carta : cartas) {

                adapter.add(carta);

            }
        }
    }
}
