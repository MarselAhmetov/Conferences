package team404.conference.modules.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team404.conference.modules.room.model.Room;
import team404.conference.modules.schedule.model.Schedule;

import java.util.Date;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    void deleteByPresentation_Id(Long id);
    @Query("select count(id) from Schedule where ((beginDate between :beginDate and :endDate) or (endDate between :beginDate and :endDate) or (:endDate between beginDate and endDate)) and room = :room")
    Integer crossingCount(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, @Param("room") Room room);
}
