package ru.ursip.webservice.mgsn.oshs.service.impl

import kotlinx.coroutines.experimental.launch
import org.slf4j.LoggerFactory
import org.springframework.data.domain.AuditorAware
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import ru.ursip.webservice.mgsn.oshs.model.Delegation
import ru.ursip.webservice.mgsn.oshs.repository.DelegationDao
import ru.ursip.webservice.mgsn.oshs.service.DelegationService
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import javax.transaction.Transactional

@Service
class DelegationServiceImpl(val delegationDao: DelegationDao,
                            val auditorAware: AuditorAware<String>,
                            val sender: JavaMailSender) : DelegationService {
    /**
     * Логгер
     */
    private val log = LoggerFactory.getLogger("audit")

    @Transactional
    override fun create(delegation: Delegation): Delegation {
        if (delegation.id == null) {
            val dep = delegationDao.save(delegation)
            launch { sendMessage(delegation) }
            log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} create $dep")
            return dep
        } else throw Exception("delegation should have null id for creation")
    }

    @Transactional
    override fun update(delegation: Delegation): Delegation {
        if (delegation.id != null) {
            val dep = delegationDao.getById(delegation.id!!)
            val updatedDep = delegationDao.save(delegation)
            launch { sendMessage(delegation) }
            log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} updated from $dep to $updatedDep")
            return updatedDep
        } else throw Exception("delegation should have not null id for update")
    }

    @Transactional
    override fun delete(id: UUID) {
        delegationDao.deleteById(id)
        log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} delete delegation with id $id")
    }

    /*
    Корутина для асинхронной отправки почты
     */
    private suspend fun sendMessage(delegation: Delegation) {
        try {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY")
            val message = SimpleMailMessage()
            message.setTo("bobrisheva_ov@ursip.ru", "kuznetsov_as@ursip.ru") //todo поменять на реальный email пользователя
            message.setSubject("Делегирование")
            val textMessage = StringJoiner("""Уважаемый, ${delegation.delegate!!.getFullName()}
${delegation.employee!!.getFullName()} делегировал Вам свои полномочия""").apply {
                delegation.startDate?.let { add(" с ${it.format(formatter)}") }
                delegation.endDate?.let { add(" по ${it.format(formatter)}") }
                if (delegation.docName != null || delegation.docNumber != null) add(".\nНа основании")
                delegation.docName?.let { add(" $it") }
                delegation.docNumber?.let { add(" № $it") }
                add(".\nС уважением, МГСН")
            }.toString()
            message.setText(textMessage)
            sender.send(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun Date.format(formatter: DateTimeFormatter): String = this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
