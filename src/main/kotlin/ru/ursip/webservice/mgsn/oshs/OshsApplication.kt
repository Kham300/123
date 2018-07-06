package ru.ursip.webservice.mgsn.oshs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * Стартовый класс приложения
 *
 * @author Anton Kuznetsov
 * @since  21.06.2018
 */
@SpringBootApplication(scanBasePackages = ["ru.ursip.webservice"])
@EnableJpaRepositories("ru.ursip.webservice.mgsn.oshs.repository")
@EnableTransactionManagement
class OshsApplication

/**
 * Стартовый метод приложения
 *
 * @param args аргументы
 */
fun main(args: Array<String>) {
    runApplication<OshsApplication>(*args)
}
