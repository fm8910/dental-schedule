package ni.com.nicasource.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctors")
@Data
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", orphanRemoval = true)
    private Set<DoctorAvailable> doctorAvailables;

    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "doctor")
    private Set<Appointment> appointments;
}
