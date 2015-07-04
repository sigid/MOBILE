package fki.mobile;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
//import android.widget.TextView;

public class MainActivity extends Activity {
	
    //private TextView txtName;
    //private TextView txtEmail;
    private Button btnLogout;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//txtName = (TextView) findViewById(R.id.name_main);
        //txtEmail = (TextView) findViewById(R.id.email_main);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        
     // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });  
	
	}
	
	//fungsi logout
	private void logoutUser() {
		//perintah2 yanhg perlu di jalankan 
		//saat proses logout ada di sini
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
