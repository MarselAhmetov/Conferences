package team404.conference.modules.presentation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.conference.general.config.mapping.GlobalMapper;
import team404.conference.general.repository.SimpleDao;
import team404.conference.modules.account.dto.AccountSafeDto;
import team404.conference.modules.account.model.Account;
import team404.conference.modules.presentation.dto.PresentationDto;
import team404.conference.modules.presentation.model.Presentation;
import team404.conference.modules.presentation.repository.PresentationRepository;
import team404.conference.modules.room.model.Room;
import team404.conference.modules.schedule.dto.ScheduleDto;
import team404.conference.modules.schedule.model.Schedule;
import team404.conference.modules.schedule.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PresentationServiceImpl implements PresentationService {

    private final PresentationRepository presentationRepository;
    private final ScheduleRepository scheduleRepository;
    private final SimpleDao simpleDao;
    private final GlobalMapper mapper;

    @Override
    public void submit(ScheduleDto scheduleDto) {
        Presentation presentation;
        Schedule schedule;
        if (scheduleDto.getPresentation() != null && scheduleDto.getPresentation().getId() != null) {
            Optional<Presentation> optionalPresentation = presentationRepository.findById(scheduleDto.getPresentation().getId());
            presentation = optionalPresentation.orElseThrow(() -> {
                throw new IllegalArgumentException("Presentation with such id does not exists");
            });

            Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleDto.getId());
            schedule = scheduleOptional.orElseThrow(() -> {
                throw new IllegalArgumentException("Presentation is not in schedule");
            });

        } else {
            presentation = new Presentation();
            schedule = new Schedule();
            schedule.setPresentation(presentation);
        }

        if (scheduleDto.getPresentation() != null) {
            presentation.setTitle(scheduleDto.getPresentation().getTitle());
            presentation.setText(scheduleDto.getPresentation().getText());
            if (!scheduleDto.getPresentation().getPresenters().isEmpty()) {
                List<Account> presenters = new ArrayList<>();

                scheduleDto.getPresentation().getPresenters()
                        .forEach(presenter -> {

                            Optional<Account> optionalAccount = simpleDao.findById(Account.class, presenter.getId());

                            presenters.add(optionalAccount.orElseThrow(() -> {
                                throw new IllegalArgumentException("One of presenters does not exist");
                            }));
                        });

                presentation.setPresenters(presenters);
            } else {
                throw new IllegalArgumentException("Presenters can't be empty");
            }
        }

        Room room = null;
        if (scheduleDto.getRoom() != null) {
            Optional<Room> roomOptional = simpleDao.findById(Room.class, scheduleDto.getRoom().getId());
            room = roomOptional.orElseThrow(() -> {
                throw new IllegalArgumentException("Room with such id does not exists");
            });

            schedule.setRoom(room);
        }

        if (!isTimeFree(room, scheduleDto.getBeginDate(), scheduleDto.getEndDate())) {
            throw new IllegalArgumentException("This time is busy");
        }

        schedule.setBeginDate(scheduleDto.getBeginDate());
        schedule.setEndDate(scheduleDto.getEndDate());

        simpleDao.saveOrUpdate(presentation);
        simpleDao.saveOrUpdate(schedule);
    }

    private Boolean isTimeFree(Room room, Date beginDate, Date endDate) {
        return !(scheduleRepository.crossingCount(beginDate, endDate, room) > 0);
    }

    @Override
    public void delete(PresentationDto presentationDto) {
        if (presentationDto.getId() != null) {
            scheduleRepository.deleteByPresentation_Id(presentationDto.getId());
            presentationRepository.deleteById(presentationDto.getId());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PresentationDto> getPresentations(AccountSafeDto presenterDto) {

        Optional<Account> optionalAccount = simpleDao.findById(Account.class, presenterDto.getId());

        Account account = optionalAccount.orElseThrow(() -> {
            throw new IllegalArgumentException("Presenter with such id does not exists");
        });
        return mapper.mapAsList(
                presentationRepository.getPresentationsByPresentersContains(account),
                PresentationDto.class
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<PresentationDto> getPresentations() {
        return mapper.mapAsList(
                presentationRepository.findAll(),
                PresentationDto.class
        );
    }

    @Override
    public PresentationDto getById(Long id) {
        Presentation presentation = presentationRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Presentation with such id does not exists");
        });
        return mapper.map(presentation, PresentationDto.class);
    }
}
