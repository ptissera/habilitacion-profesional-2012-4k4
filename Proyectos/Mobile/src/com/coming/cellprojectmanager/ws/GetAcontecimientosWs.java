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
				"\"acontecimientos\":[{\"id\":1,\"nombreTipo\":\"Tipo 1 acontecimiento en sitio debido a malas.\",\"usuarioId\":1,\"fechaCreacion\":\"01122012\",\"descripcion\":\"Guardia sitio no esta presente, no podemos acceder. Ya es la quinta vez que no esta el guardia en el sitio.\"}," +
				"{\"id\":2,\"nombreTipo\":\"Tipo 2\",\"usuarioId\":1,\"fechaCreacion\":\"01122012\",\"descripcion\":\"Materiales no han llegado. Nos retiramos hasta mañana.\"}]" +
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
