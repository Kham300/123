package ru.ursip.webservice.mgsn.oshs.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*
import javax.servlet.http.HttpServletRequest

@Configuration
@EnableJpaAuditing
class AuditConfig {

    @Autowired
    lateinit var httpServletRequest: HttpServletRequest

    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return AuditorAware { Optional.of(httpServletRequest.getHeader("iv-user") ?: "unknown user") }
    }
}