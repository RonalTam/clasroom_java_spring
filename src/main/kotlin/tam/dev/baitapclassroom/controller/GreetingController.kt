package tam.dev.baitapclassroom.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import tam.dev.baitapclassroom.service.GreetingService

@Controller
class GreetingController {

    @Autowired
    @Qualifier("vietnameseGreeting")
    private lateinit var vietnameseGreetingService: GreetingService

    @Autowired
    private lateinit var englishGreetingService: GreetingService

    @GetMapping("/english")
    @ResponseBody
    fun englishGreeting(): String {
        val message = englishGreetingService.greet()
        println("English greeting: $message")
        return message
    }

    @GetMapping("/vietnamese")
    @ResponseBody
    fun vietnameseGreeting(): String {
        val message = vietnameseGreetingService.greet()
        println("Vietnamese greeting: $message")
        return message
    }

    @GetMapping("/all")
    @ResponseBody
    fun allGreetings(): String {
        return """
            English: ${englishGreetingService.greet()}
            Vietnamese: ${vietnameseGreetingService.greet()}
        """.trimIndent()
    }
}

