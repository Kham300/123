package ru.ursip.webservice.mgsn.oshs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.ursip.webservice.mgsn.oshs.model.Employee
import java.util.*

interface EmployeeDao : JpaRepository<Employee, UUID> {

    @Query("""select e from Employee e
        left join fetch e.department
        left join fetch e.delegationsTo delTo
        left join fetch e.delegationsFrom delFrom
        left join fetch delTo.employee delToEmpl
        left join fetch delToEmpl.department
        left join fetch delFrom.delegate delFromDel
        left join fetch delFromDel.department
        where e.department.id=:id""")
    fun getByDepartmentId(id: UUID): Set<Employee>

    fun getById(id: UUID): Employee
}