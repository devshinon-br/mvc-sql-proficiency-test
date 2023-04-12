package mvc.sql.proficiencytest.model.mapper;

import mvc.sql.proficiencytest.model.Attendant;
import mvc.sql.proficiencytest.model.dto.AttendantDTO;
import org.springframework.stereotype.Component;

@Component
public class AttendantMapper {
    public Attendant toEntity(final AttendantDTO dto) {
        return new Attendant(dto.getName());
    }
}
