package com.coming.cellprojectmanager.actividades;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.TareaBo;
import com.coming.cellprojectmanager.utils.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class VerDetalleTarea extends Activity {
	private TextView idTextView;
    private TextView nombreTipoTextView;
    private TextView estadoTextView;
    private TextView fechasEstimadasTextView;
    private TextView fechasRealesTextView;
	private TextView observaciones;
	private TareaBo tareaSeleccionada;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_detalle_tarea);
        idTextView = (TextView) findViewById(R.id.itemListaTareaIdTextView);
        nombreTipoTextView = (TextView) findViewById(R.id.itemListaTareaNombreTipoTextView);
        estadoTextView = (TextView) findViewById(R.id.itemListaTareaEstadoTextView);
        fechasEstimadasTextView = (TextView) findViewById(R.id.itemListaTareaFechasEstimadasTextView);
        fechasRealesTextView = (TextView) findViewById(R.id.itemListaTareaFechasRealesTextView);
		observaciones = (TextView)findViewById(R.id.tareaDescriptionTextView);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	tareaSeleccionada = (TareaBo)extras.getSerializable(Common.EXTRAS_KEY_TAREA);
            idTextView.setText(tareaSeleccionada.getId().toString());
            nombreTipoTextView.setText(tareaSeleccionada.getNombreTipoTarea());
            estadoTextView.setText(tareaSeleccionada.getEstado());
            String inicioEstimado = Utils.fechaToFrontendString(tareaSeleccionada.getFechaInicioEstimada());
            String finEstimado = Utils.fechaToFrontendString(tareaSeleccionada.getFechaFinEstimada());
            String sinDatos = getString(R.string.sin_datos);
        	String text = getString(R.string.fechas_estimadas);
            if(inicioEstimado == null || inicioEstimado.isEmpty()) {
            	inicioEstimado = sinDatos;
            }
        	if(finEstimado == null || finEstimado.isEmpty()) {
        		finEstimado = sinDatos;
        	}
        	fechasEstimadasTextView.setText(text + " " + inicioEstimado + "-" + finEstimado);
            String inicioReal = Utils.fechaToFrontendString(tareaSeleccionada.getFechaInicioReal());
            String finReal = Utils.fechaToFrontendString(tareaSeleccionada.getFechaFinReal());
        	text = getString(R.string.fechas_reales);            
            if(inicioReal == null || inicioReal.isEmpty()) {
            	inicioReal = sinDatos;
            }
            if(finReal == null || finReal.isEmpty()) {
            	finReal = sinDatos;
            }
            fechasRealesTextView.setText(text + " " + inicioReal + "-" + finReal);        	
        	String detalle = tareaSeleccionada.getObservaciones();
        	if(detalle == null || detalle.isEmpty()) {
        		detalle = getString(R.string.sin_datos);
        	}
        	observaciones.setText(detalle);
        }
	}
}
