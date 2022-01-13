package ni.com.nicasource.rest;

import ni.com.nicasource.model.Appointment;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/appointment")
@SecurityScheme(securitySchemeName = "Basic Auth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class AppointmentResource {

    @GET
    @Path("today/{userId:[0-9]+}")
    @RolesAllowed({"paciente"})
    @Produces(MediaType.APPLICATION_JSON)
    public Appointment getAppointmentForToday(@PathParam("userId") int userId) {
        return null;

    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"paciente"})
    public String createAppointment() {
        return "Appointment created";
    }

    @DELETE
    @RolesAllowed({"paciente"})
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteAppointment() {
        return "Appointment deleted";
    }

}
