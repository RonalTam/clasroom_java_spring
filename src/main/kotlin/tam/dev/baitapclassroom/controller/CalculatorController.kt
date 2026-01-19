package tam.dev.baitapclassroom.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class CalculatorController {

    @GetMapping("/calculator")
    fun showCalculator(model: Model): String {
        return "calculator"
    }

    @PostMapping("/calculate")
    fun calculate(
        @RequestParam("a") a: Double,
        @RequestParam("b") b: Double,
        @RequestParam("operation") operation: String,
        model: Model
    ): String {
        val result: Double = when (operation) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> {
                if (b == 0.0) {
                    model.addAttribute("error", "Không thể chia cho 0!")
                    model.addAttribute("a", a)
                    model.addAttribute("b", b)
                    model.addAttribute("operation", operation)
                    return "calculator"
                }
                a / b
            }
            else -> {
                model.addAttribute("error", "Phép tính không hợp lệ!")
                model.addAttribute("a", a)
                model.addAttribute("b", b)
                model.addAttribute("operation", operation)
                return "calculator"
            }
        }

        // Hiển thị kết quả
        model.addAttribute("a", a)
        model.addAttribute("b", b)
        model.addAttribute("operation", operation)
        model.addAttribute("result", result)

        // Log ra console
        println("Tính toán: $a $operation $b = $result")

        return "calculator"
    }
}

