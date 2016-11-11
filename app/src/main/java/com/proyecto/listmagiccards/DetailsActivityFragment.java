package com.proyecto.listmagiccards;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.proyecto.listmagiccards.databinding.FragmentDetailsBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    private View detailsFragment;

    private FragmentDetailsBinding bindingDetails;




    public DetailsActivityFragment() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Ahora lo hacemos con el bindingDetails.
        bindingDetails = DataBindingUtil.inflate(
                inflater, R.layout.fragment_details, container, false);

        detailsFragment = bindingDetails.getRoot();


        Intent i = getActivity().getIntent();

        if(i != null){
             Cards carta = (Cards)i.getSerializableExtra("carta");

            if(carta != null){

                updateUi(carta);

            }

        }

        return detailsFragment;

    }

    private void updateUi(Cards carta){

        Log.d("CARTA",carta.toString());

        //Ahora hemos cambiado los find por el bindingDetails, se enlaza directamente con las id del layout sin necesidad de hacer los find.

        bindingDetails.nameCardDetail.setText("Name: " + carta.getName());
        bindingDetails.typeCardDetails.setText("Type: " + carta.getType());
        bindingDetails.colorCardDetails.setText("Color: " + carta.getColor());
        bindingDetails.rarityCardDetails.setText("Rarity: " + carta.getRarity());
        bindingDetails.descripcionCardDetails.setText("Descripción: " + carta.getDescripcion());

        Glide.with(getContext()).load(carta.getImageUrl()).into(bindingDetails.imageCardDetails);


    }

}
