package ru.ursip.webservice.mgsn.oshs.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ursip.webservice.mgsn.oshs.model.Delegation
import ru.ursip.webservice.mgsn.oshs.service.DelegationService
import java.util.*

@Api(tags = ["Работа с делегированием"])
@RestController
@RequestMapping(value = ["/v1/delegation"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
@CrossOrigin
class DelegationController(val delegationService: DelegationService) {
    @PostMapping("/create")
    @ApiOperation("Создание делегирвоания")
    fun create(@ApiParam("Делегирование") @RequestBody delegation: Delegation): ResponseEntity<Any> =
            ResponseEntity.ok(delegationService.create(delegation))

    @PostMapping("/update")
    @ApiOperation("Обновление делегирвоания")
    fun update(@ApiParam("Делегирование") @RequestBody delegation: Delegation): ResponseEntity<Any> =
            ResponseEntity.ok(delegationService.update(delegation))

    @PostMapping("/delete")
    @ApiOperation("Удаление делегирвоания")
    fun delete(@ApiParam("Идентфикатор делегирвоания") @RequestParam("id") id: UUID) =
            delegationService.delete(id)
}