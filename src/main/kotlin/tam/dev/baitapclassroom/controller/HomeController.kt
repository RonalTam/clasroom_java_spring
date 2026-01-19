package tam.dev.baitapclassroom.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import tam.dev.baitapclassroom.service.GreetingService

@Controller
class HomeController {

    @Autowired
    private lateinit var greetingService: GreetingService

    @GetMapping("/")
    @ResponseBody
    fun home(): String {
        val message = greetingService.greet()
        println("Console output: $message")
        return message
    }
}

