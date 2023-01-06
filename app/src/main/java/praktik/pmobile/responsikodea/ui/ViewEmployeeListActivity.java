package praktik.pmobile.responsikodea.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import praktik.pmobile.responsikodea.R;
import praktik.pmobile.responsikodea.model.User;
import praktik.pmobile.responsikodea.retrofit.ApiEndpoint;
import praktik.pmobile.responsikodea.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewEmployeeListActivity extends AppCompatActivity {
    private RecyclerView rvEmployeeList;
    private FloatingActionButton fabAddEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee_list);

        rvEmployeeList = findViewById(R.id.rv_employee_list);
        rvEmployeeList.setLayoutManager(new LinearLayoutManager(this));

        // Mengambil daftar karyawan dari server
        ApiService.endpoint().getAllEmployee().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> employeeList = response.body();
                    AdapterViewEmployeeList adapter = new AdapterViewEmployeeList(employeeList);
                    rvEmployeeList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(ViewEmployeeListActivity.this, "Gagal mengambil data karyawan", Toast.LENGTH_SHORT).show();
            }
        });

        fabAddEmployee = findViewById(R.id.fab_add_employee);
        fabAddEmployee.setOnClickListener(v -> {
            Intent intent = new Intent(ViewEmployeeListActivity.this, AddEmployeeActivity.class);
            startActivity(intent);
        });
    }
}

