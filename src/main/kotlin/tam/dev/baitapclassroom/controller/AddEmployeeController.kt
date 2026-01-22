package tam.dev.baitapclassroom.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import tam.dev.baitapclassroom.model.Employee

@Controller
@RequestMapping("/employee")
class AddEmployeeController {

    // Dữ liệu phòng ban (Map)
    @ModelAttribute("departmentList")
    fun getDepartmentList(): Map<String, String> {
        return mapOf(
            "CNTT" to "Công nghệ thông tin",
            "NhanSu" to "Nhân sự",
            "KinhDoanh" to "Kinh doanh"
        )
    }

    // Dữ liệu kỹ năng
    @ModelAttribute("skillList")
    fun getSkillList(): List<String> {
        return listOf("Java", "C#", "Python")
    }

    // Hiển thị form thêm nhân viên
    @GetMapping("/add")
    fun showAddForm(model: Model): String {
        model.addAttribute("employee", Employee())
        return "add-employee"
    }

    // Xử lý submit form
    @PostMapping("/add")
    fun submitForm(@ModelAttribute("employee") employee: Employee, model: Model): String {
        // Đẩy thông tin nhân viên sang trang kết quả
        model.addAttribute("employee", employee)
        return "result"
    }
}

