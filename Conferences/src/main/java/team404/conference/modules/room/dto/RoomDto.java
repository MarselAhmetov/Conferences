package team404.conference.modules.room.dto;

import lombok.Getter;
import lombok.Setter;
import team404.conference.general.dto.LongIdDto;

@Getter
@Setter
public class RoomDto extends LongIdDto {
    private String name;
}
