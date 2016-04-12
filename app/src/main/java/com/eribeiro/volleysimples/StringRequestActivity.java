package com.eribeiro.volleysimples;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.eribeiro.volleysimples.app.AppController;
import com.eribeiro.volleysimples.utils.Dominio;

public class StringRequestActivity extends Activity implements View.OnClickListener{

	private String TAG = StringRequestActivity.class.getSimpleName();
	private TextView mTextViewResponse;
	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_string);

		Button btnStringReq = (Button) findViewById(R.id.btnStringReq);
		mTextViewResponse = (TextView) findViewById(R.id.msgResponse);

		pDialog = new ProgressDialog(getApplicationContext());
		pDialog.setMessage("Carregando...");
		pDialog.setCancelable(false);

		btnStringReq.setOnClickListener(this);
	}

	private void showProgressDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hideProgressDialog() {
		if (pDialog.isShowing())
			pDialog.hide();
	}

	/**
	 * Making json object request
	 * */
	private void makeStringReq() {
		showProgressDialog();

		StringRequest strReq = new StringRequest(Method.GET,
				Dominio.URL_STRING_REQ, new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						Log.d(TAG, response);
						mTextViewResponse.setText(response);
						hideProgressDialog();

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hideProgressDialog();
					}
				});

		/**
		 * Adiciona um requisição a fila de requisições, nomeando esse requisição por "string_req"
 		 */

		AppController.getInstance().addToRequestQueue(strReq, "string_req");

	}

	@Override
	public void onClick(View v) {
		makeStringReq();
	}
}