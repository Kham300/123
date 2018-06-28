package ru.ursip.webservice.mgsn.oshs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.ursip.webservice.mgsn.oshs.model.Employee
import java.util.*

interface EmployeeDao : JpaRepository<Employee, UUID> {

    @Query("select e from Employee e left join fetch e.delegationsTo left join fetch e.delegationsFrom where e.departmentId=:id")
    fun getByDepartmentId(id: UUID): List<Employee>

    fun getById(id: UUID): Employee
}