package com.proyecto.listmagiccards;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import nl.qbusict.cupboard.Cupboard;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 45722053p on 08/11/16.
 */

public abstract class CupboardCursorAdapter <Cards> extends CursorAdapter{

    private final Cupboard mCupboard;
    private Class<Cards> mEntityClass;

    public CupboardCursorAdapter(Context context, Class<Cards> entityClass) {
        this(context, cupboard(), entityClass, null);
    }

    public CupboardCursorAdapter(Context context, Cupboard cupboard, Class<Cards> entityClass) {
        this(context, cupboard, entityClass, null);
    }

    public CupboardCursorAdapter(Context context, Cupboard cupboard, Class<Cards> entityClass, Cursor cursor) {
        super(context, cursor, false);
        this.mEntityClass = entityClass;
        this.mCupboard = cupboard;
    }

    public abstract View newView(Context context, Cards model, ViewGroup parent);

    public abstract void bindView(View view, Context context, Cards model);

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return newView(context, getItem(cursor.getPosition()), parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        bindView(view, context, getItem(cursor.getPosition()));
    }

    public Cards getItem(int position) {
        if (getCursor().moveToPosition(position)) {
            return mCupboard.withCursor(getCursor()).get(mEntityClass);
        } else {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
    }
}
