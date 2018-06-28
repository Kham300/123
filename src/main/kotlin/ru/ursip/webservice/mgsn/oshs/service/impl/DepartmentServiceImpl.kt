package ru.ursip.webservice.mgsn.oshs.service.impl

import org.slf4j.LoggerFactory
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Service
import ru.ursip.webservice.mgsn.oshs.model.Department
import ru.ursip.webservice.mgsn.oshs.repository.DepartmentDao
import ru.ursip.webservice.mgsn.oshs.service.DepartmentService
import java.util.*
import javax.transaction.Transactional

@Service
class DepartmentServiceImpl(val departmentDao: DepartmentDao,
                            val auditorAware: AuditorAware<String>) : DepartmentService {

    /**
     * Логгер
     */
    private val log = LoggerFactory.getLogger("audit")

    override fun getAll(): List<Department> = departmentDao.getAll()

    @Transactional
    override fun create(department: Department): Department {
        if (department.id == null) {
            val dep = departmentDao.save(department)
            log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} create $dep")
            return dep
        } else throw Exception("department should have null id for creation")
    }

    @Transactional

    override fun update(department: Department): Department {
        if (department.id != null) {
            val dep = departmentDao.getById(department.id!!)
            val updatedDep = departmentDao.save(department)
            log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} updated from $dep to $updatedDep")
            return updatedDep
        } else throw Exception("department should have not null id for update")
    }

    override fun delete(id: UUID) {
        departmentDao.deleteById(id)
        log.debug("${auditorAware.currentAuditor.orElse("Unknown user")} delete department with id $id")
    }
}