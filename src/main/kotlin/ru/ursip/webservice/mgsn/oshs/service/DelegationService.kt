package ru.ursip.webservice.mgsn.oshs.service

import org.springframework.stereotype.Service
import ru.ursip.webservice.mgsn.oshs.model.Delegation
import java.util.*

@Service
interface DelegationService {
    fun create(delegation: Delegation): Delegation

    fun update(delegation: Delegation): Delegation

    fun delete(id: UUID)
}