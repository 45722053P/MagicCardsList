package com.proyecto.listmagiccards;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.proyecto.listmagiccards.databinding.CardRowBinding;

import java.util.List;

/**
 * Created by 45722053p on 28/10/16.
 */

public class CardAdapter extends ArrayAdapter<Cards>{




    public CardAdapter(Context context, int resource, List<Cards> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cards card = getItem(position);
        Log.w("XXXXXXXXX",card.toString());

        CardRowBinding binding = null;

        //Ahora miraremos si esta inflada la view o si la esa reusando.

        if(convertView == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater,R.layout.card_row,parent,false);

        }else {

            binding = DataBindingUtil.getBinding(convertView);

        }

        //Ahora haremos los enlaces de los componentes de los layouts con el binding.
        //Colocamos los datos del JSON en los textView con el binding.

        Glide.with(getContext()).load(card.getImageUrl()).into(binding.fotoCard);
        binding.nameCard.setText("Name: " + card.getName());
        binding.typeCard.setText("Type: " + card.getType());


        return convertView;
    }
}
