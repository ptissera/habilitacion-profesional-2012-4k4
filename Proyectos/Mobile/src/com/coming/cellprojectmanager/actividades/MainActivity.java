package com.coming.cellprojectmanager.actividades;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.SitioBo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView sitioNombreTextView;
	private Button seleccionarSitio;
	private ListView opcionesListView;
	private SitioBo sitioSeleccionado;
	private String opciones[] = {"Actualizar progreso tarea",
			"Registrar acontecimiento",
			"Modificar acontecimiento",
			"Consultar acontecimiento"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sitioNombreTextView = (TextView)findViewById(R.id.itemListaSitiosNombreTextView);
        sitioNombreTextView.setText("");
        sitioNombreTextView.setHint(R.string.nombre_sitio_hint);        
        seleccionarSitio = (Button)findViewById(R.id.buscarSitiosButton);
        seleccionarSitio.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SeleccionarSitio.class);
				startActivityForResult(intent, 0);
			}
		});
        opcionesListView = (ListView)findViewById(R.id.opcionesListView);
        opcionesListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opciones));
        opcionesListView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
        		switch (position) {
				case 0: // Actualizar tarea
				{
			        Intent intent = new Intent();
			        intent.setClass(MainActivity.this, ActualizarProgresoTarea.class);
			        Bundle extras = new Bundle();
			        extras.putSerializable(Common.EXTRAS_KEY_SITIO, sitioSeleccionado);
			        intent.putExtras(extras);
			        startActivityForResult(intent, 1);
					break;
				}
				case 1: // Registrar acontecimiento
				{
					Toast.makeText(MainActivity.this, "No implementado", Toast.LENGTH_SHORT).show();
					break;
				}
				case 2: // Modificar acontecimiento
				{
					Toast.makeText(MainActivity.this, "No implementado", Toast.LENGTH_SHORT).show();
					break;
				}
				case 3: // Consultar acontecimiento
				{
					Toast.makeText(MainActivity.this, "No implementado", Toast.LENGTH_SHORT).show();
					break;
				}				
				default:
					break;
				}
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode) {
    	case 0: // Seleccionar Sitio
    		if(resultCode == RESULT_OK) {
    			actualizarSitio(data);    			
    		}
    		break;
    	case 1: // Actualizar progreso tarea
    		if(resultCode == RESULT_OK) {
    			actualizarSitio(data);
    		}
    		break;
    	default:
    		break;
    	}
    }    
    
    private void actualizarSitio(Intent data) {
		if(data == null) {
			return;
		}
		Bundle extras = data.getExtras();
		if(extras == null) {
			return;
		}
		sitioSeleccionado = (SitioBo)extras.getSerializable(Common.EXTRAS_KEY_SITIO);
		if(sitioSeleccionado == null) {
            sitioNombreTextView.setText("");
    	} else {
        	sitioNombreTextView.setText(sitioSeleccionado.getNombre());
    	}    			    	
    }
}
