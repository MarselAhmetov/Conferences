package team404.conference.modules.schedule.dto;

import lombok.Getter;
import lombok.Setter;
import team404.conference.general.dto.LongIdDto;
import team404.conference.modules.presentation.dto.PresentationDto;
import team404.conference.modules.room.dto.RoomDto;

import java.util.Date;

@Getter
@Setter
public class ScheduleDto extends LongIdDto {
    private Date beginDate;
    private Date endDate;
    private RoomDto room;
    private PresentationDto presentation;
}
