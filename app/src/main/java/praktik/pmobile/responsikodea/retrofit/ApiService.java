package praktik.pmobile.responsikodea.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static String BASE_URL = "http://10.0.2.2:8080/"; // Url Rest API yang digunakan, 10.0.2.2 adalah localhost untuk emulator, jika untuk penggunaan device maka ganti dengan ip address dari device
    private static Retrofit retrofit;
    public static ApiEndpoint endpoint(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiEndpoint.class);
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
