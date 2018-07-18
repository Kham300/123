package ru.ursip.webservice.mgsn.oshs.service.impl

import org.slf4j.LoggerFactory
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Service
import ru.ursip.webservice.mgsn.oshs.model.Employee
import ru.ursip.webservice.mgsn.oshs.repository.EmployeeDao
import ru.ursip.webservice.mgsn.oshs.service.EmployeeService
import java.util.*
import javax.transaction.Transactional

@Service
class EmployeeServiceImpl(val employeeDao: EmployeeDao,
                          val auditorAware: AuditorAware<String>) : EmployeeService {

    /**
     * Логгер
     */
    private val log = LoggerFactory.getLogger("audit")

    override fun getByDepartmentId(id: UUID): Set<Employee> = employeeDao.getByDepartmentId(id)

    @Transactional
    override fun create(employee: Employee): Employee {
        if (employee.id == null) {
            val savedEmpl = employeeDao.save(employee)
            log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} create $savedEmpl")
            return savedEmpl
        } else throw Exception("employee should have null id for creation")
    }

    override fun update(employee: Employee): Employee {
        if (employee.id != null) {
            val empl = employeeDao.getById(employee.id!!)
            val savedEmpl = employeeDao.save(employee)
            log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} update from $empl to $savedEmpl")
            return savedEmpl
        } else throw Exception("employee should have not null id for creation")
    }

    override fun delete(id: UUID) {
        employeeDao.deleteById(id)
        log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} delete employee with id $id")
    }
}