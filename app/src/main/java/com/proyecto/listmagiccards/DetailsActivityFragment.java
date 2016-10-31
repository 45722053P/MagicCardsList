package com.proyecto.listmagiccards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    private View detailsFragment;

    private ImageView imageDetails;
    private TextView nameDetails,typeDetails,colorDetails,rarityDetails,descripcionDetails;





    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        detailsFragment = inflater.inflate(R.layout.fragment_details, container, false);

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

        //Ahora mismo hacemos los findView para todos los elementos de dicho layout.

        imageDetails = (ImageView) detailsFragment.findViewById(R.id.imageCardDetails);
        nameDetails = (TextView)detailsFragment.findViewById(R.id.nameCardDetail);
        typeDetails = (TextView)detailsFragment.findViewById(R.id.typeCardDetails);
        colorDetails = (TextView)detailsFragment.findViewById(R.id.colorCardDetails);
        rarityDetails = (TextView)detailsFragment.findViewById(R.id.rarityCardDetails);
        descripcionDetails = (TextView)detailsFragment.findViewById(R.id.descripcionCardDetails);


        nameDetails.setText("Name: " + carta.getName());
        typeDetails.setText("Type: " + carta.getType());
        colorDetails.setText("Color: " + carta.getColor());
        rarityDetails.setText("Rarity: " + carta.getRarity());
        descripcionDetails.setText("Descripción: " + carta.getDescripcion());

        Glide.with(getContext()).load(carta.getImageUrl()).into(imageDetails);


    }

}
