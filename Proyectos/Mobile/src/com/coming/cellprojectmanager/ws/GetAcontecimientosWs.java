package com.coming.cellprojectmanager.ws;

import android.content.Context;
import android.util.Log;

public class GetAcontecimientosWs extends GetWsBase {

	public GetAcontecimientosWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParamsOk(String... params) {
		boolean paramsAreOk = (params.length == 2);
		if(paramsAreOk == false) {
			String msg = "Parametros invalidos. Deben ser [0]: nombre usuario, [1]: tarea id";
			Log.e("TareasWs", msg);
		}
		return paramsAreOk;
	}

	@Override
	protected String doFakeCall(String... params) {
		String tareaId = params[1];
		if(tareaId.equalsIgnoreCase("1")) {
			return "{" +
					"\"error\":{\"codigo\":0,\"descripcion\":\"Exito\"}," +
					"\"acontecimientos\":[{\"id\":1,\"nombreTipo\":\"Tipo 1\",\"usuarioId\":1,\"fechaCreacion\":\"01122012\",\"descripcion\":\"Guardia sitio no esta presente, no podemos acceder. Ya es la quinta vez que no esta el guardia en el sitio.\"}," +
					"{\"id\":2,\"nombreTipo\":\"Tipo 2\",\"usuarioId\":1,\"fechaCreacion\":\"01122012\",\"descripcion\":\"Materiales no han llegado. Nos retiramos hasta mañana.\"}]" +
					"}";			
		} else {
			return "{" +
					"\"error\":{\"codigo\":1,\"descripcion\":\"No hay acontecimientos para la tarea.\"}" +
					"}";			
		}

	}

	@Override
	protected String buildUrl(String... params) {
		String nombreUsuario = params[0];
		String tareaId = params[1];
		StringBuilder builder = new StringBuilder();
		builder.append(Common.WS_URL);
		builder.append(Common.WS_ACONTECIMIENTOS);
		builder.append("/nombreUsuario/");
		builder.append(nombreUsuario);
		builder.append("/tareaId/");
		builder.append(tareaId);
		return builder.toString();
	}

}
