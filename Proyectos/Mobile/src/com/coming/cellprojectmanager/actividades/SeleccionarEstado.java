package com.coming.cellprojectmanager.actividades;

import java.util.List;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.TareaBo;
import com.coming.cellprojectmanager.ws.PostTareaWs;
import com.coming.cellprojectmanager.ws.PostTareaWsResponse;
import com.coming.cellprojectmanager.ws.WsObserver;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SeleccionarEstado extends ListActivity implements WsObserver {
	private TareaBo tareaSeleccionada;
	private PostTareaWs postTareaWs;
	private ProgressDialog progressDialog;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	tareaSeleccionada = (TareaBo)extras.getSerializable(Common.EXTRAS_KEY_TAREA);
        	List<String> estados = tareaSeleccionada.getTransicionesValidas(false);
        	setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, estados));        	
        }        
        getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long ind) {
				String estado = (String)getListAdapter().getItem(position);
				tareaSeleccionada.setEstado(estado);
				Log.d("", tareaSeleccionada.toJSon());
				postTareaWs = new PostTareaWs(SeleccionarEstado.this);
				postTareaWs.execute(SeleccionarEstado.this, tareaSeleccionada.toJSon());				
			}
		});
    }

	public void notifiyPreExecute() {
    	if(progressDialog == null) {
    		progressDialog = new ProgressDialog(this);
    		
    	}
    	progressDialog.setMessage(getString(R.string.registrando_tarea));
    	progressDialog.show();		
	}

	public void notifiyPosExecute(String result) {
		progressDialog.dismiss();
		PostTareaWsResponse resp = PostTareaWsResponse.fromJSon(result);			
		if(resp.error.codigo != 0) {
			Toast.makeText(this, getString(R.string.registro_tarea_fallo),
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, getString(R.string.registro_tarea_exito),
					Toast.LENGTH_SHORT).show();
			Intent data = new Intent();
			Bundle extras = new Bundle();
			extras.putSerializable(Common.EXTRAS_KEY_TAREA, tareaSeleccionada);
	        data.putExtras(extras);
	        setResult(RESULT_OK, data);
	        finish();
		}
	}
}
