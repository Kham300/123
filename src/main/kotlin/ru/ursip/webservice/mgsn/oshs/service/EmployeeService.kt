package ru.ursip.webservice.mgsn.oshs.service

import ru.ursip.webservice.mgsn.oshs.model.Employee
import java.util.*

interface EmployeeService {
    fun getByDepartmentId(id: UUID): Set<Employee>

    fun create(employee: Employee): Employee

    fun update(employee: Employee): Employee

    fun delete(id: UUID)

    fun getById(id: UUID): Employee?
}