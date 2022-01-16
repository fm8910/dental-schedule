package ni.com.nicasource.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ni.com.nicasource.model.Doctor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DoctorRepository implements PanacheRepository<Doctor> {

    public Doctor findByName(String name) {
        return find("name", name).firstResult();
    }


}
