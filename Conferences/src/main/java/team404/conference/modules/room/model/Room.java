package team404.conference.modules.room.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import team404.conference.general.model.LongIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROOM")
@AttributeOverride(name = "id", column = @Column(name = "ROOM_ID"))
public class Room extends LongIdEntity {

    @Column(name = "NAME", nullable = false)
    private String name;
}
