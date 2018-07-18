package ru.ursip.webservice.mgsn.oshs

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Стартовый класс приложения
 *
 * @author Anton Kuznetsov
 * @since  21.06.2018
 */
@SpringBootApplication(scanBasePackages = ["ru.ursip.webservice"])
class OshsApplication

/**
 * Стартовый метод приложения
 *
 * @param args аргументы
 */
fun main(args: Array<String>) {
    SpringApplication.run(OshsApplication::class.java, *args)
}
