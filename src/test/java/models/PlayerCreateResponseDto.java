package models;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PlayerCreateResponseDto {
    public int age;
    public String gender;
    public int id;
    public String login;
    public String password;
    public String role;
    public String screenName;
}
