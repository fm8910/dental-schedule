package ni.com.nicasource.rest;

import ni.com.nicasource.model.Appointment;
import ni.com.nicasource.repository.AppointmentRepository;
import ni.com.nicasource.repository.DoctorAvailableRepository;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;

@Path("/appointment")
@SecurityScheme(securitySchemeName = "Basic Auth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class AppointmentResource {

    @Inject
    private AppointmentRepository appointmentRepository;

    @Inject
    private DoctorAvailableRepository doctorAvailableRepository;

    @GET
    @Path("today/{userId:[0-9]+}")
    @RolesAllowed({"paciente"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAppointmentForToday(@PathParam("userId") int userId) {
        LocalDate today = LocalDate.now();
        return appointmentRepository.
                find("SELECT * FROM appointment WHERE user_id = ? AND date = ?", userId, today).
                list();
    }

    @POST
    @RolesAllowed({"paciente"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Appointment createAppointment(Appointment appointment) {

        if (appointment.getDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("La fecha debe ser posterior a la fecha actual");
        }
        String dayName= appointment.getDate().getDayOfWeek().
                getDisplayName(TextStyle.FULL, java.util.Locale.getDefault());




        return appointmentRepository.getEntityManager().merge(appointment);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"paciente"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAppointment(@PathParam("id") Long id) {
        if (appointmentRepository.findById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        appointmentRepository.deleteById(id);
        return Response.ok().build();
    }

}
