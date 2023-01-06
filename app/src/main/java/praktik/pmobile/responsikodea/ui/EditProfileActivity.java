package praktik.pmobile.responsikodea.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import praktik.pmobile.responsikodea.R;
import praktik.pmobile.responsikodea.model.Role;
import praktik.pmobile.responsikodea.model.UpdateRequest;
import praktik.pmobile.responsikodea.model.User;
import praktik.pmobile.responsikodea.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextAddress;
    private EditText editTextPhoneNumber;
    private EditText editTextPassword;
    private Button buttonUpdateProfile;
    private Button buttonCancel;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editTextName = findViewById(R.id.editTextName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonUpdateProfile = findViewById(R.id.buttonUpdateProfile);
        buttonCancel = findViewById(R.id.buttonCancel);


        // Mengambil data user dari intent
        user = (User) getIntent().getSerializableExtra("user");
        // Mengisi data user ke dalam form edit profile
        editTextName.setText(user.getName());
        editTextUsername.setText(user.getUsername());
        editTextAddress.setText(user.getAddress());
        editTextPhoneNumber.setText(user.getPhoneNumber());
        editTextPassword.setText(user.getPassword());

        buttonUpdateProfile.setOnClickListener(v -> {
            // Membuat object updateRequest dengan data yang telah diisi oleh user di form edit profile
            UpdateRequest updateRequest = new UpdateRequest(editTextName.getText().toString(), editTextUsername.getText().toString(), editTextAddress.getText().toString(), editTextPhoneNumber.getText().toString(), editTextPassword.getText().toString());
            // Mengirim request update ke server
            ApiService.endpoint().update(user.getId(), updateRequest).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User updatedUser = response.body();
                        Toast.makeText(EditProfileActivity.this, "Profil diperbarui", Toast.LENGTH_SHORT).show();
                        if (updatedUser.getRole().equals(Role.OWNER)) {
                            // Jika user yang diedit adalah owner, maka akan diarahkan ke halaman owner
                            Intent intent = new Intent(EditProfileActivity.this, OwnerHomeActivity.class);
                            intent.putExtra("user", updatedUser);
                            startActivity(intent);
                        } else {
                            // Jika user yang diedit adalah employee, maka akan diarahkan ke halaman daftar karyawan
                            Intent intent = new Intent(EditProfileActivity.this, ViewEmployeeListActivity.class);
                            intent.putExtra("user", updatedUser);
                            startActivity(intent);
                        }
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // Proses jika terjadi failure pada request
                    Toast.makeText(EditProfileActivity.this, "Gagal memperbarui profil", Toast.LENGTH_SHORT).show();
                }
            });
        });

        buttonCancel.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}