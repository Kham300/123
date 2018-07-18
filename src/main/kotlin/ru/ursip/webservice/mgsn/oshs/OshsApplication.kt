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
class OshsApplication {
//    companion object {
//        @JvmStatic
//        fun main(args: Array<String>) {
//            SpringApplication.run(OshsApplication::class.java, *args)
//        }
//    }
}


//    /**
//     * Стартовый метод приложения
//     *
//     * @param args аргументы
//     */
//    fun main(args: Array<String>) {
//        SpringApplication.run(OshsApplication::class.java, *args)
//    }

fun main(args: Array<String>) {
    runApplication<OshsApplication>(*args)
}
