package ru.ursip.webservice.mgsn.oshs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.ursip.webservice.mgsn.oshs.model.Department
import java.util.*

interface DepartmentDao : JpaRepository<Department, UUID> {
    @Query("select d from Department d")
    fun getAll(): MutableList<Department>

    fun getById(id: UUID): Department
}