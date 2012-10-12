package com.coming.cellprojectmanager.actividades;

import java.util.List;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.SesionBo;
import com.coming.cellprojectmanager.modelo.SitioBo;
import com.coming.cellprojectmanager.ws.GetSitiosWs;
import com.coming.cellprojectmanager.ws.GetSitiosWsResponse;
import com.coming.cellprojectmanager.ws.WsObserver;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SeleccionarSitio extends Activity implements WsObserver {
	private ListView listView;
	private SitiosListAdapter listAdapter;
	private GetSitiosWs sitiosWs;
	private ProgressDialog progressDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_sitio);
        listView = (ListView)findViewById(R.id.sitiosListView);
        listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				SitioBo sitio = (SitioBo)listAdapter.getItem(position);
		        Intent data = new Intent();
				Bundle extras = new Bundle();
				extras.putSerializable(Common.EXTRAS_KEY_SITIO, sitio);
		        data.putExtras(extras);
		        SeleccionarSitio.this.setResult(RESULT_OK, data);
		        SeleccionarSitio.this.finish();
			}
		});
        listAdapter = new SitiosListAdapter(SeleccionarSitio.this);        
        listView.setAdapter(listAdapter);   
    }

    @Override
    protected void onStart() {
    	super.onStart();
    	habilitarPantalla();
    }

    private void habilitarPantalla() {
    	String usuario = SesionBo.getUsuarioId(this).toString();;
        if(sitiosWs != null) {
        	sitiosWs.cancel(true);
        }
        sitiosWs = new GetSitiosWs(this);        
    	sitiosWs.execute(this, usuario);    	
    }
    
	public void notifiyPreExecute() {
		if(progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage(getString(R.string.buscando_sitios));
		}
		progressDialog.show();
	}

	public void notifiyPosExecute(String result) {
		GetSitiosWsResponse resp = GetSitiosWsResponse.fromJSon(result);
        List<SitioBo> sitios = SitioBo.listaDesdeDtos(resp.sitios);
        listAdapter.sitios = sitios;
        listAdapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }
	
    private static class SitiosListAdapter extends BaseAdapter {
    	public List<SitioBo> sitios;
        private LayoutInflater inflater;

        public SitiosListAdapter(Context context) {
        	inflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return (sitios == null) ? 0 : sitios.size();
        }

        public Object getItem(int position) {
            return (sitios == null) ? null : sitios.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_sitio, null);
                holder = new ViewHolder();
                holder.idTextView = (TextView) convertView.findViewById(R.id.itemListaSitiosIdTextView);
                holder.nombreTextView = (TextView) convertView.findViewById(R.id.itemListaSitiosNombreTextView);
                holder.provinciaTextView = (TextView) convertView.findViewById(R.id.itemListaSitiosProvinciaTextView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            SitioBo sitio = (SitioBo)getItem(position);
            holder.idTextView.setText(sitio.getId().toString());
            holder.nombreTextView.setText(sitio.getNombre());
            holder.provinciaTextView.setText(sitio.getProvincia());
            return convertView;
        }

        static class ViewHolder {
            private TextView idTextView;
            private TextView nombreTextView;
            private TextView provinciaTextView;
        }    	
    }
}
