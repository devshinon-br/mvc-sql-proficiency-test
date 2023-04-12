package mvc.sql.proficiencytest.model.dto;

import lombok.Getter;
import lombok.Setter;
import mvc.sql.proficiencytest.model.Attendant;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AttendantDTO {

    @NotNull
    private String name;

}
