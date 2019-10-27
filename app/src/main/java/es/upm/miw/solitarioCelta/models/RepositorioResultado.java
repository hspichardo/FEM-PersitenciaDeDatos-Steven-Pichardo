package es.upm.miw.solitarioCelta.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    public Long add(String nombre, String fecha, String hora, int numFichas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tablaResultado.COL_NAME_NOMBRE,nombre);
        values.put(tablaResultado.COL_NAME_FECHA,fecha);
        values.put(tablaResultado.COL_NAME_HORA,hora);
        values.put(tablaResultado.COL_NAME_NUMFICHAS, numFichas);

        return db.insert(tablaResultado.TABLE_NAME,null,values);

    }

    public List<Resultado> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Resultado> resultados = new ArrayList<>();
        Cursor cursor = db.query(tablaResultado.TABLE_NAME,null,null,null,null,null,tablaResultado.COL_NAME_NUMFICHAS);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                resultados.add(cursor2Resultado(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();

        return resultados;
    }

    @NonNull
    private Resultado cursor2Resultado(@NonNull Cursor cursor){
        return new Resultado(
                cursor.getInt(cursor.getColumnIndex(tablaResultado.COL_NAME_ID)),
                cursor.getString(cursor.getColumnIndex(tablaResultado.COL_NAME_NOMBRE)),
                cursor.getInt(cursor.getColumnIndex(tablaResultado.COL_NAME_NUMFICHAS)),
                cursor.getString(cursor.getColumnIndex(tablaResultado.COL_NAME_HORA)),
                cursor.getString(cursor.getColumnIndex(tablaResultado.COL_NAME_FECHA))
        );
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tablaResultado.TABLE_NAME, null, null);
        db.close();
    }
}
