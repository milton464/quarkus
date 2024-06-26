package org.olmis.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

	@Inject
	PropertyReader propertyReader;

//	@Inject
//	private UserRepository userRepository;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {

		return propertyReader.password() + ":" + propertyReader.username() + " Test quarkus";
	}

	/*
	@Path("/users")
	@GET
	public Response getUsers() {
		System.out.println(userRepository);
		List<User> users = userRepository.listAll();
		User user = users.get(0);

		System.out.println("email: " + user.getEmail());
		JSONObject response = new JSONObject(user);

		return Response.status(Response.Status.CREATED).entity(response.toString()).build();
	}

	@Path("/byemail/{email}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUsers(@PathParam("email") String email) {
		System.out.println("email ---------" + email);

		User em = userRepository.findByEmail(email);
		JSONObject response = new JSONObject(em);
//		response.put("email", em.getEmail());
//		response.put("name", em.getFullName());

		return Response.status(Response.Status.CREATED).entity(response.toString()).build();
	}
	*/
}
