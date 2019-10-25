package es.upm.miw.SolitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlertLoadGameFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(main);

        builder
                .setTitle(R.string.txtDialogoCargarPartidaTitulo)
                .setMessage(R.string.txtDialogoCargarPartidaPregunta)
                .setPositiveButton(
                        getString(R.string.txtDialogoCargarPartidaAfirmativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int wich) {
                                main.cargarPartida();
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.txtDialogoCargarPartidaNegativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int wich) {

                            }
                        }
                );
        return builder.create();
    }
}
