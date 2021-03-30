package team404.conference.general.exception;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ErrorRecord {
    private String error;
    private String message;
    private String statusName;
    private Timestamp timestamp;
}
