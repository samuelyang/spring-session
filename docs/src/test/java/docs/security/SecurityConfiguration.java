/*
 * Copyright 2014-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package docs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

/**
 * @author Joris Kuipers
 */
// tag::class[]
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private FindByIndexNameSessionRepository<ExpiringSession> sessionRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			// other config goes here...
			.sessionManagement()
				.maximumSessions(2)
				.sessionRegistry(sessionRegistry());
		// @formatter:on
	}

	@Bean
	SpringSessionBackedSessionRegistry sessionRegistry() {
		return new SpringSessionBackedSessionRegistry<ExpiringSession>(
				this.sessionRepository);
	}
}
// end::class[]
