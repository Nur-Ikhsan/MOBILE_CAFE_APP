package praktik.pmobile.responsikodea.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import praktik.pmobile.responsikodea.R;
import praktik.pmobile.responsikodea.model.RegisterRequest;
import praktik.pmobile.responsikodea.model.User;
import praktik.pmobile.responsikodea.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEmployeeActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etUsername;
    private EditText etAddress;
    private EditText etPhoneNumber;
    private EditText etPassword;
    private Button buttonSave;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        etName = findViewById(R.id.et_name);
        etUsername = findViewById(R.id.et_username);
        etAddress = findViewById(R.id.et_address);
        etPhoneNumber = findViewById(R.id.et_phone_number);
        etPassword = findViewById(R.id.et_password);
        buttonSave = findViewById(R.id.button_save);
        buttonCancel = findViewById(R.id.button_cancel);

        buttonSave.setOnClickListener(v -> {
            // Mengirim request register ke server
            String name = etName.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String phoneNumber = etPhoneNumber.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String role = "EMPLOYEE";

            RegisterRequest registerRequest = new RegisterRequest(null, name, username, address, phoneNumber, password, role);
            ApiService.endpoint().register(registerRequest).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddEmployeeActivity.this, "Berhasil menambahkan karyawan baru", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddEmployeeActivity.this, ViewEmployeeListActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddEmployeeActivity.this, "Gagal menambahkan karyawan baru", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(AddEmployeeActivity.this, "Gagal terkoneksi dengan server", Toast.LENGTH_SHORT).show();
                }
            });
        });
        buttonCancel.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}