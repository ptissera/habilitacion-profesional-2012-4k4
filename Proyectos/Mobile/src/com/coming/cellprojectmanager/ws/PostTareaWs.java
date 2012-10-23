package com.coming.cellprojectmanager.ws;

import android.content.Context;
import android.util.Log;

public class PostTareaWs extends PostWsBase {

	public PostTareaWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParamsOk(String... params) {
		boolean paramsAreOk = (params.length == 2);
		if(paramsAreOk == false) {
			String msg = "Parametros invalidos. Deben ser [0]: tarea en json a postear, [1]: tarea id.";
			Log.e("PostTareaWs", msg);
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
		builder.append(Common.WS_TAREAS);
		builder.append(params[1]);
		return builder.toString();
	}

	@Override
	protected boolean validResponseCode(int responseCode) {
		return (responseCode >= 200 && responseCode < 400);
	}

}
