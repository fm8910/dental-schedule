package ni.com.nicasource.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ni.com.nicasource.model.Appointment;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AppointmentRepository implements PanacheRepository<Appointment> {

    public List<Appointment> findByPatientId(Long id) {
        return find("patientId", id).list();
    }

    public List<Appointment> findByDoctorId(Long id) {
        return find("doctorId", id).list();
    }

}
