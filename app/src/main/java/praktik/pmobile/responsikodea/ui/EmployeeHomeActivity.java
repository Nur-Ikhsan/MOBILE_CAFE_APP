package praktik.pmobile.responsikodea.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import praktik.pmobile.responsikodea.R;
import praktik.pmobile.responsikodea.model.User;

public class EmployeeHomeActivity extends AppCompatActivity {
    private TextView textViewUsername;
    private TextView textViewNama;
    private TextView textViewAlamat;
    private TextView textViewNoHp;
    private Button buttonViewLocation;
    private Button buttonLogout;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);

        textViewUsername = findViewById(R.id.textViewUsername);
        textViewNama = findViewById(R.id.textViewNama);
        textViewAlamat = findViewById(R.id.textViewAlamat);
        textViewNoHp = findViewById(R.id.textViewNoHp);
        buttonViewLocation = findViewById(R.id.buttonViewLocation);
        buttonLogout = findViewById(R.id.buttonLogout);

        user = (User) getIntent().getSerializableExtra("user");
        textViewUsername.setText(user.getUsername());
        textViewNama.setText(String.format("Nama : %s", user.getName()));
        textViewAlamat.setText(String.format("Alamat : %s", user.getAddress()));
        textViewNoHp.setText(String.format("No Hp : %s", user.getPhoneNumber()));

        buttonViewLocation.setOnClickListener(v -> {
            // membuka activity untuk edit profile
            Intent intent = new Intent(EmployeeHomeActivity.this, ViewLocationActivity.class);
            startActivity(intent);
        });

        buttonLogout.setOnClickListener(v -> {
            // Membuat AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeHomeActivity.this);
            builder.setMessage("Apakah Anda yakin ingin logout?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // logout dan kembali ke activity login
                            Intent intent = new Intent(EmployeeHomeActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // tidak melakukan apa-apa
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        });
    }
}
