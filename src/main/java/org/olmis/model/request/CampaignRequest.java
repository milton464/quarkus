package org.olmis.model.request;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CampaignRequest {

	private BigInteger id; 
	
	private BigInteger agencyId;

	private BigInteger userId;

	private BigInteger teamId;

	private String title;

	private String timeZone;

}
