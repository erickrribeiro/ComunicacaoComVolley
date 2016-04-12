package com.eribeiro.volleysimples;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.eribeiro.volleysimples.app.AppController;
import com.eribeiro.volleysimples.utils.Dominio;

public class JsonRequestActivity extends Activity implements OnClickListener {

	private String TAG = JsonRequestActivity.class.getSimpleName();
	private Button btnJsonObj, btnJsonArray;
	private TextView msgResponse;
	private ProgressDialog pDialog;

	//These tags will be used to cancel the requests
	private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);

		btnJsonObj = (Button) findViewById(R.id.btnJsonObj);
		btnJsonArray = (Button) findViewById(R.id.btnJsonArray);
		msgResponse = (TextView) findViewById(R.id.msgResponse);

		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);

		btnJsonObj.setOnClickListener(this);
		btnJsonArray.setOnClickListener(this);
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
	private void makeJsonObjReq() {
		showProgressDialog();
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
				Dominio.URL_PACE_JSON_ARRAY, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						msgResponse.setText(response.toString());
						hideProgressDialog();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hideProgressDialog();
					}
				}) {

			/**
			 * Passing some request headers
// Cancelling request			 * */
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				return headers;
			}

			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("name", "Androidhive");
				params.put("email", "abc@androidhive.info");
				params.put("pass", "password123");

				return params;
			}

		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq,tag_json_obj);
		//ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);

	}

	/**
	 * Making json array request
	 * */
	String jsonResponse;
	private void makeJsonArryReq() {
		showProgressDialog();
		JsonArrayRequest req = new JsonArrayRequest(Dominio.URL_PACE_JSON_ARRAY,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());

						try {

							jsonResponse = "";
							/**
							 * Loop contendo as postagens
							 */
							for (int i = 0; i < response.length(); i++) {

								JSONObject feedItemJSONObject = (JSONObject) response.get(i);

								String id = feedItemJSONObject.getString("id");
								String date = feedItemJSONObject.getString("date_gmt");
								String link = feedItemJSONObject.getString("link");

								///----------

								JSONObject titleJSONObject = feedItemJSONObject.getJSONObject("title");

								String rendered = titleJSONObject.getString("rendered");

								String authorID =feedItemJSONObject.getString("author");

								///----------

								JSONObject excerptJSONObject = feedItemJSONObject.getJSONObject("excerpt");

								String excerpt = excerptJSONObject.getString("rendered");

								///----------

								String featured_media =feedItemJSONObject.getString("featured_media");

								jsonResponse += "id: " + id + "\n\n";
								jsonResponse += "Date: " + date + "\n\n";
								jsonResponse += "Link: " + link + "\n\n";
								jsonResponse += "Title: " + rendered + "\n\n\n";

							}

							msgResponse.setText(jsonResponse);

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
						}




						//msgResponse.setText(response.toString());
						hideProgressDialog();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hideProgressDialog();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req,
				tag_json_arry);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnJsonObj:
			makeJsonObjReq();
			break;
		case R.id.btnJsonArray:
			makeJsonArryReq();
			break;
		}

	}

}
