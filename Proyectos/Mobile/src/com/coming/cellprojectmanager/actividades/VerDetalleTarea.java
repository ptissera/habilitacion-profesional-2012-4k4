package com.coming.cellprojectmanager.actividades;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.TareaBo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class VerDetalleTarea extends Activity {
	private TextView descipcion;
	private TareaBo tareaSeleccionada;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_detalle_tarea);
		descipcion = (TextView)findViewById(R.id.tareaDescriptionTextView);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	tareaSeleccionada = (TareaBo)extras.getSerializable(Common.EXTRAS_KEY_TAREA);
        	String detalle = tareaSeleccionada.getDescripcion();
        	if(detalle == null || detalle.isEmpty()) {
        		detalle = getString(R.string.sin_datos);
        	}
        	descipcion.setText(detalle);
        }
	}

}
