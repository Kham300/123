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
            val textMessage = StringBuilder().apply {
                append(if (delegation.delegate!!.getMale()) "Уважаемая," else "Уважамый,")
                append(" ${delegation.delegate!!.getFullName()}!")
                append("\n\n${delegation.employee!!.getFullName()} ")
                append(if (delegation.employee!!.getMale()) "делегировала" else "делегировал")
                append(" Вам свои полномочия")
                delegation.startDate?.let { append(" с ${it.format(formatter)}") }
                delegation.endDate?.let { append(" по ${it.format(formatter)}") }
                if (delegation.docName != null || delegation.docNumber != null) append(".\nНа основании")
                delegation.docName?.let { append(" $it") }
                delegation.docNumber?.let { append(" № $it") }
                append(".\n\nС уважением, МГСН")
            }.toString()
            message.setText(textMessage)
            sender.send(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun Date.format(formatter: DateTimeFormatter): String = this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
