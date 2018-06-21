package ru.ursip.webservice.mgsn.oshs.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Конфигурация сваггера
 *
 * @author Anton Kuznetsov
 * @since  21.06.2018
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {

    /**
     * Настройка сваггера
     */
    @Bean
    fun api(): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("ru.ursip.webservice.mgsn.oshs.controller"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(getApiInfo())
                    .useDefaultResponseMessages(false)

    /**
     * Описание сервиса
     */
    private fun getApiInfo(): ApiInfo =
            ApiInfo(
                    "MGSN-OSHS API",
                    "ОШС для проекта МГСН",
                    "1.0.0",
                    "http://www.ursip.ru/",
                    Contact("URSIP", "http://www.ursip.ru/", "support@ursip.ru"),
                    "URSIP",
                    "http://www.ursip.ru/", ArrayList())
}