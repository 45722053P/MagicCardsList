package com.proyecto.listmagiccards;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.alexvasilkov.events.Events;
import com.proyecto.listmagiccards.databinding.FragmentMainBinding;

import static com.proyecto.listmagiccards.DataBaseManage.getCursorLoader;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ProgressDialog loading;
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

        //Este progressDialog es para que muestre un mensaje mientras carga las cartas de la api, es decir las descarga.
        loading = new ProgressDialog(getContext());
        loading.setMessage("Cargando Cartas....");


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

    @Events.Subscribe("Empieza la descarga")
    void preRefresh() {
        loading.show();
    }

    @Events.Subscribe("Fin de la descarga")
    void afterRefresh() {
        loading.dismiss();
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
    public void onStart(){

        super.onStart();
        Events.register(this);
    }

    //En este metodo colocaremos la accion que hara el refresh que sera hacer una llamada a la api en segundo plano con el Asynctask.
    private void refresh() {

        RefreshBackground refreshbackground = new RefreshBackground(getActivity().getApplicationContext());
        refreshbackground.execute();

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

}
