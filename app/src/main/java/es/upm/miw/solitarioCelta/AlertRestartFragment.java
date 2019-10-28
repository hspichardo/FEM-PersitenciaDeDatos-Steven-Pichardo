package es.upm.miw.solitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;

public class AlertRestartFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(main);

        builder
                .setTitle(R.string.txtDialogoReiniciarTitulo)
                .setMessage(R.string.txtDialogoReiniciarPregunta)
                .setPositiveButton(
                        getString(R.string.txtDialogoReiniciarAfirmativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int wich) {
                                main.miJuego.reiniciar();
                                main.cronometro.setBase(SystemClock.elapsedRealtime());
                                main.cronometro.start();
                                main.mostrarTablero();
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.txtDialogoReiniciarNegativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int wich) {

                            }
                        }
                );
        return builder.create();
    }
}
