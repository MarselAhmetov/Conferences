package team404.conference.modules.account.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import team404.conference.general.model.LongIdEntity;
import team404.conference.modules.presentation.model.Presentation;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT")
@AttributeOverride(name = "id", column = @Column(name = "ACCOUNT_ID"))
public class Account extends LongIdEntity {
    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "presenters")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Presentation> presentations;
}
