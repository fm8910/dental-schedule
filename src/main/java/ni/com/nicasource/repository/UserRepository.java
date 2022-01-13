package ni.com.nicasource.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ni.com.nicasource.model.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User findByUsernameAndPassword(String username, String password) {
        return find("username", username).firstResult();
    }
}
