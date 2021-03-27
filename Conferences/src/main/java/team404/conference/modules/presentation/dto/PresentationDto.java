package team404.conference.modules.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team404.conference.general.dto.LongIdDto;
import team404.conference.modules.account.dto.AccountSafeDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PresentationDto extends LongIdDto {
    private String title;
    private String text;
    private List<AccountSafeDto> presenters;
}
