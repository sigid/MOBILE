package com.menu.digital;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {
	Button bFood, bDrink;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bFood = (Button) findViewById(R.id.bMakanan);
		bDrink = (Button) findViewById(R.id.bMinuman);

		bFood.setOnClickListener(this);
		bDrink.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bMakanan:
			Bundle b = new Bundle();
			Intent intent = new Intent(MainActivity.this, MenuActivity.class);
			b.putString("kategori", "1");
			intent.putExtras(b);
			startActivity(intent);

			break;

		case R.id.bMinuman:

			Bundle bu = new Bundle();
			Intent in = new Intent(MainActivity.this, MenuActivity.class);
			bu.putString("kategori", "2");
			in.putExtras(bu);
			startActivity(in);

			break;
		}
	}

}
