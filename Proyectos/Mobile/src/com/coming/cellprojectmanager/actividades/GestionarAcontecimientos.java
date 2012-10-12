package com.coming.cellprojectmanager.actividades;

import java.lang.ref.WeakReference;
import java.util.List;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.AcontecimientoBo;
import com.coming.cellprojectmanager.modelo.SesionBo;
import com.coming.cellprojectmanager.modelo.TareaBo;
import com.coming.cellprojectmanager.utils.Utils;
import com.coming.cellprojectmanager.ws.GetAcontecimientosWs;
import com.coming.cellprojectmanager.ws.GetAcontecimientosWsResponse;
import com.coming.cellprojectmanager.ws.WsObserver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class GestionarAcontecimientos extends Activity implements WsObserver {
	private ListView listView;
	private Button nuevoAcontecimientoButton;
	private AcontecimientosListAdapter listAdapter;
	private GetAcontecimientosWs acontecimientosWs;
	private ProgressDialog progressDialog;
	private TareaBo tareaSeleccionada;
	private AcontecimientoBo acontecimientoSeleccionado;

	private static final int REQUEST_CODE_NUEVO_ACONTECIMIENTO = 0;
	protected static final int REQUEST_CODE_VER_ACONTECIMIENTO = 1;
	protected static final int REQUEST_CODE_OPCIONES_ACONTECIMIENTO = 2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_acontecimientos);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	tareaSeleccionada = (TareaBo)extras.getSerializable(Common.EXTRAS_KEY_TAREA);
        	if(tareaSeleccionada != null) {
        		obtenerDatos();
        	}
        }
        nuevoAcontecimientoButton = (Button)findViewById(R.id.nuevoButton);
        nuevoAcontecimientoButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GestionarAcontecimientos.this, NuevoAcontecimiento.class);
				Bundle extras = new Bundle();
				extras.putSerializable(Common.EXTRAS_KEY_TAREA, tareaSeleccionada);
				intent.putExtras(extras);
				startActivityForResult(intent, REQUEST_CODE_NUEVO_ACONTECIMIENTO);
			}
		});
        listView = (ListView)findViewById(R.id.acontecimientosListView);
        listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long ind) {
				acontecimientoSeleccionado = (AcontecimientoBo)adapterView.getItemAtPosition(position);
				Intent intent = new Intent(GestionarAcontecimientos.this, VerAcontecimiento.class);
				Bundle extras = new Bundle();
				extras.putSerializable(Common.EXTRAS_KEY_TAREA, tareaSeleccionada);
				extras.putSerializable(Common.EXTRAS_KEY_ACONTECIMIENTO, acontecimientoSeleccionado);
				intent.putExtras(extras);
				startActivityForResult(intent, REQUEST_CODE_VER_ACONTECIMIENTO);
			}
		});
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position,
					long ind) {
				acontecimientoSeleccionado = (AcontecimientoBo)adapterView.getItemAtPosition(position);
				Intent intent = new Intent(GestionarAcontecimientos.this, OpcionesDeAcontecimiento.class);
				Bundle extras = new Bundle();
				extras.putSerializable(Common.EXTRAS_KEY_TAREA, tareaSeleccionada);
				extras.putSerializable(Common.EXTRAS_KEY_ACONTECIMIENTO, acontecimientoSeleccionado);
				intent.putExtras(extras);
				startActivityForResult(intent, REQUEST_CODE_OPCIONES_ACONTECIMIENTO);
				return true;
			}
		});
        listAdapter = new AcontecimientosListAdapter(this);        
        listView.setAdapter(listAdapter);        
    }

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode) {
    	case REQUEST_CODE_NUEVO_ACONTECIMIENTO:
    	case REQUEST_CODE_OPCIONES_ACONTECIMIENTO:	
    		if(resultCode == RESULT_OK) {
    			obtenerDatos();
    		}
    		break;
    	case REQUEST_CODE_VER_ACONTECIMIENTO:
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
    	setResult(RESULT_OK);
    	super.onBackPressed();
    }
    
    
    public void notifiyPreExecute() {
    	if(progressDialog == null) {
    		progressDialog = new ProgressDialog(this);
    		progressDialog.setMessage(getString(R.string.buscando_acontecimientos));
    	}
    	progressDialog.show();
    }

    public void notifiyPosExecute(String result) {
    	GetAcontecimientosWsResponse resp = GetAcontecimientosWsResponse.fromJSon(result);
        List<AcontecimientoBo> acontecimientos = AcontecimientoBo.listaDesdeDtos(resp.acontecimientos);
        listAdapter.acontecimientos = acontecimientos;
        listAdapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }

    private void obtenerDatos() {
    	String usuario = SesionBo.getUsuarioId(this).toString();    	    		
		if(acontecimientosWs != null) {
			acontecimientosWs.cancel(true);
		}
		acontecimientosWs = new GetAcontecimientosWs(this);    		
		acontecimientosWs.execute(this, usuario, tareaSeleccionada.getId().toString());        	        		
	}

    private static class AcontecimientosListAdapter extends BaseAdapter {
    	public List<AcontecimientoBo> acontecimientos;
        private WeakReference<LayoutInflater> inflater;

        public AcontecimientosListAdapter(Context context) {
        	inflater = new WeakReference<LayoutInflater>(LayoutInflater.from(context));
        }

        public int getCount() {
            return (acontecimientos == null) ? 0 : acontecimientos.size();
        }

        public Object getItem(int position) {
            return (acontecimientos == null) ? null : acontecimientos.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            AcontecimientoBo acontecimiento = (AcontecimientoBo)getItem(position);
            
            if (convertView == null) {
                convertView = inflater.get().inflate(R.layout.item_acontecimiento, null);
                holder = new ViewHolder();
                holder.nombreTipoTextView = (TextView) convertView.findViewById(R.id.nombreTipoAcontecimientoTextView);
                holder.fechaCreacionTextView = (TextView) convertView.findViewById(R.id.fechaCreacionTextView);
                holder.resumenTextView = (TextView) convertView.findViewById(R.id.resumenTextView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            holder.nombreTipoTextView.setText(acontecimiento.getNombreTipoAcontecimiento());
            String fecha = Utils.fechaToFrontendString(acontecimiento.getFechaCreacion());
        	holder.fechaCreacionTextView.setText(convertView.getContext().getString(R.string.creado_el) + 
        			" " + fecha);
        	holder.resumenTextView.setText(acontecimiento.getDescripcion());
            	
            return convertView;
        }

        static class ViewHolder {
            private TextView nombreTipoTextView;
            private TextView fechaCreacionTextView;
            private TextView resumenTextView;
        }    	
    }    

}
