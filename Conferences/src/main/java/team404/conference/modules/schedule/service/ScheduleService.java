package team404.conference.modules.schedule.service;

import team404.conference.modules.room.dto.RoomDto;
import team404.conference.modules.room.dto.ScheduledRoomDto;
import team404.conference.modules.schedule.dto.ScheduleDto;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
    List<ScheduledRoomDto> getSchedulesSortedByRooms();
}
