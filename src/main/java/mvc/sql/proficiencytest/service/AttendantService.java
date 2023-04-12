package mvc.sql.proficiencytest.service;

import mvc.sql.proficiencytest.model.Attendant;
import mvc.sql.proficiencytest.model.Ticket;
import mvc.sql.proficiencytest.model.Vehicle;
import mvc.sql.proficiencytest.model.dto.VehicleDTO;
import mvc.sql.proficiencytest.model.mapper.VehicleMapper;
import mvc.sql.proficiencytest.repository.AttendantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AttendantService {

    @Autowired
    private AttendantRepository attendantRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleMapper mapper;

    public Vehicle registerVehicle(final VehicleDTO dto) {
        return mapper.toEntity(dto);
    }

    public void registerDepartureTime(final Ticket ticket) {
        ticket.setDepartureTime(LocalDateTime.now());
    }

    public void registerEntryTime(final Ticket ticket) {
        ticket.setEntryTime(LocalDateTime.now());
    }

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
