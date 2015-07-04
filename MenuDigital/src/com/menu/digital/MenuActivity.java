package com.menu.digital;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.menu.digital.AmbilData.JsonObjectResult;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MenuActivity extends Activity {
	EntitasMakanan entitasmakanan;
	ArrayList<EntitasMakanan> menu = new ArrayList<EntitasMakanan>();
	ListView lv;
	String url = "http://10.0.2.2/menurestoran/menu_services.php";
	String urlpic = "http://10.0.2.2/menurestoran/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_listview);

		lv = (ListView) findViewById(R.id.listMenu);

		Bundle b = this.getIntent().getExtras();
		if (b.containsKey("kategori")) {

			String kat = b.getString("kategori");
			Log.d("kat", kat);
			AmbilData ambildata = new AmbilData();
			ambildata.init(MenuActivity.this, jsresult, kat, url);
		}

	}

	public JsonObjectResult jsresult = new JsonObjectResult() {

		@Override
		public void gotJsonObject(JSONObject jobject) {
			// TODO Auto-generated method stub
			try {
				JSONArray arraytempat = jobject.getJSONArray("datamenu");

				for (int i = 0; i < arraytempat.length(); i++) {
					entitasmakanan = new EntitasMakanan();
					entitasmakanan.setIDmenu(arraytempat.getJSONObject(i)
							.getInt("idmenu"));
					entitasmakanan.setNamaMenu(arraytempat.getJSONObject(i)
							.getString("nama_menu"));
					entitasmakanan.setHargaMenu(arraytempat.getJSONObject(i)
							.getString("harga_menu"));
					entitasmakanan.setDeskripsiMenu(arraytempat
							.getJSONObject(i).getString("diskripsi_menu"));
					entitasmakanan.setPicMenu(arraytempat.getJSONObject(i)
							.getString("pic_menu"));

					menu.add(entitasmakanan);

				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			MenuBaseAdapter datatempat = new MenuBaseAdapter(MenuActivity.this,
					menu);
			lv.setAdapter(datatempat);
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int p,
						long arg3) {
					// TODO Auto-generated method stub
					String des = menu.get(p).getDeskripsiMenu();
					String nm = menu.get(p).getNamaMenu();
					String hrg = menu.get(p).getHargaMenu();
					String pic = menu.get(p).getPicMenu();

					tampilkandetail(nm, des, hrg, pic);

				}
			});
		}

	};

	public void tampilkandetail(String nama, String desk, String harga,
			String pic) {

		final Dialog d = new Dialog(this);
		d.setTitle("detail");
		d.setContentView(R.layout.dialog_custom);

		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(d.getWindow().getAttributes());
		lp.width = LayoutParams.MATCH_PARENT;
		lp.height = LayoutParams.WRAP_CONTENT;

		d.getWindow().setAttributes(lp);

		ImageView gb = (ImageView) d.findViewById(R.id.picDialog);
		TextView n = (TextView) d.findViewById(R.id.idNamaMenu);
		TextView h = (TextView) d.findViewById(R.id.idHarga);
		TextView desc = (TextView) d.findViewById(R.id.idDeskripsi);
		Button bOk = (Button) d.findViewById(R.id.idOK);

		n.setText(nama);
		h.setText(harga);
		desc.setText(desk);
		new DownloadImageTask(gb).execute(urlpic + pic);
		bOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				d.dismiss();
			}
		});
		d.show();
	}

	public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			if (result != null) {
				Bitmap bmp2 = Bitmap.createScaledBitmap(result, 72, 72, true);
				bmImage.setImageBitmap(bmp2);
			}

		}
	}
}
