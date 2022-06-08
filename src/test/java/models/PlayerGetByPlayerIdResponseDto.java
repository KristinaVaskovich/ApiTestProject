package models;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PlayerGetByPlayerIdResponseDto {
    public int id;
    public String login;
    public String password;
    public String screenName;
    public String gender;
    public int age;
    public String role;
}
