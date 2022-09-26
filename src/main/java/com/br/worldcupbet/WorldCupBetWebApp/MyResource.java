package com.br.worldcupbet.WorldCupBetWebApp;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

	private static List<Bet> betList = new ArrayList<Bet>();

	public MyResource() {
		if(betList.size() == 0) {			
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
	
	@POST
	@Path("/addBet")
	public Response addBet(@QueryParam("punterName") String punterName,
						   @QueryParam("teamOnWhichBet") String teamOnWhichBet,
						   @QueryParam("betValue") double betValue) {
		
		for (Bet b : betList) {
			if(punterName.equals(b.getPunterName())){
				return Response.status(Response.Status.CONFLICT).build();
			}
		}
		
		Bet bet = new Bet();
		
		bet.setPunterName(punterName);
		bet.setTeamOnWhichBet(teamOnWhichBet);
		bet.setBetValue(betValue);
				
		betList.add(bet);
		
		return Response.status(Response.Status.CREATED).build();
		
	}
	
	@DELETE
	@Path("/delete")
	public Response delete(@QueryParam("punterName") String punterName) {		
		
		Bet bet = new Bet();
		
		for (Bet b : betList) {
			if(punterName.equals(b.getPunterName())){
				bet = b;
				break;
			}
		}
		
		betList.remove(bet);						
		
		return Response.status(Response.Status.NO_CONTENT).build();		
	}
	
	@PUT
	@Path("/updateBet")
	public Response updateBet(@QueryParam("punterName") String punterName,
							   @QueryParam("teamOnWhichBet") String teamOnWhichBet,
							   @QueryParam("betValue") double betValue) {				
		
		for (Bet b : betList) {
			if(punterName.equals(b.getPunterName())){
				b.setTeamOnWhichBet(teamOnWhichBet);
				b.setBetValue(betValue);
				break;
			}
		}
		
		return Response.status(Response.Status.NO_CONTENT).build();		
	}
}

