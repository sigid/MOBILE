package fki.mobile;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private Button btnRegister;
	private Button btnLinkToLogin;
	private EditText inputFullName;
	private EditText inputEmail;
	private EditText inputPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		inputFullName = (EditText) findViewById(R.id.name_register);
		inputEmail = (EditText) findViewById(R.id.email_register);
		inputPassword = (Button) findViewById(R.id.btnRegisterTo);
		btnRegister = (Button) findViewById(R.id.btnRegisterTo);
		btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
		
		
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				String name = inputFullName.getText().toString();
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				
				if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
					
					Toast.makeText(getApplicationContext(),
							"Di sini akan diproses registrasi user baru",
							Toast.LENGTH_LONG).show();
					
				} else {
					
					Toast.makeText(getApplicationContext(),
							"Deskripsikan tentang Anda!",
							Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//pindah ke halaman register
				Intent i = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(i);
				finish();
			}
		});
	}
}
