package com.voley.listview;

import com.voley.adapter.CustomListAdapter;
import com.voley.app.AppController;
import com.voley.model.Movie;
import info.androidhive.customlistviewvolley.R;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

@SuppressLint("NewApi") public class MainActivity extends Activity{
	//Log tag
	private static final String TAG = MainActivity.class.getSimpleName();
	
	//movies json url
	private static final String url = "http://10.0.2.2/android/movies.json";
	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView listView;
	private CustomListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, movieList);
		listView.setAdapter(adapter);
		
		pDialog = new ProgressDialog(this);
		//showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();
		
		//changing action bar color
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));
		
		//Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>(){
			@Override
			public void onResponse(JSONArray response){
				Log.d(TAG, response.toString());
				hidePDialog();
				
				//parsing JSON
				for(int i=0; i<response.length();i++){
					try{
						JSONObject obj = response.getJSONObject(i);
						Movie movie = new Movie();
						movie.setTitle(obj.getString("title"));
						movie.setThumbnailUrl(obj.getString("image"));
						movie.setRating(((Number)obj.get("rating")).doubleValue());
						movie.setYear(obj.getInt("releaseYear"));
						
						//Genre is json array
						JSONArray genreArray = obj.getJSONArray("genre");
						ArrayList<String> genre = new ArrayList<String>();
						for(int j=0;j<genreArray.length();j++){
							genre.add((String) genreArray.getString(j));
						}
						movie.setGenre(genre);
						
						//adding movie to movies array
						movieList.add(movie);
					} catch (JSONException e){
						e.printStackTrace();
					}
				}
				
				//notifying list adapter about data changes
				//so that it renders the list view with updated data
				adapter.notifyDataSetChanged();
			}
		}, new Response.ErrorListener(){
			@Override
			public void onErrorResponse(VolleyError error){
				VolleyLog.d(TAG, "Error: "+ error.getMessage());
				hidePDialog();
			}
		});
		
		//Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		hidePDialog();
	}
	
	private void hidePDialog(){
		if(pDialog != null){
			pDialog.dismiss();
			pDialog = null;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		//inflate the menu, this adds item to the action bar if it is present
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
