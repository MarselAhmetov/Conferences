package team404.conference.modules.presentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.conference.modules.account.model.Account;
import team404.conference.modules.presentation.model.Presentation;

import java.util.List;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {
    List<Presentation> getPresentationsByPresentersContains(Account presenter);

}
