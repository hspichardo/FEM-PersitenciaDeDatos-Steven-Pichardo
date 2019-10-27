package es.upm.miw.solitarioCelta;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import es.upm.miw.solitarioCelta.models.RepositorioResultado;
import es.upm.miw.solitarioCelta.models.Resultado;

public class SCeltaListaResultados extends AppCompatActivity {
    public ListView lvResultados;
    public ResultadoAdapter resultadoAdapter;
    public RepositorioResultado repositorioResultado;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_layout);
        repositorioResultado = new RepositorioResultado(this);
        lvResultados = findViewById(R.id.lvResultados);
        List<Resultado> resultados = repositorioResultado.getAll();
        resultadoAdapter = new ResultadoAdapter(this,R.layout.resultado_item,resultados);
        lvResultados.setAdapter(resultadoAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_resultados, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.eliminarResultados:
                new AlertDeleteResultFragment().show(getFragmentManager(),"ALERT DIALOG");
                return true;
            default:
                Snackbar.make(
                        findViewById(android.R.id.content),
                        getString(R.string.txtSinImplementar),
                        Snackbar.LENGTH_LONG

                ).show();
        }
        return true;

        }
    }

