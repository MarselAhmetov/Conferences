package team404.conference.modules.presentation.service;

import team404.conference.modules.account.dto.AccountSafeDto;
import team404.conference.modules.presentation.dto.PresentationDto;
import team404.conference.modules.schedule.dto.ScheduleDto;

import java.util.List;

public interface PresentationService {
    void submit(ScheduleDto scheduleDto);
    void delete(PresentationDto presentationDto);
    List<PresentationDto> getPresentations(AccountSafeDto presenter);
    List<PresentationDto> getPresentations();
    PresentationDto getById(Long id);

}
