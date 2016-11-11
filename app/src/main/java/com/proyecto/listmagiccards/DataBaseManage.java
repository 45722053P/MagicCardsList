package com.proyecto.listmagiccards;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 45722053p on 08/11/16.
 */

public class DataBaseManage {

    private static UriHelper URI_HELPER = UriHelper.with(CardContentProvider.AUTHORITY);

    private static Uri CARD_URI = URI_HELPER.getUri(Cards.class);



    static void guardarCartas (ArrayList<Cards> cartas, Context context){

        cupboard().withContext(context).put(CARD_URI,Cards.class,cartas);

    }

    static void borrarCartas (Context context){

        cupboard().withContext(context).delete(CARD_URI, "_id > ?", "1");
    }

    static CursorLoader getCursorLoader(Context context) {

        return new CursorLoader(context, CARD_URI, null, null, null, null);

    }


}
