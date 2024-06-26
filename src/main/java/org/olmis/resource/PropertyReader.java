package org.olmis.resource;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "server")
public interface PropertyReader {

	public String username();
	public String password();
}
