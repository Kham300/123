package ru.ursip.webservice.mgsn.oshs.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.ursip.webservice.mgsn.oshs.model.Delegation
import java.util.*

interface DelegationDao : JpaRepository<Delegation, UUID> {
    fun getById(id: UUID): Delegation
}