package com.areablu.interactions.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.areablu.config.EnvironmentConfig;

@Service
public class CheckStatus {

	@Autowired
	private EnvironmentConfig environmentConfig;

	public String check() {
		return environmentConfig.getStatus();
	}
}
