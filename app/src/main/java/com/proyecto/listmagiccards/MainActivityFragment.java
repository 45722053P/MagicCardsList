package com.proyecto.listmagiccards;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;


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

        String[] data = {
                "Ejemplos",
                "Ejemplos",
                "Ejemplos",
                "Ejemplos",
                "Ejemplos",
                "Ejemplos",
                "Ejemplos"
        };

        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.card_row,
                R.id.nameCard,
                items
        );
        listaCartas.setAdapter(adapter);




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

    //En este metodo colocaremos la accion que hara el refresh que sera hacer una llamada a la api en segundo plano.
    private void refresh() {

        LlalamdaApi api = new LlalamdaApi();
        String result = api.get100Cards();
        Log.d("DEBUG", result);
    }

}
