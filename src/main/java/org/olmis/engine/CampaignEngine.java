package org.olmis.engine;

import java.math.BigInteger;
import java.util.List;

import com.mt.quarkus.model.tables.pojos.Campaigns;
import com.mt.quarkus.repository.BECampaignRepository;

import org.jooq.types.ULong;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class CampaignEngine extends Thread {

	@Inject
	private BECampaignRepository campaignRepository;

	@Override
	public void run() {

		while (true) {

			try {
				List<Campaigns> campaignList = campaignRepository.getCampaign(BigInteger.valueOf(1));
				log.info("campaignList: {}", campaignList.size());
				List<ULong> idList = campaignList.stream().map(Campaigns::getId).toList();
				System.out.println(idList);

				Thread.sleep(10000);
			} catch (Exception e) {
				log.error("error: {}", e);
			}

		}

	}

}
