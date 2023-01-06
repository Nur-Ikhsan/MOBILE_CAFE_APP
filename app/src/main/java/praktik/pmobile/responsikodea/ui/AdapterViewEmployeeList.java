package praktik.pmobile.responsikodea.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import praktik.pmobile.responsikodea.R;
import praktik.pmobile.responsikodea.model.User;
import praktik.pmobile.responsikodea.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterViewEmployeeList extends RecyclerView.Adapter<AdapterViewEmployeeList.ViewHolder> {
    private List<User> employeeList;

    public AdapterViewEmployeeList(List<User> employeeList) {
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_view_employee_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int _position) {
        int position = holder.getAdapterPosition();
        User employee = employeeList.get(position);
        holder.textViewUsername.setText(employee.getUsername());
        holder.textViewName.setText(employee.getName());
        holder.textViewAddress.setText(employee.getAddress());
        holder.textViewPhoneNumber.setText(employee.getPhoneNumber());
        holder.relativeLayout.setOnClickListener(view -> {
            // membuka activity untuk edit profile
            Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
            intent.putExtra("user", employee);
            view.getContext().startActivity(intent);
        });
        holder.relativeLayout.setOnLongClickListener(view -> {
            // tampilkan dialog konfirmasi hapus
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Konfirmasi Hapus")
                    .setMessage("Apakah Anda yakin ingin menghapus item dengan Username "+ employee.getUsername() + "?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        ApiService.endpoint().delete(employee.getId()).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(view.getContext(), "Berhasil menghapus item", Toast.LENGTH_SHORT).show();
                                    employeeList.remove(position);
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(view.getContext(), "Gagal menghapus item", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(view.getContext(), "Gagal menghapus item", Toast.LENGTH_SHORT).show();
                            }
                        });
                    })
                    .setNegativeButton("Tidak", (dialog, which) -> {
                    // tidak ada yang dilakukan, tutup saja dialog
                    })
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView textViewUsername;
        TextView textViewName;
        TextView textViewAddress;
        TextView textViewPhoneNumber;

        ViewHolder(View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
            textViewPhoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
        }
    }
}
