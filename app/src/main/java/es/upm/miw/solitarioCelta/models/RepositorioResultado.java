package es.upm.miw.solitarioCelta.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import es.upm.miw.solitarioCelta.MainActivity;

import static es.upm.miw.solitarioCelta.models.ResultadoContract.tablaResultado;

public class RepositorioResultado extends SQLiteOpenHelper {
    private static final String NOMBRE_FICHERO = tablaResultado.TABLE_NAME + ".db";
    private static final int DATABASE_VERSION = 1;


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + tablaResultado.TABLE_NAME + " (" +
                    tablaResultado._ID + " INTEGER PRIMARY KEY," +
                    tablaResultado.COL_NAME_NOMBRE + " TEXT," +
                    tablaResultado.COL_NAME_NUMFICHAS + " INT," +
                    tablaResultado.COL_NAME_HORA + " TEXT," +
                    tablaResultado.COL_NAME_FECHA + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + tablaResultado.TABLE_NAME;

    public RepositorioResultado(Context context){
        super(context,NOMBRE_FICHERO, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db) {
        Log.i(MainActivity.LOG_KEY, "SQL: " + SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
