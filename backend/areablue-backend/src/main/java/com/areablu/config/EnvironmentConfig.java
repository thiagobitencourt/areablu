package com.areablu.config;

import org.aeonbits.owner.Config;

public interface EnvironmentConfig extends Config {

	@Config.Key("APP_STATUS")
	@DefaultValue("OK")
	String getStatus();

}
