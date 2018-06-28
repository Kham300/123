package ru.ursip.webservice.mgsn.oshs.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@ApiModel(value = "Делегирование")
@EntityListeners(AuditingEntityListener::class)
data class Delegation(

        @ApiModelProperty("Идентификатор")
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: UUID? = null,

        @ApiModelProperty("Идентификатор сотрудника, делегирующего свои полномочия")
        var employeeId: UUID? = null,

        @ApiModelProperty("Идентификатор сотрудника, которому делигировали полномочия")
        var delegateId: UUID? = null,

        @ApiModelProperty("Дата начала срока делегирвоания")
        var startDate: Date? = null,

        @ApiModelProperty("Дата окнчания сркоа делегирвоания")
        var endDate: Date? = null,

        @ApiModelProperty("Номер документа основания")
        var docNumber: String? = null,

        @ApiModelProperty("Наименвоание документа основания")
        var docName: String? = null,

        @ApiModelProperty("Файл доумента основания")
        var fileId: String? = null,

        @ApiModelProperty("Кем создано")
        @CreatedBy
        var createBy: String? = null,

        @ApiModelProperty("Дата создания")
        @CreatedDate
        var createDate: Date? = Date()
)