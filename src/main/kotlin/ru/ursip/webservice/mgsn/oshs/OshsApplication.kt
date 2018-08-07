package ru.ursip.webservice.mgsn.oshs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Стартовый класс приложения
 *
 * @author Anton Kuznetsov
 * @since  21.06.2018
 */
@SpringBootApplication(scanBasePackages = ["ru.ursip.webservice"])
class OshsApplication

fun main(args: Array<String>) {
    runApplication<OshsApplication>(*args)
}
