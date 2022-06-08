package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

@ToString
@EqualsAndHashCode
@Getter
public class PlayerGetAllResponseDto {
    public List<PlayerItem> players;
}
