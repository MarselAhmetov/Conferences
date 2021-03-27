package team404.conference.modules.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import team404.conference.general.model.LongIdEntity;
import team404.conference.modules.presentation.model.Presentation;
import team404.conference.modules.room.model.Room;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SCHEDULE")
@AttributeOverride(name = "id", column = @Column(name = "SCHEDULE_ID"))
public class Schedule extends LongIdEntity {

    @Column(name = "BEGIN_DATE", nullable = false)
    private Date beginDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @OneToOne
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    @OneToOne
    @JoinColumn(name = "PRESENTATION_ID", nullable = false)
    private Presentation presentation;
}
