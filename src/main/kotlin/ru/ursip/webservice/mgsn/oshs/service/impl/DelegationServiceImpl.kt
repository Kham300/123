package ru.ursip.webservice.mgsn.oshs.service.impl

import org.slf4j.LoggerFactory
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Service
import ru.ursip.webservice.mgsn.oshs.model.Delegation
import ru.ursip.webservice.mgsn.oshs.repository.DelegationDao
import ru.ursip.webservice.mgsn.oshs.service.DelegationService
import java.util.*

@Service
class DelegationServiceImpl(val delegationDao: DelegationDao,
                            val auditorAware: AuditorAware<String>) : DelegationService {
    /**
     * Логгер
     */
    private val log = LoggerFactory.getLogger("audit")

    override fun create(delegation: Delegation): Delegation {
        if (delegation.id == null) {
            val dep = delegationDao.save(delegation)
            log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} create $dep")
            return dep
        } else throw Exception("delegation should have null id for creation")
    }

    override fun update(delegation: Delegation): Delegation {
        if (delegation.id != null) {
            val dep = delegationDao.getById(delegation.id!!)
            val updatedDep = delegationDao.save(delegation)
            log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} updated from $dep to $updatedDep")
            return updatedDep
        } else throw Exception("delegation should have not null id for update")
    }

    override fun delete(id: UUID) {
        delegationDao.deleteById(id)
        log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} delete delegation with id $id")
    }
}