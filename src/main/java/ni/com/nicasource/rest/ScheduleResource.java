package ni.com.nicasource.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/schedule")
public class ScheduleResource {

    @GET
    @Path("{userId:[0-9]+}")
    @RolesAllowed({"paciente","doctor"})
    public String getSchedule(@PathParam("userId") int userId, @QueryParam("date") String date) {
        return "Schedule";
    }
}
