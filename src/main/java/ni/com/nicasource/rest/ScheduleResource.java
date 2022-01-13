package ni.com.nicasource.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/schedule")
public class ScheduleResource {

    @GET
    @Path("{userId:[0-9]+}/date/{date}")
    @RolesAllowed({"paciente","doctor"})
    public String getSchedule() {
        return "Schedule";
    }
}
