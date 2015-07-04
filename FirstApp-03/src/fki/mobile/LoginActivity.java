package fki.mobile;

import fki.model.helper.SQLiteHandler;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button btnLogin, btnRegister;
	private EditText inputEmail, inputPassword;
	
	private SQLiteHandler db;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		inputEmail = (EditText) findViewById(R.id.email_login);
		inputPassword = (EditText) findViewById(R.id.password_login);
		btnLogin = (Button) findViewById(R.id.btnLoginTo);
		btnRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
		
		// SQLite database handler
        db = new SQLiteHandler(getApplicationContext());
			
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				// Memerika apakah user telah memasukan data username & password
				if (email.trim().length() > 0 && password.trim().length() > 0) {
					// Check Username dan Password dengan database di sini					
					if (LoginTo(email,password)) {
						GoToMainPage();
					} else {
						Toast.makeText(getApplicationContext(),
								"username : " + email + " atau password: " + password + 
								" ada yang salah!", Toast.LENGTH_LONG)
								.show();
					}
					
				} else {
					// User belum memasukan data username dan password
					Toast.makeText(getApplicationContext(),
							"Masukan username dan password!", Toast.LENGTH_LONG)
							.show();
				}				
			}			
		});
		
		
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Pindah ke Halaman Registrasi
				Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
				finish();
			}
		});
		
	}
	
	private void GoToMainPage() {
		
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
		finish();
		
	}
	
	
	private boolean LoginTo(String username, String password) {
		boolean r = false;
		r = (db.UserLogin(username, password) > 0);
		return r;
	}
	
	
}
