package com.example.celicaconect_10.Navegador.Menu.Turismo.Listener;


import com.example.celicaconect_10.Navegador.Menu.Turismo.Colecciones.Lugares;

import java.util.List;

public interface IFirebaseturismo {

    void onFirebaseloadSuccesss(List<Lugares> lugaresList);
    void onFirebaseLoadFailed(String mensage);

}
