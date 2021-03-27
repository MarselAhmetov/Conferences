package team404.conference.modules.room.dto;

import lombok.Getter;
import lombok.Setter;
import team404.conference.modules.schedule.dto.ScheduleDto;

import java.util.List;

@Getter
@Setter
public class ScheduledRoomDto extends RoomDto{
    List<ScheduleDto> schedules;
}
