package es.upm.miw.solitarioCelta;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import es.upm.miw.solitarioCelta.databinding.ResultadoItemBinding;
import es.upm.miw.solitarioCelta.models.Resultado;

public class ResultadoAdapter extends ArrayAdapter {
    private static LayoutInflater layoutInflater;
    private Context context;
    private int idLayout;
    private List<Resultado> resultados;


    public ResultadoAdapter(@NonNull Context context, int idLayout, @NonNull List<Resultado> resultados) {
        super(context, idLayout, resultados);
        this.context =context;
        this.idLayout = idLayout;
        this.resultados = resultados;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (null == layoutInflater) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        ResultadoItemBinding resultadoItemBinding = DataBindingUtil.getBinding(convertView);
        if (resultadoItemBinding == null) {
            resultadoItemBinding = DataBindingUtil.inflate(layoutInflater, idLayout, parent, false);
        }
        resultadoItemBinding.setResultado(resultados.get(position));
        resultadoItemBinding.executePendingBindings();
        return resultadoItemBinding.getRoot();
    }
}
