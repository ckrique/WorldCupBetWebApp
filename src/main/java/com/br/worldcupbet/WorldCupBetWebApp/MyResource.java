package com.br.worldcupbet.WorldCupBetWebApp;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("BetSon")
public class MyResource {

	private List<Bet> betList = new ArrayList<Bet>();

	public MyResource() {
		Bet bet1 = new Bet();
		Bet bet2 = new Bet();
		
		bet1.setPunterName("Dickson");
		bet1.setTeamOnWhichBet("Brasil");
		bet1.setBetValue(450.50);
		
		bet2.setPunterName("Pedro");
		bet2.setTeamOnWhichBet("França");
		bet2.setBetValue(800.00);
		
		
		betList.add(bet1);
		betList.add(bet2);
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			String jsonString = mapper.writeValueAsString(betList);

			return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro Interno").build();
		}
	}
	
	@GET
	@Path("/getByName")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByPunterName(@QueryParam("punterName") String punterName) {
		
		Bet bet = new Bet();
		
		for (Bet b : betList) {
			if(punterName.equals(b.getPunterName())){
				bet = b;
				break;
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String jsonString = mapper.writeValueAsString(bet);

			return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro Interno").build();
		}
	}
}

