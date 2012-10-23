package com.coming.cellprojectmanager.ws;

import android.content.Context;
import android.util.Log;

public class GetLoginWs extends GetWsBase {

	public GetLoginWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParamsOk(String... params) {
		boolean paramsAreOk = (params.length == 2);
		if(paramsAreOk == false) {
			String msg = "Parametros invalidos. Deben ser [0]: nombre usuario, [1]: password";
			Log.e("GetLoginWs", msg);
		}
		return paramsAreOk;
	}

	@Override
	protected String doFakeCall(String... params) {
		return "{" +
				"\"error\":{\"codigo\":0,\"descripcion\":\"Exito.\"}," +
				"\"usuario\":{\"id\":1,\"nombre\":\"guillen\"}" +
				"}";
	}

	@Override
	protected String buildUrl(String... params) {
		String nombreUsuario = params[0];
		String pwd = params[1];
		StringBuilder builder = new StringBuilder();
		builder.append(Common.WS_URL);
		builder.append(Common.WS_LOGIN);
		builder.append(nombreUsuario);
		builder.append("/");
		builder.append(pwd);		
		return builder.toString();
	}

	@Override
	protected boolean validResponseCode(int responseCode) {
		return (responseCode == 200);
	}

}
