package com.coming.cellprojectmanager.ws;

import android.content.Context;
import android.util.Log;

public class GetAcontecimientosWs extends GetWsBase {

	public GetAcontecimientosWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParamsOk(String... params) {
		boolean paramsAreOk = (params.length == 1);
		if(paramsAreOk == false) {
			String msg = "Parametros invalidos. Deben ser [0]: tarea id";
			Log.e("TareasWs", msg);
		}
		return paramsAreOk;
	}

	@Override
	protected String doFakeCall(String... params) {
		return "{" +
				"\"error\":{\"codigo\":0,\"descripcion\":\"Exito\"}," +
				"\"acontecimientos\":[{\"id\":1,\"nombreTipo\":\"Materiales a destiempo\",\"usuarioId\":1,\"fechaCreacion\":\"01122012\",\"descripcion\":\"Los materiales no han llegado en el horario previsto por el cliente.\"}," +
				"{\"id\":2,\"nombreTipo\":\"Imposible realizar tarea\",\"usuarioId\":1,\"fechaCreacion\":\"01122012\",\"descripcion\":\"Se relevo mal los materailes y dispositivos necesarios.\"}]" +
				"}";			
	}

	@Override
	protected String buildUrl(String... params) {
		String tareaId = params[0];
		StringBuilder builder = new StringBuilder();
		builder.append(Common.WS_URL);
		builder.append(Common.WS_ACONTECIMIENTOS);
		builder.append(tareaId);
		return builder.toString();
	}

	@Override
	protected boolean validResponseCode(int responseCode) {
		return (responseCode >= 200 && responseCode < 400);
	}

}
