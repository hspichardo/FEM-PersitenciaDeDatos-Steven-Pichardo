package es.upm.miw.solitarioCelta.models;

import android.provider.BaseColumns;

final public class ResultadoContract {
    private ResultadoContract(){

    }

    public static abstract class tablaResultado implements BaseColumns{
        static final String TABLE_NAME = "resultados";

        static final String COL_NAME_ID         = _ID;
        static final String COL_NAME_NOMBRE     = "nombre";
        static final String COL_NAME_NUMFICHAS = "numFichas";
        static final String COL_NAME_HORA     = "hora";
        static final String COL_NAME_FECHA    = "fecha";
        static final String COL_NAME_TIEMPO_JUGADO    = "tiempoJugado";
    }
}

