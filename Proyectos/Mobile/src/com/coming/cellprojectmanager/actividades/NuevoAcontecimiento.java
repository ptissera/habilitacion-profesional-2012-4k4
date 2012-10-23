package com.coming.cellprojectmanager.actividades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.AcontecimientoBo;
import com.coming.cellprojectmanager.modelo.SesionBo;
import com.coming.cellprojectmanager.modelo.TareaBo;
import com.coming.cellprojectmanager.modelo.TipoAcontecimientoBo;
import com.coming.cellprojectmanager.ws.GetTiposAcontecimientosWs;
import com.coming.cellprojectmanager.ws.GetTiposAcontecimientosWsResponse;
import com.coming.cellprojectmanager.ws.PostAcontecimientoWs;
import com.coming.cellprojectmanager.ws.PostAcontecimientoWsResponse;
import com.coming.cellprojectmanager.ws.PutAcontecimientoWs;
import com.coming.cellprojectmanager.ws.PutAcontecimientoWsResponse;
import com.coming.cellprojectmanager.ws.WsObserver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NuevoAcontecimiento extends Activity {
	private Spinner tipoSpinner;
	private EditText descripcionEditText;
	private Button aceptarButton;
	private TareaBo tareaSeleccionada;
	private ProgressDialog progressDialog;
	private AcontecimientoBo acontecimiento;
	private GetTiposAcontecimientosWs tipoAcontecimientosWs;
	private PostAcontecimientoWs postAcontecimientoWs;
	private PutAcontecimientoWs putAcontecimientoWs;
	private boolean esNuevoAcontecimiento;
	
	private WsObserver getTipoAcontecimientosWsObserver = new WsObserver() {
		public void notifiyPreExecute() {
	    	if(progressDialog == null) {
	    		progressDialog = new ProgressDialog(NuevoAcontecimiento.this);
	    	}
	    	progressDialog.setMessage(getString(R.string.buscando_tipos_acontecimientos));
	    	progressDialog.show();
		}

		public void notifiyPosExecute(String result) {
			GetTiposAcontecimientosWsResponse resp = GetTiposAcontecimientosWsResponse.fromJSon(result); 
	        List<TipoAcontecimientoBo> tipos = TipoAcontecimientoBo.listaDesdeDtos(resp.tipoAcontecimientos);
	        acutalizarTipoSpinner(tipos);
			progressDialog.dismiss();
		}

	};

	private WsObserver postAcontecimientosWsObserver = new WsObserver() {
		public void notifiyPreExecute() {
	    	if(progressDialog == null) {
	    		progressDialog = new ProgressDialog(NuevoAcontecimiento.this);
	    		
	    	}
	    	progressDialog.setMessage(getString(R.string.registrando_acontecimiento));
	    	progressDialog.show();
		}

		public void notifiyPosExecute(String result) {
			progressDialog.dismiss();
			PostAcontecimientoWsResponse resp = PostAcontecimientoWsResponse.fromJSon(result);			
			if(resp.error.codigo != 0) {
				Toast.makeText(NuevoAcontecimiento.this, getString(R.string.registro_acontecimiento_fallo),
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(NuevoAcontecimiento.this, getString(R.string.registro_acontecimiento_exito),
						Toast.LENGTH_SHORT).show();
				Intent data = new Intent();
				data.putExtra(Common.EXTRAS_KEY_ACONTECIMIENTO, acontecimiento);
				setResult(RESULT_OK, data);
				finish();				
			}
		}			
	};

	private WsObserver putAcontecimientosWsObserver = new WsObserver() {
		public void notifiyPreExecute() {
	    	if(progressDialog == null) {
	    		progressDialog = new ProgressDialog(NuevoAcontecimiento.this);
	    		
	    	}
	    	progressDialog.setMessage(getString(R.string.registrando_acontecimiento));
	    	progressDialog.show();
		}

		public void notifiyPosExecute(String result) {
			progressDialog.dismiss();
			PutAcontecimientoWsResponse resp = PutAcontecimientoWsResponse.fromJSon(result);			
			if(resp.error.codigo != 0) {
				Toast.makeText(NuevoAcontecimiento.this, getString(R.string.registro_acontecimiento_fallo),
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(NuevoAcontecimiento.this, getString(R.string.registro_acontecimiento_exito),
						Toast.LENGTH_SHORT).show();
				Intent data = new Intent();
				data.putExtra(Common.EXTRAS_KEY_ACONTECIMIENTO, acontecimiento);
				setResult(RESULT_OK, data);
				finish();				
			}
		}			
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_acontecimiento);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	tareaSeleccionada = (TareaBo)extras.getSerializable(Common.EXTRAS_KEY_TAREA);
        	acontecimiento = (AcontecimientoBo)extras.getSerializable(Common.EXTRAS_KEY_ACONTECIMIENTO);
        }
        tipoSpinner = (Spinner)findViewById(R.id.acontecimientoTiposSpinner);
        descripcionEditText = (EditText)findViewById(R.id.acontecimientoDescripcionEditText);
        aceptarButton = (Button)findViewById(R.id.acptarButton);
        aceptarButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				boolean creacionOk = crearAcontecimiento();
				if(creacionOk == false) {
					return;
				}
				Log.d("", acontecimiento.toJSon());
				if(!esNuevoAcontecimiento) {
					putAcontecimientoWs = new PutAcontecimientoWs(NuevoAcontecimiento.this);
					putAcontecimientoWs.execute(putAcontecimientosWsObserver, acontecimiento.toJSon(),
							acontecimiento.getId().toString());
				} else {
					postAcontecimientoWs = new PostAcontecimientoWs(NuevoAcontecimiento.this);
					postAcontecimientoWs.execute(postAcontecimientosWsObserver, acontecimiento.toJSon(),
							tareaSeleccionada.getId().toString());
				}
				
			}
		});
        obtenerDatos();
	}
	
	private void obtenerDatos() {
		if(tipoAcontecimientosWs != null) {
			tipoAcontecimientosWs.cancel(true);
		}
		tipoAcontecimientosWs = new GetTiposAcontecimientosWs(this);
		tipoAcontecimientosWs.execute(getTipoAcontecimientosWsObserver, "");
	}

	private boolean crearAcontecimiento() {
		esNuevoAcontecimiento = false;
		String desc = descripcionEditText.getText().toString();
		if(desc.isEmpty()) {
			Toast.makeText(this, getString(R.string.toast_falta_descripcion),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if(acontecimiento == null) {
			// es un nuevo acontecimiento, no hubo una seleccion previa
			esNuevoAcontecimiento = true;
			acontecimiento = new AcontecimientoBo();
	    	String usuario = SesionBo.getUsuarioId(this).toString();;
			acontecimiento.setUsuarioId(usuario);
			acontecimiento.setFechaCreacion(Calendar.getInstance().getTime());
		}
		String tipo = (String)tipoSpinner.getSelectedItem();	
		acontecimiento.setNombreTipoAcontecimeinto(tipo);
		acontecimiento.setDescripcion(desc);
		acontecimiento.setTareaId(tareaSeleccionada.getId());
		return true;
	}
	
	private void acutalizarTipoSpinner( List<TipoAcontecimientoBo> tipos) {
		List<String> nombreTipos = new ArrayList<String>();
        for(TipoAcontecimientoBo tipo : tipos) {
        	nombreTipos.add(tipo.getNombre());
        }
        tipoSpinner.setAdapter(new ArrayAdapter<String>(NuevoAcontecimiento.this, 
        		android.R.layout.simple_spinner_dropdown_item, nombreTipos));
        if(acontecimiento != null) {
        	// es una modificacion de uno seleccionado previamente
        	int position = tipos.indexOf(acontecimiento.getNombreTipoAcontecimiento());
        	if(position > -1) {
        		tipoSpinner.setSelection(position);
        	}
        	descripcionEditText.setText(acontecimiento.getDescripcion());
        }
	}	
}
