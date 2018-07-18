package ru.ursip.webservice.mgsn.oshs.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ursip.webservice.mgsn.oshs.model.Employee
import ru.ursip.webservice.mgsn.oshs.service.EmployeeService
import java.util.*

@Api(tags = ["Работа с сотрудниками"])
@RestController
@RequestMapping(value = ["/v1/employees"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
@CrossOrigin
class EmployeeController(val employeeService: EmployeeService) {

    @GetMapping("/getByDepartmentId")
    @ApiOperation("Получение сотрудников подраздления")
    fun getAll(@ApiParam("Идентификатор подразделения") @RequestParam("id") id: UUID): ResponseEntity<Any> {
        val employees = employeeService.getByDepartmentId(id)
        return if (employees.isEmpty()) ResponseEntity.noContent().build()
        else ResponseEntity.ok(employees)
    }

    @PostMapping("/create")
    @ApiOperation("Создание сотрудника")
    fun create(@ApiParam("Сотрудник") @RequestBody employee: Employee): ResponseEntity<Any> =
            ResponseEntity.ok(employeeService.create(employee))

    @PostMapping("/update")
    @ApiOperation("Обновление сотрудника")
    fun update(@ApiParam("Сотрудник") @RequestBody employee: Employee): ResponseEntity<Any> =
            ResponseEntity.ok(employeeService.update(employee))

    @PostMapping("/delete")
    @ApiOperation("Удаление сотрудника")
    fun delete(@ApiParam("Идентфикатор сотрудника") @RequestParam("id") id: UUID) =
            employeeService.delete(id)
}