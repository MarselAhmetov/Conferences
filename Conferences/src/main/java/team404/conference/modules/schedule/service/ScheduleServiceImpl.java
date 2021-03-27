package team404.conference.modules.schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team404.conference.general.config.mapping.GlobalMapper;
import team404.conference.general.repository.SimpleDao;
import team404.conference.modules.room.dto.RoomDto;
import team404.conference.modules.room.dto.ScheduledRoomDto;
import team404.conference.modules.schedule.dto.ScheduleDto;
import team404.conference.modules.schedule.model.Schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final SimpleDao simpleDao;
    private final GlobalMapper mapper;

    @Override
    public List<ScheduledRoomDto> getSchedulesSortedByRooms() {
        List<ScheduleDto> schedules = mapper.mapAsList(simpleDao.findAll(Schedule.class), ScheduleDto.class);
        HashMap<RoomDto, List<ScheduleDto>> map = new HashMap<>();
        schedules.forEach((schedule) -> {
            if (map.containsKey(schedule.getRoom())) {
                map.get(schedule.getRoom()).add(schedule);
            } else {
                List<ScheduleDto> list = new ArrayList();
                list.add(schedule);
                map.put(schedule.getRoom(), list);
            }
        });

        List<ScheduledRoomDto> list = new ArrayList<>();
        for (Map.Entry<RoomDto, List<ScheduleDto>> entry : map.entrySet()) {
            ScheduledRoomDto scheduledRoomDto = mapper.map(entry.getKey(), ScheduledRoomDto.class);
            scheduledRoomDto.setSchedules(entry.getValue());
            list.add(scheduledRoomDto);
        }
        return list;
    }
}
