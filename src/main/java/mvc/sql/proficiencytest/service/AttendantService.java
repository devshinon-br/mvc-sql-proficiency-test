package mvc.sql.proficiencytest.service;

import mvc.sql.proficiencytest.model.Attendant;
import mvc.sql.proficiencytest.repository.AttendantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AttendantService {

    @Autowired
    private AttendantRepository attendantRepository;

    public Attendant findAttendantById(final UUID id) {
        return attendantRepository.findAttendantById(id);
    }

    public List<Attendant> listAttendants() {
        return attendantRepository.listAttendants();
    }

    public void createAttendant(final Attendant attendant) {
        attendantRepository.createAttendant(attendant);
    }

    public void updateAttendant(final Attendant attendant) {
        attendantRepository.updateAttendant(attendant);
    }

    public void deleteAttendant(final UUID id) {
        attendantRepository.deleteAttendant(id);
    }
}
