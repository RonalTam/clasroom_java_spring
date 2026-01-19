package tam.dev.baitapclassroom.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import tam.dev.baitapclassroom.model.Employee

@Controller
@RequestMapping("/employee")
class EmployeeController {

    @GetMapping("/single")
    fun showSingleEmployee(model: Model): String {
        // Tạo một đối tượng Employee với dữ liệu cố định
        val employee = Employee(
            id = 1,
            name = "Nguyen Van A",
            salary = 15000000.0
        )

        // Đẩy đối tượng employee sang View
        model.addAttribute("employee", employee)

        return "employee"
    }

    @GetMapping("/list")
    fun showEmployeeList(model: Model): String {
        // Tạo danh sách 5 nhân viên giả lập
        val employeeList = listOf(
            Employee(id = 1, name = "Nguyen Van A", salary = 15000000.0),
            Employee(id = 2, name = "Tran Thi B", salary = 18000000.0),
            Employee(id = 3, name = "Le Van C", salary = 20000000.0),
            Employee(id = 4, name = "Pham Thi D", salary = 22000000.0),
            Employee(id = 5, name = "Hoang Van E", salary = 25000000.0)
        )

        // Đẩy danh sách nhân viên sang View
        model.addAttribute("employees", employeeList)

        return "employee-list"
    }
}

