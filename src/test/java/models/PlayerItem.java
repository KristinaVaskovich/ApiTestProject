package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class PlayerItem {
    public int age;
    public String gender;
    public int id;
    public String role;
    public String screenName;
}
