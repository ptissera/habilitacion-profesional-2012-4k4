package com.coming.cellprojectmanager.actividades;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.TareaBo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class OpcionesDeTarea extends ListActivity {
	private TareaBo tareaSeleccionada;	
	private String opciones[] = {"Cambiar estado", "Ver detalles", "Ver acontecimientos"};
	
	private static final int REQUEST_CODE_CAMBIAR_ESTADO = 0;
	private static final int REQUEST_CODE_VER_TAREA = 1;
	private static final int REQUEST_CODE_VER_ACONTECIMIENTO = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	tareaSeleccionada = (TareaBo)extras.getSerializable(Common.EXTRAS_KEY_TAREA);
        }	
        getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long ind) {
        		switch (position) {
				case 0: // Cambiar estado tarea
				{
					Intent intent = new Intent(OpcionesDeTarea.this, SeleccionarEstado.class);
					Bundle extras = new Bundle();
					extras.putSerializable(Common.EXTRAS_KEY_TAREA, tareaSeleccionada);
					intent.putExtras(extras);
					startActivityForResult(intent, REQUEST_CODE_CAMBIAR_ESTADO);
					break;
				}
				case 1: // Ver detalles
				{
					Intent intent = new Intent(OpcionesDeTarea.this, VerDetalleTarea.class);
					Bundle extras = new Bundle();
					extras.putSerializable(Common.EXTRAS_KEY_TAREA, tareaSeleccionada);
					intent.putExtras(extras);
					startActivityForResult(intent, REQUEST_CODE_VER_TAREA);
					break;
				}					
				case 2: // Ver acontecimientos
				{
					Intent intent = new Intent(OpcionesDeTarea.this, GestionarAcontecimientos.class);
					Bundle extras = new Bundle();
					extras.putSerializable(Common.EXTRAS_KEY_TAREA, tareaSeleccionada);
					intent.putExtras(extras);
					startActivityForResult(intent, REQUEST_CODE_VER_ACONTECIMIENTO);
					break;
				}
				default:
					break;
				}				
			}
		});
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opciones));
        getListView().setBackgroundColor(getResources().getColor(R.color.background));
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode) {
    	case REQUEST_CODE_CAMBIAR_ESTADO:
    		if(resultCode == RESULT_OK) {
    			if(data != null) {
        			Bundle extras = data.getExtras();
        			if(extras != null) {
            			TareaBo tareaConNuevoEstado = (TareaBo)data.getExtras().getSerializable(Common.EXTRAS_KEY_TAREA);
            			tareaSeleccionada.setEstado(tareaConNuevoEstado.getEstado());        				
        			}
    			}
    			setResult(RESULT_OK, data);
    			finish();
    		}
    		break;
    	case REQUEST_CODE_VER_TAREA: // Ver detalles
    	case REQUEST_CODE_VER_ACONTECIMIENTO: // Ver acontecimientos
    	default:
    		finish();
    		break;
    	}
    }	
}
