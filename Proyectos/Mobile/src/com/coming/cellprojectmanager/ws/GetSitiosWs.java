package com.coming.cellprojectmanager.ws;

import android.content.Context;
import android.util.Log;

public class GetSitiosWs extends GetWsBase {
	public GetSitiosWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParamsOk(String... params) {
		boolean paramsAreOk = (params.length == 1);
		if(paramsAreOk == false) {
			String msg = "Parametros invalidos. Deben ser [0]: nombre usuario";
			Log.e("SitiosWs", msg);
		}
		return paramsAreOk;
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
		StringBuilder builder = new StringBuilder();
		builder.append(Common.WS_URL);
		builder.append(Common.WS_SITIOS);
		builder.append("/nombreUsuario/");
		builder.append(nombreUsuario);
		return builder.toString();
	}

	@Override
	protected boolean validResponseCode(int responseCode) {
		return (responseCode == 200);
	}
}
