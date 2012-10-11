package com.coming.cellprojectmanager.actividades;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.coming.cellprojectmanager.modelo.AcontecimientoBo;
import com.coming.cellprojectmanager.modelo.TareaBo;

public class OpcionesDeAcontecimiento extends ListActivity {
	private TareaBo tareaSeleccionada;
	private AcontecimientoBo acontecimientoSeleccionado;	
	private String opciones[] = {"Ver detalles", "Modificar"};

	private static final int REQUEST_CODE_VER_DETALLES = 0;
	private static final int REQUEST_CODE_MODIFICAR = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	tareaSeleccionada = (TareaBo)extras.getSerializable(Common.EXTRAS_KEY_TAREA);
        	acontecimientoSeleccionado = (AcontecimientoBo)extras.getSerializable(Common.EXTRAS_KEY_ACONTECIMIENTO);
        }	
        getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long ind) {
        		switch (position) {
				case 0: // Ver detalles
				{
					Intent intent = new Intent(OpcionesDeAcontecimiento.this, VerAcontecimiento.class);
					Bundle extras = new Bundle();
					extras.putSerializable(Common.EXTRAS_KEY_TAREA, tareaSeleccionada);
					extras.putSerializable(Common.EXTRAS_KEY_ACONTECIMIENTO, acontecimientoSeleccionado);
					intent.putExtras(extras);
					startActivityForResult(intent, REQUEST_CODE_VER_DETALLES);
					break;
				}
				case 1: // Modificar
				{
					Intent intent = new Intent(OpcionesDeAcontecimiento.this, NuevoAcontecimiento.class);
					Bundle extras = new Bundle();
					extras.putSerializable(Common.EXTRAS_KEY_TAREA, tareaSeleccionada);
					extras.putSerializable(Common.EXTRAS_KEY_ACONTECIMIENTO, acontecimientoSeleccionado);
					intent.putExtras(extras);
					startActivityForResult(intent, REQUEST_CODE_MODIFICAR);
					break;
				}
				default:
					break;
				}				
			}
		});
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opciones));
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode) {
    	case REQUEST_CODE_MODIFICAR:
    		if(resultCode == RESULT_OK) {
				setResult(RESULT_OK, data);
    			finish();
    		}
    		break;
    	case REQUEST_CODE_VER_DETALLES:
    	default:
    		finish();
    		break;
    	}
    }	
}
