package tam.dev.baitapclassroom.model

data class Employee(
    val id: Int = 0,
    var name: String = "",
    var salary: Double = 0.0,
    var gender: String = "",
    var department: String = "",
    var skills: List<String> = emptyList()
)

