package models;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PlayerUpdateResponseDto {
    public int age;
    public String gender;
    public int id;
    public String login;
    public String role;
    public String screenName;
}
