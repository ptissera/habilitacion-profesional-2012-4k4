package com.coming.cellprojectmanager.actividades;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.AcontecimientoBo;
import com.coming.cellprojectmanager.utils.Utils;

public class VerAcontecimiento extends Activity {
	private AcontecimientoBo acontecimientoSeleccionado;
	private TextView idTextView;
	private TextView tipoTextView;
	private TextView fechaCreacion;
	private TextView descripcion;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_acontecimiento);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	acontecimientoSeleccionado = (AcontecimientoBo)extras.getSerializable(Common.EXTRAS_KEY_ACONTECIMIENTO);        	
        }
        idTextView = (TextView)findViewById(R.id.idAcontecimientoTextView);
        tipoTextView = (TextView)findViewById(R.id.nombreTipoAcontecimientoTextView);
        fechaCreacion = (TextView)findViewById(R.id.fechaCreacionTextView);
        descripcion = (TextView)findViewById(R.id.descripcionTextView);
        if(acontecimientoSeleccionado != null) {
        	idTextView.setText(acontecimientoSeleccionado.getId().toString());
        	tipoTextView.setText(acontecimientoSeleccionado.getNombreTipoAcontecimiento());
        	String fecha = Utils.fechaToFrontendString(acontecimientoSeleccionado.getFechaCreacion());
        	fechaCreacion.setText(getString(R.string.creado_el) + " " + fecha);
        	descripcion.setText(acontecimientoSeleccionado.getDescripcion());
        } else {
        	String sinDatos = getString(R.string.sin_datos);
        	idTextView.setText(sinDatos);
        	tipoTextView.setText(sinDatos);
        	fechaCreacion.setText(sinDatos);
        	descripcion.setText(sinDatos);
        }
    }
	
}
