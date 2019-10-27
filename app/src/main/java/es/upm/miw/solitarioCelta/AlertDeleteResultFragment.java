package es.upm.miw.solitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlertDeleteResultFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final SCeltaListaResultados sCeltaListaResultados = (SCeltaListaResultados) getActivity();


        AlertDialog.Builder builder = new AlertDialog.Builder(sCeltaListaResultados);
        builder
                .setTitle(R.string.txtborrarTitulo)
                .setMessage(R.string.txtborrarPregunta)
                .setPositiveButton(
                        getString(R.string.txtborrarAfirmativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sCeltaListaResultados.repositorioResultado.deleteAll();
                                sCeltaListaResultados.resultadoAdapter.clear();
                                sCeltaListaResultados.lvResultados.setAdapter(sCeltaListaResultados.resultadoAdapter);
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.txtborrarNegativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }
                );

        return builder.create();
    }
}
