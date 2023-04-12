package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.Attendant;

import java.util.List;
import java.util.UUID;

public interface AttendantRepository {
    Attendant findAttendantById(final UUID id);

    List<Attendant> listAttendants();

    void createAttendant(final Attendant attendant);

    void updateAttendant(final Attendant attendant);

    void deleteAttendant(final UUID id);
}
