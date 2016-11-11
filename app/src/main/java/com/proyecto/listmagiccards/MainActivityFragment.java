package com.proyecto.listmagiccards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.proyecto.listmagiccards.databinding.FragmentMainBinding;

import java.util.ArrayList;

import static com.proyecto.listmagiccards.DataBaseManage.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private CardsCursorAdapter adapter;
    private View FragmentView;

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
        FragmentMainBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);

        FragmentView = binding.getRoot();

        adapter = new CardsCursorAdapter(getContext(),Cards.class);

        binding.listCads.setAdapter(adapter);

        binding.listCads.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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


        getLoaderManager().initLoader(0,null,this);

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return getCursorLoader(getContext());

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        adapter.swapCursor(null);

    }


    private class refreshBackground extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

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


            borrarCartas(getContext());

            guardarCartas(result,getContext());



            return null;
        }
    }
}
