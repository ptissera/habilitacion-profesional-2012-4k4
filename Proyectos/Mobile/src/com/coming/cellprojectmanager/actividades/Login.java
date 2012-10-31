package com.coming.cellprojectmanager.actividades;

import com.coming.cellprojectmanager.R;
import com.coming.cellprojectmanager.modelo.SesionBo;
import com.coming.cellprojectmanager.ws.GetLoginWs;
import com.coming.cellprojectmanager.ws.GetLoginWsResponse;
import com.coming.cellprojectmanager.ws.WsObserver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements WsObserver {
	private EditText nombreEditText;
	private EditText pwdEditText;
	private Button aceptarButton;
	private ProgressDialog progressDialog;
	private GetLoginWs loginWs;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nombreEditText = (EditText)findViewById(R.id.nombreEditText);
        pwdEditText = (EditText)findViewById(R.id.pwdEditText);
        aceptarButton = (Button)findViewById(R.id.aceptarButton);
        aceptarButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				login();
			}
		});
    }
    
    private void login() {
    	String usu = nombreEditText.getText().toString();
    	String pwd = pwdEditText.getText().toString();
    	if(usu.length() == 0) {
    		Toast.makeText(this, R.string.ingrese_nombre_usuario, Toast.LENGTH_SHORT).show();
    		return;
    	}
    	if(pwd.length() == 0) {
    		Toast.makeText(this, R.string.ingrese_pwd, Toast.LENGTH_SHORT).show();
    		return;
    	}
    	loginWs = new GetLoginWs(this);
    	loginWs.execute(this, usu, pwd);
	}

	public void notifiyPreExecute() {
    	if(progressDialog == null) {
    		progressDialog = new ProgressDialog(this);
    		progressDialog.setMessage(getString(R.string.iniciando_sesion));
    	}
    	progressDialog.show();
	}

	public void notifiyPosExecute(String result) {
		progressDialog.dismiss();
		GetLoginWsResponse resp = GetLoginWsResponse.fromJSon(result);
		if(resp.error.codigo != 0) {
			SesionBo.setUsuario(Login.this, -1L, "");
			if(resp.error.codigo == 1) {
				Toast.makeText(this, R.string.error_en_login, Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, R.string.error_en_coneccion, Toast.LENGTH_SHORT).show();
			}
			return;
		}
		SesionBo.setUsuario(Login.this, resp.usuario.id, resp.usuario.nombre);
		Intent intent = new Intent(Login.this, GestionarTareas.class);
		startActivity(intent);
	}    
}
