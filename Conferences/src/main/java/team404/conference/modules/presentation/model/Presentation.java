package team404.conference.modules.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import team404.conference.general.model.LongIdEntity;
import team404.conference.modules.account.model.Account;
import team404.conference.modules.account.model.Role;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRESENTATION")
@AttributeOverride(name = "id", column = @Column(name = "PRESENTATION_ID"))
public class Presentation extends LongIdEntity {

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @ManyToMany
    @JoinTable(
            name = "ACCOUNT_PRESENTATION",
            joinColumns = @JoinColumn(name = "PRESENTATION_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    )
    private List<Account> presenters;
}
