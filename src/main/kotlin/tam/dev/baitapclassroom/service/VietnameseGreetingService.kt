package tam.dev.baitapclassroom.service

import org.springframework.stereotype.Component

@Component("vietnameseGreeting")
class VietnameseGreetingService : GreetingService {
    override fun greet(): String {
        return "Xin chào Spring MVC"
    }
}

