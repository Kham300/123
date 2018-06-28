package ru.ursip.webservice.mgsn.oshs.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "employees")
@ApiModel("Отдел")
@EntityListeners(AuditingEntityListener::class)
data class Employee(

        @ApiModelProperty("Идентификатор")
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: UUID? = null,

        @ApiModelProperty("Имя")
        var name: String? = null,

        @ApiModelProperty("Фамилия")
        var surname: String? = null,

        @ApiModelProperty("Отчество")
        var patronymic: String? = null,

        @ApiModelProperty("Должность")
        var position: String? = null,

        @ApiModelProperty("Электронная почта")
        var email: String? = null,

        @ApiModelProperty("Теелфон")
        var phone: String? = null,

        @ApiModelProperty("Кабинет")
        var room: String? = null,

        @ApiModelProperty("Фото")
        var photo: String? = null,

        @ApiModelProperty("Примечание")
        var note: String? = null,

        @ApiModelProperty("Группа")
        @Column(name = "employee_group")
        var group: String? = null,

        @ApiModelProperty("Роль")
        var role: String? = null,

        @ApiModelProperty("Логин по СУДИР")
        var loginSydir: String? = null,

        @ApiModelProperty("Признак удаления")
        var isRemoved: Boolean? = false,

        @ApiModelProperty("Признак внешнего пользователя")
        var isExternal: Boolean? = false,

        @ApiModelProperty("Кем создано")
        @CreatedBy
        var createBy: String? = null,

        @ApiModelProperty("Дата создания")
        @CreatedDate
        var createDate: Date? = Date(),

        @ApiModelProperty("Идентификатор подразделения")
        var departmentId: UUID? = null,

        @ApiModelProperty("Делегирования от пользователя")
        @OneToMany(mappedBy = "employeeId")
        var delegationsTo: MutableSet<Delegation>? = null,

        @ApiModelProperty("Делегирования пользователю")
        @OneToMany(mappedBy = "delegateId")
        var delegationsFrom: MutableSet<Delegation>? = null
)