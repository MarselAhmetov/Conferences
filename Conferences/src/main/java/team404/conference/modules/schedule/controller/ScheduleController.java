package team404.conference.modules.schedule.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team404.conference.modules.room.dto.ScheduledRoomDto;
import team404.conference.modules.schedule.service.ScheduleServiceImpl;

import java.util.List;

@Api(value = "Schedule controller")
@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleServiceImpl scheduleService;

    @ApiOperation(value = "Get Presentations sorted by rooms")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/sorted")
    public List<ScheduledRoomDto> getAllPresentations() {
        return scheduleService.getSchedulesSortedByRooms();
    }
}
