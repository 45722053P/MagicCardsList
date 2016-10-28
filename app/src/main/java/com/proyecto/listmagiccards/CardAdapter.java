package com.proyecto.listmagiccards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 45722053p on 28/10/16.
 */

public class CardAdapter extends ArrayAdapter<Cards>{

    TextView nombre,color,tipo,rareza;
    ImageView imageCard;


    public CardAdapter(Context context, int resource, List<Cards> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cards card = getItem(position);
        Log.w("XXXXXXXXX",card.toString());

        //Ahora miraremos si esta inflada la view o si la esa reusando.

        if(convertView == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.card_row,parent,false);

        }

        //Ahora haremos los enlaces de los componentes de los layouts.

        imageCard = (ImageView)convertView.findViewById(R.id.fotoCard);
        nombre = (TextView)convertView.findViewById(R.id.nameCard);
        tipo = (TextView)convertView.findViewById(R.id.typeCard);
        rareza = (TextView)convertView.findViewById(R.id.rarityCard);
        color = (TextView)convertView.findViewById(R.id.colorCard);


        //Colocamos los datos del JSON en los textView.

        Glide.with(getContext()).load(card.getImageUrl()).into(imageCard);
        nombre.setText(card.getName());
        tipo.setText(card.getType());
        rareza.setText(card.getRarity());
        color.setText(card.getColor());


        return convertView;
    }
}
