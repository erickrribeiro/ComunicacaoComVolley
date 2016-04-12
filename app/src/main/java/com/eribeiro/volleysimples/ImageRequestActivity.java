package com.eribeiro.volleysimples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.eribeiro.volleysimples.app.AppController;
import com.eribeiro.volleysimples.utils.Dominio;

/**
 * Está Classe que tem como objetivo criar um Activity (Tela), que terá como funcionalidade
 * única receber uma imagem via JSON e exibir no dispositivo.
 */
public class ImageRequestActivity extends Activity implements View.OnClickListener{

	private NetworkImageView mNetworkImageView;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);

		Button mButtonShowImage = (Button) findViewById(R.id.btnShowImage);
		mNetworkImageView = (NetworkImageView) findViewById(R.id.imgNetwork);
		imageView = (ImageView) findViewById(R.id.imgView);

		mButtonShowImage.setOnClickListener(this);
	}
	/**
	 *	Método responsável por receber uma imagem via JSON, e exibir em um NetworkImageView
	 */
	private void fecthImageJson() {
		ImageLoader imageLoader = AppController.getInstance().getImageLoader();

		// If you are using NetworkImageView
		mNetworkImageView.setImageUrl(Dominio.URL_IMAGE, imageLoader);

		// Loading image with placeholder and error image
		imageLoader.get(Dominio.URL_IMAGE, ImageLoader.getImageListener(
				imageView, R.drawable.ico_loading, R.drawable.ico_error));

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btnShowImage:
				fecthImageJson();
				break;
		}
	}
}
