package org.olmis.engine;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class CampaignApplication implements QuarkusApplication {

	@Inject
	private CampaignEngine campaignEngine;

	public static void main(String[] args) {
		Quarkus.run(CampaignApplication.class, args);
		
	}

	@Override
	public int run(String... args) throws Exception {
		System.out.println("CampaignApplication.......................");
//		campaignEngine.start();
		Quarkus.waitForExit();
		return 0;
	}
}
