package com.coming.cellprojectmanager.ws;

import android.content.Context;
import android.util.Log;

public class PostAcontecimientoWs extends PostWsBase {

	public PostAcontecimientoWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParamsOk(String... params) {
		boolean paramsAreOk = (params.length == 1);
		if(paramsAreOk == false) {
			String msg = "Parametros invalidos. Deben ser [0]: acontecimeinto en json a postear.";
			Log.e("PostAcontecimientoWs", msg);
		}
		return paramsAreOk;
	}

	@Override
	protected String doFakeCall(String... params) {
		return "{" +
				"\"error\":{\"codigo\":0,\"descripcion\":\"Exito\"}" +
				"}";
	}

	@Override
	protected String buildUrl(String... params) {
		StringBuilder builder = new StringBuilder();
		builder.append(Common.WS_URL);
		builder.append(Common.WS_ACONTECIMIENTO);
		return builder.toString();
	}

}
