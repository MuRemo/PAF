package com.HospitalManagement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospital")

public class Hospital {

	Hospital hos = new Hospital();
	
	
    /////////////////////////////////-----------VIEW--------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String viewHospital() {

		return hos.viewHospital();

	}

   /////////////////////////////////-----------INSERT--------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String insert(
			@FormParam("name") String name, 
			@FormParam("address") String address,
			@FormParam("charge") String charge, 
			@FormParam("phonenumber") String phonenumber,
			@FormParam("roomcount") String roomcount
			) 
	{

		String output = hos.insert(name, address, charge, phonenumber, roomcount );
		return output;
	}

   /////////////////////////////////-----------UPDATE--------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String update(String hosp) {
		
		JsonObject hospital = new JsonParser().parse(hosp).getAsJsonObject();

		String hospitalid = hospital.get("hospitalid").getAsString();
		String name = hospital.get("name").getAsString();
		String address = hospital.get("address").getAsString();
		String charge = hospital.get("charge").getAsString();
		String phonenumber = hospital.get("phonenumber").getAsString();
		String roomcount = hospital.get("roomcount").getAsString();

		String output = hos.update(hospitalid);

		return output;
	}

	/////////////////////////////////-----------DELETE--------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String delete(String hosp) {

		Document doc = Jsoup.parse(hosp, "", Parser.xmlParser());

		String hospitalid = doc.select("hospitalid").text();

		String output = hos.delete(hospitalid);

		return output;
	}
}