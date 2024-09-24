package com.backend.enums;
;


public enum Modele {
    CLIO(Marque.RENAULT), MEGANE(Marque.RENAULT),
    _208(Marque.PEUGEOT), _308(Marque.PEUGEOT),
    C3(Marque.CITROEN), C4(Marque.CITROEN),
    GOLF(Marque.VOLKSWAGEN), PASSAT(Marque.VOLKSWAGEN),
    FIESTA(Marque.FORD), FOCUS(Marque.FORD),
    YARIS(Marque.TOYOTA), COROLLA(Marque.TOYOTA),
    A3(Marque.AUDI), A4(Marque.AUDI), A6(Marque.AUDI),
    TUCSON(Marque.HYUNDAI), KONA(Marque.HYUNDAI), SONATA(Marque.HYUNDAI);

    private final Marque marque;

    Modele(Marque marque) {
        this.marque = marque;
    }

    public Marque getMarque() {
        return marque;
    }
}
