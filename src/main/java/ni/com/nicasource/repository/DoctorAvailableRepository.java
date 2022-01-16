package ni.com.nicasource.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ni.com.nicasource.model.DoctorAvailable;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DoctorAvailableRepository implements PanacheRepository<DoctorAvailable> {

    public DoctorAvailable findByUserId(Long userId) {
        return find("user.id", userId).firstResult();
    }


}
