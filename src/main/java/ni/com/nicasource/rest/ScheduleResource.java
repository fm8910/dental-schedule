package ni.com.nicasource.rest;

import ni.com.nicasource.model.DoctorAvailable;
import ni.com.nicasource.repository.DoctorAvailableRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/schedule")
public class ScheduleResource {

    @Inject
    DoctorAvailableRepository doctorAvailableRepository;

    @GET
    @Path("{userId:[0-9]+}")
    @RolesAllowed({"paciente","doctor"})
    public String getSchedule(@PathParam("userId") int userId, @QueryParam("date") String date) {
        return "Schedule";
    }

    @POST
    @Path("new-day")
    @RolesAllowed({"doctor"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSchedule(DoctorAvailable doctorAvailable) {
        doctorAvailableRepository.persist(doctorAvailable);
        return Response.ok().build();
    }
}
