package com.coming.cellprojectmanager.actividades;

import java.lang.ref.WeakReference;

import java.util.List;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.SesionBo;
import com.coming.cellprojectmanager.modelo.TareaBo;
import com.coming.cellprojectmanager.utils.Utils;
import com.coming.cellprojectmanager.ws.GetTareasWs;
import com.coming.cellprojectmanager.ws.GetTareasWsResponse;
import com.coming.cellprojectmanager.ws.WsObserver;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GestionarTareas extends Activity implements WsObserver {
	private ListView listView;
	private TareasListAdapter listAdapter;
	private GetTareasWs tareasWs;
	private ProgressDialog progressDialog;
	private TareaBo tareaSeleccionada;
	
	private static final int REQUEST_CODE_VER_TAREA = 0;
	private static final int REQUEST_CODE_OPCIONES = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_tareas);
        listView = (ListView)findViewById(R.id.tareasListView);
        listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long ind) {
				tareaSeleccionada = (TareaBo)adapterView.getItemAtPosition(position);
				Intent intent = new Intent(GestionarTareas.this, VerDetalleTarea.class);
				Bundle extras = new Bundle();
				extras.putSerializable(Common.EXTRAS_KEY_TAREA, tareaSeleccionada);
				intent.putExtras(extras);
				startActivityForResult(intent, REQUEST_CODE_VER_TAREA);
			}
		});
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position,
					long ind) {
				tareaSeleccionada = (TareaBo)adapterView.getItemAtPosition(position);
				Intent intent = new Intent(GestionarTareas.this, OpcionesDeTarea.class);
				Bundle extras = new Bundle();
				extras.putSerializable(Common.EXTRAS_KEY_TAREA, tareaSeleccionada);
				intent.putExtras(extras);
				startActivityForResult(intent, REQUEST_CODE_OPCIONES);
				return true;
			}
		});
        listAdapter = new TareasListAdapter(this);        
        listView.setAdapter(listAdapter);
        WeakReference<LayoutInflater> inflater = new WeakReference<LayoutInflater>(LayoutInflater.from(this));
        View emptyView = inflater.get().inflate(R.layout.sin_datos, null);
        TextView msg = (TextView)emptyView.findViewById(R.id.sinDatosTextView);
        msg.setText(getString(R.string.no_hay_tareas));
        addContentView(emptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        listView.setEmptyView(emptyView);
        obtenerDatos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(getString(R.string.actualizar));
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case 0:
    		obtenerDatos();
    		break;
    	default:
    		break;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode) {
    	case REQUEST_CODE_VER_TAREA:
    		break;    		
    	case REQUEST_CODE_OPCIONES:
    		if(resultCode == RESULT_OK) {
    			obtenerDatos();
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
    	GetTareasWsResponse resp = GetTareasWsResponse.fromJSon(result);
    	if(resp.error.codigo != 0) {
			Toast.makeText(this, getString(R.string.obtener_tareas_fallo),
					Toast.LENGTH_SHORT).show();
	        progressDialog.dismiss();
    		return;
    	}
        List<TareaBo> tareas = TareaBo.listaDesdeDtos(resp.tareas);
        listAdapter.tareas = tareas;
        listAdapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }

    private void obtenerDatos() {
    	if(tareasWs != null) {
			tareasWs.cancel(true);
		}
        tareasWs = new GetTareasWs(this);
    	String usuarioId = SesionBo.getUsuarioId(this).toString();;
		tareasWs.execute(this, usuarioId);        	        		
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
                holder.nombreSitioTextView = (TextView) convertView.findViewById(R.id.itemListaTareaSitioTextView);
                holder.nombreTipoTextView = (TextView) convertView.findViewById(R.id.itemListaTareaNombreTipoTextView);
                holder.estadoTextView = (TextView) convertView.findViewById(R.id.itemListaTareaEstadoTextView);
                holder.fechasEstimadasTextView = (TextView) convertView.findViewById(R.id.itemListaTareaFechasEstimadasTextView);
                holder.fechasRealesTextView = (TextView) convertView.findViewById(R.id.itemListaTareaFechasRealesTextView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            holder.idTextView.setText(tarea.getId().toString());
            holder.nombreSitioTextView.setText(tarea.getNombreSitio());
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
            private TextView nombreSitioTextView;
            private TextView estadoTextView;
            private TextView fechasEstimadasTextView;
            private TextView fechasRealesTextView;
        }    	
    }    
}
