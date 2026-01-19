package tam.dev.baitapclassroom.service

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class EnglishGreetingService : GreetingService {
    override fun greet(): String {
        return "Hello Spring MVC"
    }
}

