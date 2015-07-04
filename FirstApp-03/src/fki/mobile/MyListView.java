package fki.mobile;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyListView extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		String[] adobe_products = getResources().getStringArray(R.array.adobe_products);
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,R.id.label, adobe_products));
		
		ListView lv = getListView();
		
		lv.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				String product = ((TextView) view).getText().toString();
				Intent i = new Intent(getApplicationContext(), SingleListItem.class);
				i.putExtra("product", product);
				startActivity(i);
			}
		});
	}
}
