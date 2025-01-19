package model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserData {
    private String email;
    private String password;
    private String name;

    public UserData(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
