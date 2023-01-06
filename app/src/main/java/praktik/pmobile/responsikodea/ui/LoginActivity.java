package praktik.pmobile.responsikodea.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import praktik.pmobile.responsikodea.R;
import praktik.pmobile.responsikodea.model.LoginRequest;
import praktik.pmobile.responsikodea.model.Role;
import praktik.pmobile.responsikodea.model.User;
import praktik.pmobile.responsikodea.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            LoginRequest loginRequest = new LoginRequest(username, password);

            ApiService.endpoint().login(loginRequest).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User user = response.body();
                        Role role = user.getRole();
                        if (role.equals(Role.OWNER)) {
                            Log.d("LoginActivity", "onClick: owner");
                            Intent intent = new Intent(LoginActivity.this, OwnerHomeActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                        } else if (role.equals(Role.EMPLOYEE)) {
                            Log.d("LoginActivity", "onClick: employee");
                            Intent intent = new Intent(LoginActivity.this, EmployeeHomeActivity.class);
                            intent.putExtra("user", (Serializable) user);
                            startActivity(intent);
                        }
                        Log.d("LoginActivity", "onClick: Else");
                        Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    }  else {
                        // make toast message
                        Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("LoginActivity", "onClick: Di Luar if");
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Gagal terkoneksi dengan server", Toast.LENGTH_SHORT).show();
                    Log.d("LoginActivity", "onFailure: " + t.getMessage());
                }
            });
        });
    }
}
