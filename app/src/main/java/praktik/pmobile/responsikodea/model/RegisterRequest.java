package praktik.pmobile.responsikodea.model;

public class RegisterRequest {
    private Long id;
    private String name;
    private String username;
    private String address;
    private String phoneNumber;
    private String password;
    private String role;

    public RegisterRequest(Long id, String name, String username, String address, String phoneNumber, String password, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }
}
