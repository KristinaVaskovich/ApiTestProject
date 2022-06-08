package models;

import lombok.Setter;

@Setter
public class PlayerUpdateRequestDto {
    public int age;
    public String gender;
    public String login;
    public String password;
    public String role;
    public String screenName;
}
