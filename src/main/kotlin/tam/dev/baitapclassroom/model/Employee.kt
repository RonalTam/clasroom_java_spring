package tam.dev.baitapclassroom.model

import jakarta.validation.constraints.*

data class Employee(
    val id: Int = 0,

    @field:NotBlank(message = "Tên không được để trống")
    @field:Size(min = 5, message = "Tên phải có tối thiểu 5 ký tự")
    var name: String = "",

    @field:Min(value = 100, message = "Lương tối thiểu là 100")
    var salary: Double = 0.0,

    @field:Email(message = "Email phải đúng định dạng")
    var email: String = "",

    var gender: String = "",
    var department: String = "",
    var skills: List<String> = emptyList()
)

