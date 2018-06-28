package ru.ursip.webservice.mgsn.oshs.service

import ru.ursip.webservice.mgsn.oshs.model.Department
import java.util.*

interface DepartmentService {
    fun getAll(): List<Department>

    fun create(department: Department): Department

    fun update(department: Department): Department

    fun delete(id: UUID)
}