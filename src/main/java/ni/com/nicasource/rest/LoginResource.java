package ni.com.nicasource.rest;

import io.quarkus.elytron.security.common.BcryptUtil;
import ni.com.nicasource.model.User;
import ni.com.nicasource.repository.UserRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/auth")
public class LoginResource {

    @Inject
    private UserRepository userRepository;

    @POST
    @Path("login")
    @PermitAll
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Successful login", content = @Content(schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "401", description = "Invalid credentials")
    })
    @Operation(summary = "Login", description = "Login with username and password")
    public User login(User user) {
        System.out.println("iniciando sesión");
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @POST
    @Path("signup")
    @PermitAll
    @Operation(summary = "Adds a user", description = "Creates a user and persists into database")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))))
    public String signUp(User user) {
        user.setPassword(BcryptUtil.bcryptHash(user.getPassword()));
        userRepository.persist(user);
        return "User created successfully";
    }


}
