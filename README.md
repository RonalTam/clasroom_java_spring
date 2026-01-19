# Bài Tập Spring MVC - IoC/DI Demo

## Mục tiêu
Thiết lập môi trường phát triển và hiểu cách hoạt động của cơ chế IoC/DI trong Spring Framework.

## Cấu trúc dự án

### 1. Interface GreetingService
```
src/main/kotlin/tam/dev/baitapclassroom/service/GreetingService.kt
```
- Định nghĩa contract cho greeting service
- Phương thức: `greet(): String`

### 2. Implementations của GreetingService

#### EnglishGreetingService
```
src/main/kotlin/tam/dev/baitapclassroom/service/EnglishGreetingService.kt
```
- Implement GreetingService
- Được đánh dấu với `@Component` và `@Primary`
- Trả về chuỗi "Hello Spring MVC"

#### VietnameseGreetingService
```
src/main/kotlin/tam/dev/baitapclassroom/service/VietnameseGreetingService.kt
```
- Implement GreetingService
- Được đánh dấu với `@Component("vietnameseGreeting")`
- Trả về chuỗi "Xin chào Spring MVC"

### 3. Controllers

#### HomeController
```
src/main/kotlin/tam/dev/baitapclassroom/controller/HomeController.kt
```
- Được đánh dấu với `@Controller`
- Sử dụng `@Autowired` để inject GreetingService (mặc định là EnglishGreetingService do @Primary)
- Endpoint `/` trả về message từ service

#### GreetingController
```
src/main/kotlin/tam/dev/baitapclassroom/controller/GreetingController.kt
```
- Được đánh dấu với `@Controller`
- Demo cách sử dụng `@Qualifier` để inject bean cụ thể
- Endpoints:
  - `/english` - Sử dụng EnglishGreetingService
  - `/vietnamese` - Sử dụng VietnameseGreetingService (với @Qualifier)
  - `/all` - Hiển thị cả hai loại greeting

## Cách chạy ứng dụng

### Sử dụng Gradle (Hiện tại):
```bash
# Build dự án
.\gradlew.bat clean build

# Chạy ứng dụng
.\gradlew.bat bootRun
```

### Sử dụng Maven (Nếu muốn chuyển sang Maven):
```bash
# Build dự án
mvn clean install

# Chạy ứng dụng
mvn spring-boot:run
```

## Kiểm tra ứng dụng

### Các Endpoint có sẵn:

#### 1. Endpoint chính (/)
```
http://localhost:8080/
```
Trả về: `Hello Spring MVC`

#### 2. English Greeting (/english)
```
http://localhost:8080/english
```
Trả về: `Hello Spring MVC`

#### 3. Vietnamese Greeting (/vietnamese)
```
http://localhost:8080/vietnamese
```
Trả về: `Xin chào Spring MVC`

#### 4. All Greetings (/all)
```
http://localhost:8080/all
```
Trả về:
```
English: Hello Spring MVC
Vietnamese: Xin chào Spring MVC
```

### Trên Console:
Khi truy cập endpoint, message sẽ được in ra console:
```
Console output: Hello Spring MVC
English greeting: Hello Spring MVC
Vietnamese greeting: Xin chào Spring MVC
```

### Trên trình duyệt:
Mở trình duyệt và truy cập các URL trên.

### Sử dụng PowerShell:
```powershell
# Test endpoint chính
Invoke-WebRequest -Uri http://localhost:8080/ -UseBasicParsing | Select-Object -ExpandProperty Content

# Test English greeting
Invoke-WebRequest -Uri http://localhost:8080/english -UseBasicParsing | Select-Object -ExpandProperty Content

# Test Vietnamese greeting
Invoke-WebRequest -Uri http://localhost:8080/vietnamese -UseBasicParsing | Select-Object -ExpandProperty Content

# Test all greetings
Invoke-WebRequest -Uri http://localhost:8080/all -UseBasicParsing | Select-Object -ExpandProperty Content
```

## Giải thích cơ chế IoC/DI

### Inversion of Control (IoC)
- Spring Container quản lý vòng đời của các bean
- `@Component` đánh dấu EnglishGreetingService và VietnameseGreetingService để Spring tự động tạo và quản lý
- `@Controller` đánh dấu HomeController và GreetingController để Spring quản lý
- Lập trình viên không cần tạo và quản lý object thủ công

### Dependency Injection (DI)
- `@Autowired` yêu cầu Spring tự động inject GreetingService vào Controller
- Spring tìm bean phù hợp và inject vào
- Không cần tạo object thủ công: `new EnglishGreetingService()`

### Xử lý nhiều Implementation
Khi có nhiều class implement cùng một interface:

#### 1. Sử dụng @Primary
```kotlin
@Component
@Primary  // Bean này sẽ được ưu tiên khi inject
class EnglishGreetingService : GreetingService
```

#### 2. Sử dụng @Qualifier
```kotlin
@Autowired
@Qualifier("vietnameseGreeting")  // Chỉ định bean cụ thể
private lateinit var vietnameseGreetingService: GreetingService
```

### Lợi ích của IoC/DI
- ✅ **Loose Coupling**: Controller không phụ thuộc vào implementation cụ thể
- ✅ **Easy Testing**: Dễ dàng thay thế implementation khi test
- ✅ **Maintainability**: Dễ bảo trì và mở rộng
- ✅ **Flexibility**: Có thể thay đổi implementation mà không sửa code Controller

## Cấu hình

### File build.gradle
- Spring Boot Starter Web MVC: `spring-boot-starter-webmvc`
- Kotlin support với Spring

### File pom.xml (Tham khảo)
- Đã tạo file pom.xml tương đương để tham khảo
- Chứa cấu hình Spring Web MVC dependencies

## Kết quả
✅ Ứng dụng đã được build và chạy thành công
✅ Controller đã inject Service thông qua @Autowired
✅ Message "Hello Spring MVC" hiển thị cả trên console và trình duyệt

