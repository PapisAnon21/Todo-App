package sn.ept.git.seminaire.cicd.exceptions.message;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 *
 * @author ISENE
 */
@Getter

@AllArgsConstructor(staticName = "of")

@ToString

public class ErrorMessage {
    private int status;
    private Date timestamp;
    private String message;
    private String description;

}
