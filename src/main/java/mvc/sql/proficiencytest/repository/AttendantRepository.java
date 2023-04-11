package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.Attendant;

import java.util.UUID;

public interface AttendantRepository {
    Attendant findAttendantById(final UUID id);

    void createAttendant(final Attendant attendant);

    void updateAttendant(final Attendant attendant);

    void deleteAttendant(final UUID id);
}
