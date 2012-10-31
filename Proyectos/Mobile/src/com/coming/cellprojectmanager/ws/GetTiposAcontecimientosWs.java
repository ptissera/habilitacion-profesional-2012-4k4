package com.coming.cellprojectmanager.ws;

import android.content.Context;

public class GetTiposAcontecimientosWs extends GetWsBase {

	public GetTiposAcontecimientosWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParamsOk(String... params) {
		boolean paramsAreOk = true;
		return paramsAreOk;
	}

	@Override
	protected String doFakeCall(String... params) {
		return "{" +
				"\"error\":{\"codigo\":0,\"descripcion\":\"Exito\"}," +
				"\"tipoAcontecimientos\":[{\"nombre\":\"Materiales a destiempo\"}," +
				"{\"nombre\":\"Imposible realizar tarea\"}," +
				"{\"nombre\":\"Ingenieria incompeta\"}]" +
				"}";
	}

	@Override
	protected String buildUrl(String... params) {
		StringBuilder builder = new StringBuilder();
		builder.append(Common.WS_URL);
		builder.append(Common.WS_TIPOS_ACONTECIMIENTOS);
		return builder.toString();
	}

	@Override
	protected boolean validResponseCode(int responseCode) {
		return (responseCode >= 200 && responseCode < 400);
	}
}
