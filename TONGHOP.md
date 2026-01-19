# 📚 Bài Tập Spring MVC - Tổng Hợp

## Danh Sách Bài Tập

### ✅ Bài 1: IoC/DI - Dependency Injection
**Mục tiêu:** Thiết lập môi trường và hiểu cơ chế IoC/DI

**Các file:**
- `service/GreetingService.kt` - Interface
- `service/EnglishGreetingService.kt` - Implementation với @Primary
- `service/VietnameseGreetingService.kt` - Implementation với @Qualifier
- `controller/HomeController.kt` - Controller với @Autowired
- `controller/GreetingController.kt` - Demo @Qualifier

**Endpoints:**
- `http://localhost:8080/` - Hello Spring MVC
- `http://localhost:8080/english` - English greeting
- `http://localhost:8080/vietnamese` - Vietnamese greeting
- `http://localhost:8080/all` - All greetings

**Kiến thức:**
- @Component, @Controller
- @Autowired - Dependency Injection
- @Primary - Bean ưu tiên
- @Qualifier - Chỉ định bean cụ thể
- Interface và Implementation
- Loose coupling

---

### ✅ Bài 2: Calculator - Request Mapping và Parameters
**Mục tiêu:** Làm quen với Request Mapping và xử lý tham số

**Các file:**
- `controller/CalculatorController.kt` - Controller xử lý tính toán
- `templates/calculator.html` - Giao diện HTML với Thymeleaf

**Endpoints:**
- `http://localhost:8080/calculator` - Trang calculator

**Kiến thức:**
- @GetMapping, @PostMapping
- @RequestParam - Nhận tham số từ form
- Model attributes - Gửi dữ liệu về view
- Thymeleaf template engine
- Form handling (POST method)
- Error handling
- HTML form với dropdown

**Chức năng:**
- Cộng, trừ, nhân, chia
- Xử lý lỗi chia cho 0
- Giao diện đẹp với CSS
- Giữ giá trị sau submit

---

## 🚀 Hướng Dẫn Chạy Dự Án

### 1. Build dự án
```bash
.\gradlew.bat clean build
```

### 2. Chạy ứng dụng
```bash
.\gradlew.bat bootRun
```

### 3. Truy cập các endpoint

#### Bài 1 - IoC/DI:
```
http://localhost:8080/
http://localhost:8080/english
http://localhost:8080/vietnamese
http://localhost:8080/all
```

#### Bài 2 - Calculator:
```
http://localhost:8080/calculator
```

---

## 📁 Cấu Trúc Dự Án

```
baitapclassroom/
├── src/main/kotlin/tam/dev/baitapclassroom/
│   ├── BaitapclassroomApplication.kt
│   ├── controller/
│   │   ├── HomeController.kt          [Bài 1]
│   │   ├── GreetingController.kt      [Bài 1]
│   │   └── CalculatorController.kt    [Bài 2]
│   └── service/
│       ├── GreetingService.kt         [Bài 1]
│       ├── EnglishGreetingService.kt  [Bài 1]
│       └── VietnameseGreetingService.kt [Bài 1]
├── src/main/resources/
│   ├── templates/
│   │   └── calculator.html            [Bài 2]
│   └── application.properties
├── build.gradle
├── pom.xml
├── README.md                          [Bài 1]
├── BAITAP2_CALCULATOR.md              [Bài 2]
└── TONGhop.md                        [File này]
```

---

## 🔧 Dependencies Chính

```gradle
dependencies {
    // Spring Boot Web MVC
    implementation 'org.springframework.boot:spring-boot-starter-webmvc'
    
    // Thymeleaf Template Engine (Bài 2)
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    
    // Kotlin Support
    implementation 'org.jetbrains.kotlin:kotlin-reflect'
    implementation 'tools.jackson.module:jackson-module-kotlin'
    
    // DevTools (Auto reload)
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
}
```

---

## 📖 Kiến Thức Đã Học

### Spring MVC Core
- [x] @SpringBootApplication
- [x] @Controller
- [x] @Component
- [x] @GetMapping
- [x] @PostMapping
- [x] @ResponseBody
- [x] @RequestParam
- [x] @Autowired
- [x] @Primary
- [x] @Qualifier

### Thymeleaf
- [x] th:value
- [x] th:text
- [x] th:if
- [x] th:selected
- [x] Template expressions ${...}
- [x] Form binding

### Design Patterns
- [x] Dependency Injection
- [x] Inversion of Control
- [x] MVC Pattern
- [x] Interface-based programming

### Web Development
- [x] HTTP GET/POST
- [x] Form handling
- [x] Request parameters
- [x] Model-View-Controller
- [x] Error handling
- [x] HTML forms
- [x] CSS styling

---

## 🧪 Test Commands

### Test Bài 1 (IoC/DI)
```powershell
# Test các endpoints
Invoke-WebRequest -Uri http://localhost:8080/ -UseBasicParsing
Invoke-WebRequest -Uri http://localhost:8080/english -UseBasicParsing
Invoke-WebRequest -Uri http://localhost:8080/vietnamese -UseBasicParsing
Invoke-WebRequest -Uri http://localhost:8080/all -UseBasicParsing
```

### Test Bài 2 (Calculator)
```powershell
# Phép cộng
$body = @{a='10'; b='5'; operation='+'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body

# Phép nhân
$body = @{a='20'; b='4'; operation='*'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body

# Phép trừ
$body = @{a='100'; b='25'; operation='-'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body

# Phép chia
$body = @{a='50'; b='8'; operation='/'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body

# Test lỗi (chia cho 0)
$body = @{a='10'; b='0'; operation='/'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body
```

---

## 💡 Tips & Best Practices

### 1. Code Organization
- Tách biệt Controller, Service, Model
- Sử dụng package structure rõ ràng
- Interface cho loose coupling

### 2. Spring Annotations
- @Component cho service layer
- @Controller cho web layer
- @Autowired cho DI
- @Primary khi có nhiều implementation
- @Qualifier để chỉ định bean cụ thể

### 3. Error Handling
- Validate input
- Handle edge cases (chia cho 0)
- User-friendly error messages

### 4. UI/UX
- Responsive design
- Clear feedback
- Keep form values after submit
- Smooth animations

---

## 🎯 Kết Quả Đạt Được

### Bài 1: IoC/DI
✅ Hiểu cơ chế Dependency Injection  
✅ Biết cách sử dụng @Autowired  
✅ Xử lý nhiều implementation với @Primary và @Qualifier  
✅ Áp dụng Interface-based programming  
✅ Loose coupling giữa các component  

### Bài 2: Calculator
✅ Hiểu Request Mapping (GET/POST)  
✅ Xử lý form parameters với @RequestParam  
✅ Sử dụng Thymeleaf template  
✅ Binding data với Model  
✅ Error handling gracefully  
✅ Tạo UI đẹp và user-friendly  

---

## 📚 Tài Liệu Tham Khảo

- [Spring Framework Documentation](https://docs.spring.io/spring-framework/reference/)
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [Kotlin Spring Guide](https://spring.io/guides/tutorials/spring-boot-kotlin/)

---

## 🔄 Next Steps

### Bài 3 (Gợi ý):
- Model và Database (JPA/Hibernate)
- CRUD operations
- Form validation
- Session management
- REST API

---

**Chúc mừng bạn đã hoàn thành 2 bài tập đầu tiên! 🎉**

Happy coding! 💻✨

