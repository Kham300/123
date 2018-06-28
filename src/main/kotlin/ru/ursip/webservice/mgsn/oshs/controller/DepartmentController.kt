package ru.ursip.webservice.mgsn.oshs.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ursip.webservice.mgsn.oshs.model.Department
import ru.ursip.webservice.mgsn.oshs.service.DepartmentService
import java.util.*

@Api(tags = ["Работа с подразделениями"])
@RestController
@RequestMapping(value = ["/v1/departments"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
@CrossOrigin
class DepartmentController(val departmentService: DepartmentService) {

    @GetMapping("/getAll")
    @ApiOperation("Получение подразделений")
    fun getAll(): ResponseEntity<Any> {
        val departments = departmentService.getAll()
        return if (departments.isEmpty()) ResponseEntity.noContent().build()
        else ResponseEntity.ok(departments)
    }

    @PostMapping("/create")
    @ApiOperation("Создание подразделения")
    fun create(@ApiParam("Подразделение") @RequestBody department: Department): ResponseEntity<Any> =
            ResponseEntity.ok(departmentService.create(department))

    @PostMapping("/update")
    @ApiOperation("Обновление подразделения")
    fun update(@ApiParam("Подразделение") @RequestBody department: Department): ResponseEntity<Any> =
            ResponseEntity.ok(departmentService.update(department))

    @PostMapping("/delete")
    @ApiOperation("Удаление делегирвоания")
    fun delete(@ApiParam("Идентфикатор подразделения") @RequestParam("id") id: UUID) =
            departmentService.delete(id)
}