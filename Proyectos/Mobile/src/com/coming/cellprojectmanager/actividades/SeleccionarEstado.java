package com.coming.cellprojectmanager.actividades;

import java.util.List;

import com.coming.cellprojectmanager.modelo.TareaBo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SeleccionarEstado extends ListActivity {
	private TareaBo tarea;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long ind) {
				String estado = (String)getListAdapter().getItem(position);
		        tarea.setEstado(estado);
				Intent data = new Intent();
				Bundle extras = new Bundle();
				extras.putSerializable(Common.EXTRAS_KEY_TAREA, tarea);
		        data.putExtras(extras);
		        SeleccionarEstado.this.setResult(RESULT_OK, data);
		        SeleccionarEstado.this.finish();
			}
		});
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	tarea = (TareaBo)extras.getSerializable(Common.EXTRAS_KEY_TAREA);
        	List<String> estados = tarea.getTransicionesValidas(false);
        	setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, estados));        	
        }
    }
}
