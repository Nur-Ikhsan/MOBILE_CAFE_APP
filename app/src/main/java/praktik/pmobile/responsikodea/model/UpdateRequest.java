package praktik.pmobile.responsikodea.model;

public class UpdateRequest {
    private String name;
    private String username;
    private String address;
    private String phoneNumber;
    private String password;

    public UpdateRequest(String name, String username, String address, String phoneNumber, String password) {
        this.name = name;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
