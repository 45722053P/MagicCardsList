package com.proyecto.listmagiccards;

/**
 * Created by 45722053p on 08/11/16.
 */

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class CardContentProvider extends CupboardContentProvider {
    // The content provider authority is used for building Uri's for the provider
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Cards.class);
    }

    public CardContentProvider() {
        super(AUTHORITY, 1);
    }
}