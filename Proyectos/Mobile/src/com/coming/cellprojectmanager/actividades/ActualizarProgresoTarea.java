package com.coming.cellprojectmanager.actividades;

import java.lang.ref.WeakReference;

import java.util.List;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.SitioBo;
import com.coming.cellprojectmanager.modelo.TareaBo;
import com.coming.cellprojectmanager.utils.Utils;
import com.coming.cellprojectmanager.ws.TareasWs;
import com.coming.cellprojectmanager.ws.TareasWsResponse;
import com.coming.cellprojectmanager.ws.WsObserver;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ActualizarProgresoTarea extends Activity implements WsObserver {
	private ListView listView;
	private TextView sitioNombreTextView;
	private Button buscarSitioButton;
	private TareasListAdapter listAdapter;
	private TareasWs tareasWs;
	private ProgressDialog progressDialog;
	private SitioBo sitioSeleccionado;
	private TareaBo tareaSeleccionada;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_progreso_tarea);
        sitioNombreTextView = (TextView)findViewById(R.id.itemListaSitiosNombreTextView);
        sitioNombreTextView.setHint(R.string.nombre_sitio_hint);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	sitioSeleccionado = (SitioBo)extras.getSerializable(Common.EXTRAS_KEY_SITIO);
        	sitioNombreTextView.setText("");
        	if(sitioSeleccionado != null) {
        		sitioNombreTextView.setText(sitioSeleccionado.getNombre());
        		obtenerDatos();
        	}
        } else {
        	sitioNombreTextView.setText("");
        }
        buscarSitioButton = (Button)findViewById(R.id.buscarSitiosButton);
        buscarSitioButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ActualizarProgresoTarea.this, SeleccionarSitio.class);
				startActivityForResult(intent, 1);
			}
		});
        listView = (ListView)findViewById(R.id.tareasListView);
        listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long ind) {
				tareaSeleccionada = (TareaBo)adapterView.getItemAtPosition(position);
				Intent intent = new Intent(ActualizarProgresoTarea.this, SeleccionarEstado.class);
				Bundle extras = new Bundle();
				extras.putSerializable("tarea", tareaSeleccionada);
				intent.putExtras(extras);
				startActivityForResult(intent, 0);
			}
		});
        listAdapter = new TareasListAdapter(this);        
        listView.setAdapter(listAdapter);        
    }

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode) {
    	case 0: // Seleccionar Estado
    		if(resultCode == RESULT_OK) {
    			TareaBo tareaConNuevoEstado = (TareaBo)data.getExtras().getSerializable("tarea");
    			tareaSeleccionada.setEstado(tareaConNuevoEstado.getEstado());
    			listAdapter.notifyDataSetChanged();
    		}
    		break;
    	case 1: // Seleccionar Sitio
    		if(resultCode == RESULT_OK) {
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
    	            listAdapter.tareas.clear();
    	            listAdapter.notifyDataSetChanged();
    	    	} else {
    	        	sitioNombreTextView.setText(sitioSeleccionado.getNombre());
        	    	obtenerDatos();    	    		
    	    	}    			
    		}
    		break;    		
    	default:
    		break;
    	}
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();   	
    }
    
    @Override
    public void onBackPressed() {
    	Intent intent = new Intent();
    	intent.putExtra("sitio", sitioSeleccionado);
    	setResult(RESULT_OK, intent);
    	super.onBackPressed();
    }
    
    public void notifiyPreExecute() {
    	if(progressDialog == null) {
    		progressDialog = new ProgressDialog(this);
    		progressDialog.setMessage(getString(R.string.buscando_tareas));
    	}
    	progressDialog.show();
    }

    public void notifiyPosExecute(String result) {
    	TareasWsResponse resp = TareasWsResponse.fromJSon(result);
        List<TareaBo> tareas = TareaBo.listaDesdeDtos(resp.tareas);
        listAdapter.tareas = tareas;
        listAdapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }

    private void obtenerDatos() {
    	SharedPreferences pref = this.getSharedPreferences("cellprojectmanagermobile", MODE_PRIVATE);
    	String nombreUsuario = pref.getString("nombreUsuario", "");    	    		
		if(tareasWs != null) {
			tareasWs.cancel(true);
		}
        tareasWs = new TareasWs(this);    		
		tareasWs.execute(this, nombreUsuario, sitioSeleccionado.getId().toString());        	        		
	}

    private static class TareasListAdapter extends BaseAdapter {
    	public List<TareaBo> tareas;
        private WeakReference<LayoutInflater> inflater;

        public TareasListAdapter(Context context) {
        	inflater = new WeakReference<LayoutInflater>(LayoutInflater.from(context));
        }

        public int getCount() {
            return (tareas == null) ? 0 : tareas.size();
        }

        public Object getItem(int position) {
            return (tareas == null) ? null : tareas.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            TareaBo tarea = (TareaBo)getItem(position);
            
            if (convertView == null) {
                convertView = inflater.get().inflate(R.layout.item_tarea, null);
                holder = new ViewHolder();
                holder.idTextView = (TextView) convertView.findViewById(R.id.itemListaTareaIdTextView);
                holder.nombreTipoTextView = (TextView) convertView.findViewById(R.id.itemListaTareaNombreTipoTextView);
                holder.estadoTextView = (TextView) convertView.findViewById(R.id.itemListaTareaEstadoTextView);
                holder.fechasEstimadasTextView = (TextView) convertView.findViewById(R.id.itemListaTareaFechasEstimadasTextView);
                holder.fechasRealesTextView = (TextView) convertView.findViewById(R.id.itemListaTareaFechasRealesTextView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            holder.idTextView.setText(tarea.getId().toString());
            holder.nombreTipoTextView.setText(tarea.getNombreTipoTarea());
            holder.estadoTextView.setText(tarea.getEstado());

            String inicioEstimado = Utils.fechaToFrontendString(tarea.getFechaInicioEstimada());
            String finEstimado = Utils.fechaToFrontendString(tarea.getFechaFinEstimada());
            Context ctx = inflater.get().getContext();
            String sinDatos = ctx.getString(R.string.sin_datos);
        	String text = ctx.getString(R.string.fechas_estimadas);
            if(inicioEstimado == null || inicioEstimado.isEmpty()) {
            	inicioEstimado = sinDatos;
            }
        	if(finEstimado == null || finEstimado.isEmpty()) {
        		finEstimado = sinDatos;
        	}
        	holder.fechasEstimadasTextView.setText(text + " " + inicioEstimado + "-" + finEstimado);

            String inicioReal = Utils.fechaToFrontendString(tarea.getFechaInicioReal());
            String finReal = Utils.fechaToFrontendString(tarea.getFechaFinReal());
        	text = ctx.getString(R.string.fechas_reales);            
            if(inicioReal == null || inicioReal.isEmpty()) {
            	inicioReal = sinDatos;
            }
            if(finReal == null || finReal.isEmpty()) {
            	finReal = sinDatos;
            }
            holder.fechasRealesTextView.setText(text + " " + inicioReal + "-" + finReal);
            	
            return convertView;
        }

        static class ViewHolder {
        	private TextView idTextView;
            private TextView nombreTipoTextView;
            private TextView estadoTextView;
            private TextView fechasEstimadasTextView;
            private TextView fechasRealesTextView;
        }    	
    }    
}
