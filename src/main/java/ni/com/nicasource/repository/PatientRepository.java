package ni.com.nicasource.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ni.com.nicasource.model.Patient;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {

}
