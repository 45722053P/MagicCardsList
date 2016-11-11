package com.proyecto.listmagiccards;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.proyecto.listmagiccards.databinding.CardRowBinding;

/**
 * Created by alex on 11/11/2016.
 */


//En esta clase model se refiera a una carta a la hora de hacer los set podemos ver que elegimos model para settearlos.
public class CardsCursorAdapter extends CupboardCursorAdapter<Cards> {


    public CardsCursorAdapter(Context context, Class<Cards> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Cards model, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        CardRowBinding cardRowBinding = DataBindingUtil.inflate(inflater,R.layout.card_row,parent,false);

        return cardRowBinding.getRoot();

    }

    @Override
    public void bindView(View view, Context context, Cards model) {

        CardRowBinding cardRowBinding = DataBindingUtil.getBinding(view);

        cardRowBinding.nameCard.setText(model.getName());
        cardRowBinding.typeCard.setText(model.getType());
        Glide.with(context).load(model.getImageUrl()).into(cardRowBinding.fotoCard);


    }
}
