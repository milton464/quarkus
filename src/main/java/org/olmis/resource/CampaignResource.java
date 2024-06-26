package org.olmis.resource;

import static com.mt.quarkus.model.tables.Campaigns.CAMPAIGNS;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mt.quarkus.dto.BECampaignDto;
import com.mt.quarkus.model.Campaign;
import com.mt.quarkus.model.enums.CampaignsEmailThreads;
import com.mt.quarkus.model.enums.CampaignsIsFavourite;
import com.mt.quarkus.model.enums.CampaignsStatus;
import com.mt.quarkus.model.tables.pojos.Campaigns;
import com.mt.quarkus.repository.BECampaignRepository;
import com.mt.quarkus.repository.impl.CampaignRepositoryImpl;

import org.jooq.JSON;
import org.jooq.types.ULong;
import org.json.JSONObject;
import org.olmis.model.request.CampaignRequest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @author MILTON
 */
@Path("campaigns")
@Slf4j
public class CampaignResource {

	@Inject
	private CampaignRepositoryImpl campaignRepository;
	
	@Inject
	private BECampaignRepository beCampaignRepository;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCampaigns() {
		JSONObject response = new JSONObject();
		List<Campaign> campaigns = campaignRepository.findAllCampaign();
		response.put("campaigns", campaigns);
		return Response.status(Response.Status.OK).entity(response.toString()).build();
	}

	@GET
	@Path("findById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCampaignById(@PathParam("id") BigInteger id) {
		JSONObject response = new JSONObject();
		Optional<BECampaignDto> campaigns = beCampaignRepository.findCampaignById(id);
		if (campaigns.isPresent()) {
			response.put("campaigns", campaigns.get());
		}
		return Response.status(Response.Status.OK).entity(response.toString()).build();
	}	

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllCampaignList() {
		JSONObject response = new JSONObject();
		List<Campaigns> campaigns = beCampaignRepository.getCampaign(BigInteger.ONE);
		response.put("campaigns", campaigns);
		return Response.status(Response.Status.OK).entity(response.toString()).build();
	}	

	@GET
	@Path("update/{id}/agency/{agencyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCampaign(@PathParam("id")BigInteger id, @PathParam("agencyId")BigInteger agencyId) {
		JSONObject response = new JSONObject();
		Map<String, Object> fieldMap = new HashMap<>();
		fieldMap.put(CAMPAIGNS.STATUS.getName(), CampaignsStatus.ACTIVE);
		boolean isUpdate = beCampaignRepository.update(id, agencyId, fieldMap);
		response.put("campaigns", isUpdate);
		return Response.status(Response.Status.OK).entity(response.toString()).build();
	}	

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCampaign(CampaignRequest campaignRequest) {
		JSONObject response = new JSONObject();
		System.out.println("campaignRequest.....");
		log.info("campaignRequest: {}", campaignRequest);
		Campaigns campaign = new Campaigns();
		campaign.setAgencyId(ULong.valueOf(campaignRequest.getAgencyId()));
		campaign.setUserId(ULong.valueOf(campaignRequest.getUserId()));
		campaign.setTeamId(ULong.valueOf(campaignRequest.getTeamId()));
		campaign.setTitle(campaignRequest.getTitle());
		campaign.setTimezone(campaignRequest.getTimeZone());
		campaign.setIsFavourite(CampaignsIsFavourite.YES);
		campaign.setStatus(CampaignsStatus.ACTIVE);
		campaign.setEmailThreads(CampaignsEmailThreads.SINGLE_THREAD);
		campaign.setApiKey("sdffjkh");
		campaign.setPauseTime(LocalDateTime.now());
		campaign.setStartTime(LocalDateTime.now());
		campaign.setSettingInfos(JSON.valueOf(new JSONObject().put("title", "campaign").toString()));
		
		Optional<BigInteger> id = beCampaignRepository.addCampaign(campaign);
		if (id.isPresent()) {
			campaignRequest.setId(id.get());
		}
		
		response.put("campaign", campaignRequest);
		return Response.status(Response.Status.OK).entity(response.toString()).build();
	}	
	
	
}
