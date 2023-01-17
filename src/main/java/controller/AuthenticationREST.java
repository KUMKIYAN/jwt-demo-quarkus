package controller;

import jwt.PBKDF2Encoder;
import jwt.TokenUtils;
import model.AuthRequest;
import model.AuthResponse;
import model.User;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Path("/user")
public class AuthenticationREST {

	@Inject
	PBKDF2Encoder passwordEncoder;

	@ConfigProperty(name = "com.ard333.quarkusjwt.jwt.duration") public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

	@PermitAll
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(AuthRequest authRequest) {
		User u = User.findByUsername(authRequest.username);
		if (u != null && u.password.equals(passwordEncoder.encode(authRequest.password))) {
			try {
				return Response.ok(new AuthResponse(TokenUtils.generateToken(u.username, u.roles, duration, issuer))).build();
			} catch (Exception e) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("roles-test")
	@RolesAllowed({"USER", "ADMIN" })
	@Produces(MediaType.TEXT_PLAIN)
	public String helloRolesAllowed(@Context SecurityContext ctx) {
		return "hello";
	}

}