package ni.com.nicasource;

import io.quarkus.runtime.Startup;
import ni.com.nicasource.model.DisponibilidadDTO;
import ni.com.nicasource.model.DisponibilidadMedicosDTO;
import ni.com.nicasource.model.Doctor;
import ni.com.nicasource.model.DoctorAvailable;
import ni.com.nicasource.repository.DoctorAvailableRepository;
import ni.com.nicasource.repository.DoctorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@ApplicationScoped
public class FeedDoctorAvailableFromRest {

    private static final Logger LOGGER = Logger.getLogger(FeedDoctorAvailableFromRest.class.getName());
    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    DoctorRepository doctorRepository;
    DoctorAvailableRepository doctorAvailableRepository;
    UserTransaction utx;

    @Inject
    public FeedDoctorAvailableFromRest(DoctorRepository doctorRepository,
                                       DoctorAvailableRepository doctorAvailableRepository,
                                       UserTransaction utx) {
        this.doctorRepository = doctorRepository;
        this.doctorAvailableRepository = doctorAvailableRepository;
        this.utx = utx;
        init();
    }

    @Transactional
    public void init()  {
       try {
            CompletableFuture<List<DisponibilidadMedicosDTO>> response=  getData();

           for (DisponibilidadMedicosDTO medicosDTO : response.get()) {
               Doctor doctor = doctorRepository.findByName(medicosDTO.getNombre());
               utx.begin();
               for (DisponibilidadDTO disponibilidadDTO : medicosDTO.disponibilidad) {
                   DoctorAvailable doctorAvailable = new DoctorAvailable();
                   doctorAvailable.setDoctor(doctor);
                   for (DayOfWeek dayOfWeek :DayOfWeek.values()) {
                       if (disponibilidadDTO.dia.toLowerCase().equals(dayOfWeek.getDisplayName(TextStyle.FULL, new Locale("es")))) {
                           doctorAvailable.setDayOfWeek(dayOfWeek);
                       }
                   }
                   doctorAvailable.setStartTime(LocalTime.
                           parse(disponibilidadDTO.horaInicio, DateTimeFormatter.ofPattern("h:mma")));
                   doctorAvailable.setEndTime(LocalTime.
                           parse(disponibilidadDTO.horaFin,DateTimeFormatter.ofPattern("h:mma")));
                   doctorAvailableRepository.persist(doctorAvailable);
               }
               utx.commit();
           }
         } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al obtener datos", e);
         }
    }

    CompletableFuture<List<DisponibilidadMedicosDTO>> getData() {
        return  client
                .sendAsync(
                        HttpRequest.newBuilder()
                                .GET()
                                .uri(URI.create("https://api.jsonbin.io/b/61b3b51e62ed886f915dd68a"))
                                .header("Accept", "application/json")
                                .build()
                        ,
                        HttpResponse.BodyHandlers.ofString()
                )
                .thenApply(HttpResponse::body)
                .thenApply(stringHttpResponse -> JsonbBuilder.newBuilder().build().fromJson(stringHttpResponse, new TypeLiteral<List<DisponibilidadMedicosDTO>>() {}.getType()))
                .thenApply(data ->(List<DisponibilidadMedicosDTO>)data)
                .toCompletableFuture();
    }

}
