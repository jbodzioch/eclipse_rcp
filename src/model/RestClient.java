package model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;

public class RestClient {

	public static Collection<BookTo> search(String title) {

		Gson gson = new Gson();
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI()).queryParam("titlePrefix", title);

		String response = target.request().accept(MediaType.APPLICATION_JSON).get(Response.class)
				.readEntity(String.class);

		BookTo[] tempBooks = gson.fromJson(response, BookTo[].class);
		Collection<BookTo> result = new ArrayList<BookTo>();

		for (BookTo bookTo : tempBooks) {
			result.add(bookTo);
		}

		return result;
	}

	public static void add(String title, String authors) {

		BookTo to = new BookTo(title, authors);
		Gson gson = new Gson();
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getPostURI());

		String input = gson.toJson(to);

		target.request().accept(MediaType.APPLICATION_JSON).post(Entity.json(input));

	}

	public static void delete(Long id) {

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getDeleteURI()).queryParam("id", id);

		target.request().accept(MediaType.APPLICATION_JSON).delete();

	}

	public static void put(Long id, String title, String authors) {

		BookTo to = new BookTo(id, title, authors);
		Gson gson = new Gson();
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getPutURI());

		String input = gson.toJson(to);

		target.request().accept(MediaType.APPLICATION_JSON).put(Entity.json(input));

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:9721/workshop/books-by-title").build();
	}

	private static URI getPostURI() {
		return UriBuilder.fromUri("http://localhost:9721/workshop/book").build();
	}

	private static URI getDeleteURI() {
		return UriBuilder.fromUri("http://localhost:9721/workshop/delete").build();
	}

	private static URI getPutURI() {
		return UriBuilder.fromUri("http://localhost:9721/workshop/put").build();
	}

}
