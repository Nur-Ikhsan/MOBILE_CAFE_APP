package praktik.pmobile.responsikodea.retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import praktik.pmobile.responsikodea.model.LoginRequest;
import praktik.pmobile.responsikodea.model.RegisterRequest;
import praktik.pmobile.responsikodea.model.UpdateRequest;
import praktik.pmobile.responsikodea.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiEndpoint {

    @POST("/api/users/login")
    Call<User> login(@Body LoginRequest loginRequest);

    @POST("/api/users/register")
    Call<User> register(@Body RegisterRequest registerRequest);

    @DELETE("/api/users/{id}")
    Call<ResponseBody> delete(@Path("id") long id);

    @PUT("/api/users/{id}")
    Call<User> update(@Path("id") long id, @Body UpdateRequest updateRequest);

    @GET("/api/users/employees")
    Call<List<User>> getAllEmployee();

    @GET("/api/users/{username}")
    Call<User> getUserByUsername(@Path("username") String username);

}
