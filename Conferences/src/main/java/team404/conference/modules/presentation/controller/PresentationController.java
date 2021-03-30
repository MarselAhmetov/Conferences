package team404.conference.modules.presentation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team404.conference.modules.account.dto.AccountSafeDto;
import team404.conference.modules.presentation.dto.PresentationDto;
import team404.conference.modules.presentation.service.PresentationService;
import team404.conference.modules.schedule.dto.ScheduleDto;

import java.util.List;

@Api(value = "Presentation controller")
@RestController
@RequestMapping("/api/presentation")
@RequiredArgsConstructor
public class PresentationController {

    private final PresentationService presentationService;

    @ApiOperation(value = "Submit Presentation information")
    @PreAuthorize("hasAuthority('PRESENTER')")
    @PostMapping
    public ResponseEntity<String> submit(@RequestBody ScheduleDto scheduledPresentationDto) {
        presentationService.submit(scheduledPresentationDto);
        return ResponseEntity.ok("Presentation created");
    }

    @ApiOperation(value = "Get Presentations")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<PresentationDto> getAllPresentations() {
        return presentationService.getPresentations();
    }

    @ApiOperation(value = "Get Presentations by presenter(account)")
    @PreAuthorize("hasAuthority('PRESENTER')")
    @GetMapping("/presenter")
    public List<PresentationDto> getPresentations(@RequestBody AccountSafeDto account) {
        return presentationService.getPresentations(account);
    }

    @ApiOperation(value = "Get Presentation by id")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public PresentationDto getPresentation(@ApiParam(name = "Presentation id") @PathVariable Long id) {
        return presentationService.getById(id);
    }

    @ApiOperation(value = "Delete Presentation")
    @PreAuthorize("hasAuthority('PRESENTER')")
    @DeleteMapping
    public void deletePresentation(@RequestBody PresentationDto presentation) {
        presentationService.delete(presentation);
    }
}
