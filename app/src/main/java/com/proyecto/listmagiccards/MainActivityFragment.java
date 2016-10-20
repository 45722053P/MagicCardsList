package com.proyecto.listmagiccards;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
}
