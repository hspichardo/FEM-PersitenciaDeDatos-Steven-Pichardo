package es.upm.miw.solitarioCelta;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import es.upm.miw.solitarioCelta.models.RepositorioResultado;

public class MainActivity extends AppCompatActivity {

	SCeltaViewModel miJuego;
    public static final String LOG_KEY = "MiW";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miJuego = ViewModelProviders.of(this).get(SCeltaViewModel.class);
        mostrarTablero();
    }

    /**
     * Se ejecuta al pulsar una ficha
     * Las coordenadas (i, j) se obtienen a partir del nombre del recurso, ya que el botón
     * tiene un identificador en formato pXY, donde X es la fila e Y la columna
     * @param v Vista de la ficha pulsada
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void fichaPulsada(@NotNull View v) {
        String resourceName = getResources().getResourceEntryName(v.getId());
        int i = resourceName.charAt(1) - '0';   // fila
        int j = resourceName.charAt(2) - '0';   // columna

        Log.i(LOG_KEY, "fichaPulsada(" + i + ", " + j + ") - " + resourceName);
        miJuego.jugar(i, j);
        Log.i(LOG_KEY, "#fichas=" + miJuego.numeroFichas());

        mostrarTablero();
        if (miJuego.juegoTerminado()) {
            // TODO guardar puntuación


            RepositorioResultado repositorioResultado = new RepositorioResultado(this);
            repositorioResultado.add(
                  getJugador(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalDateTime.now().format(DateTimeFormatter.ISO_TIME),
                    miJuego.numeroFichas()

            );
            new AlertDialogFragment().show(getFragmentManager(), "ALERT_DIALOG");
        }
    }

    /**
     * Visualiza el tablero
     */
    public void mostrarTablero() {
        RadioButton button;
        String strRId;
        String prefijoIdentificador = getPackageName() + ":id/p"; // formato: package:type/entry
        int idBoton;

        for (int i = 0; i < JuegoCelta.TAMANIO; i++)
            for (int j = 0; j < JuegoCelta.TAMANIO; j++) {
                strRId = prefijoIdentificador + i + j;
                idBoton = getResources().getIdentifier(strRId, null, null);
                if (idBoton != 0) { // existe el recurso identificador del botón
                    button = findViewById(idBoton);
                    button.setChecked(miJuego.obtenerFicha(i, j) == JuegoCelta.FICHA);
                }
            }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.opciones_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcAjustes:
                startActivity(new Intent(this, SCeltaPrefs.class));
                return true;
            case R.id.opcAcercaDe:
                startActivity(new Intent(this, AcercaDe.class));
                return true;
            case R.id.opcReiniciarPartida:
                new AlertRestartFragment().show(getSupportFragmentManager(),"ALERT DIALOG");
                return true;
            case R.id.opcGuardarPartida:
                guardarPartida();
                return true;
            case R.id.opcRecuperarPartida:
                new AlertLoadGameFragment().show(getFragmentManager(),"ALERT DIALOG");
                return true;
            case R.id.opcMejoresResultados:
                startActivity(new Intent(this,SCeltaListaResultados.class));
                return true;

            // TODO!!! resto opciones

            default:
                Snackbar.make(
                        findViewById(android.R.id.content),
                        getString(R.string.txtSinImplementar),
                        Snackbar.LENGTH_LONG

                ).show();
        }
        return true;
    }

    public void guardarPartida(){

        String tableroActual = miJuego.serializaTablero();
        try {
            FileOutputStream fos = openFileOutput(getString(R.string.ficheroGuardarPartida), Context.MODE_PRIVATE);
            fos.write(tableroActual.getBytes());
            fos.close();
            Toast.makeText(this,
                    getString(R.string.positivoGuardarPartida),
                    Toast.LENGTH_SHORT).show();
        }catch (IOException e) {
            Toast.makeText(this,
                    getString(R.string.negativoGuardarPartida),
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void cargarPartida(){
        try {
            BufferedReader fin = new BufferedReader(
                    new InputStreamReader(openFileInput(getString(R.string.ficheroGuardarPartida))));
            String juego = fin.readLine();
            miJuego.deserializaTablero(juego);
            mostrarTablero();

            Toast.makeText(this,
                    getString(R.string.positivoCargarPartida),
                    Toast.LENGTH_SHORT).show();
        }catch (IOException e) {
            Toast.makeText(this,
                    getString(R.string.negativoCargarPartida),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private String getJugador() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        return settings.getString(getString(R.string.key_nombre_jugador), getString(R.string.default_jugador));
    }


}
