package ru.ursip.webservice.mgsn.oshs.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@ApiModel("Отдел")
@EntityListeners(AuditingEntityListener::class)
data class Department(

        @ApiModelProperty("Идентификатор")
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: UUID? = null,

        @ApiModelProperty("Идентификатор родительского подразделения")
        var parentId: UUID? = null,

        @ApiModelProperty("Наименвоание подразделения")
        var name: String? = null,

        @ApiModelProperty("Кем создано")
        @CreatedBy
        var createBy: String? = null,

        @ApiModelProperty("Дата создания")
        @CreatedDate
        var createDate: Date? = Date()
)