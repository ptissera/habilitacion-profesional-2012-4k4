package com.coming.cellprojectmanager.ws;

import android.content.Context;
import android.util.Log;

public class SitiosWs extends WsBase {
	public SitiosWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParams(String... params) {
		boolean error = (params.length != 1);
		if(error) {
			String msg = "Parametros invalidos. Deben ser [0]: nombre usuario";
			Log.e("SitiosWs", msg);
		}
		return error;
	}

	@Override
	protected String doFakeCall(String... params) {
		return "{" +
				"\"error\":{\"codigo\":0,\"descripcion\":\"Exito\"}," +
				"\"sitios\":[{\"id\":1,\"nombre\":\"Sitio 1\",\"provincia\":\"Cordoba\",\"latitud\":4.44556677,\"longitud\":51.44556677}," +
				"{\"id\":2,\"nombre\":\"Sitio 2\",\"provincia\":\"Cordoba\",\"latitud\":4.44556670,\"longitud\":51.44556670}]" +
				"}";
	}

	@Override
	protected String buildUrl(String... params) {
		String nombreUsuario = params[0];
		String url = "http://localhost/projectcellmanager/sitio/sitios/nombreUsuario/" + nombreUsuario;
		return url;
	}
}
