# Bài Tập 2: Calculator - Request Mapping và Xử lý Tham Số

## Mục tiêu
Làm quen với việc ánh xạ yêu cầu (Request Mapping) và xử lý tham số trong Spring MVC.

## Yêu cầu đã hoàn thành
✅ 1. Tạo lớp CalculatorController  
✅ 2. Thiết kế biểu mẫu HTML với 2 ô nhập số a, b và dropdown chọn phép tính  
✅ 3. Gửi form về server bằng POST method  
✅ 4. Sử dụng @RequestParam để nhận tham số và tính toán  
✅ 5. Trả kết quả hiển thị trên giao diện người dùng  

## Cấu trúc dự án

### 1. CalculatorController
```
src/main/kotlin/tam/dev/baitapclassroom/controller/CalculatorController.kt
```

**Các annotation sử dụng:**
- `@Controller` - Đánh dấu class là Controller
- `@GetMapping("/calculator")` - Xử lý GET request để hiển thị form
- `@PostMapping("/calculate")` - Xử lý POST request để tính toán
- `@RequestParam` - Nhận tham số từ form (a, b, operation)

**Chức năng:**
- Nhận 2 số (a, b) và phép tính từ form
- Thực hiện tính toán: +, -, *, /
- Xử lý lỗi chia cho 0
- Trả kết quả về view

### 2. Giao diện HTML
```
src/main/resources/templates/calculator.html
```

**Thymeleaf template với:**
- Form HTML đẹp mắt với CSS gradient
- 2 input fields cho số a và b (type="number")
- Dropdown select cho phép tính (+, -, *, /)
- Submit button
- Hiển thị kết quả động với Thymeleaf
- Hiển thị lỗi nếu có (ví dụ: chia cho 0)

## Cách sử dụng

### 1. Khởi động ứng dụng
```bash
.\gradlew.bat bootRun
```

### 2. Truy cập Calculator
Mở trình duyệt và truy cập:
```
http://localhost:8080/calculator
```

### 3. Sử dụng Calculator
1. Nhập số thứ nhất vào ô "a"
2. Chọn phép tính từ dropdown (+, -, *, /)
3. Nhập số thứ hai vào ô "b"
4. Click nút "Tính Toán"
5. Kết quả sẽ hiển thị ngay bên dưới form

## Ví dụ sử dụng

### Test bằng trình duyệt:
1. Truy cập: http://localhost:8080/calculator
2. Nhập: a = 10, operation = +, b = 5
3. Kết quả: 10 + 5 = 15.00

### Test bằng PowerShell:
```powershell
# Test phép cộng
$body = @{a='10'; b='5'; operation='+'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body

# Test phép nhân
$body = @{a='20'; b='4'; operation='*'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body

# Test phép trừ
$body = @{a='100'; b='25'; operation='-'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body

# Test phép chia
$body = @{a='50'; b='8'; operation='/'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body

# Test lỗi chia cho 0
$body = @{a='10'; b='0'; operation='/'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body
```

## Kết quả Test

### ✅ Phép cộng: 10 + 5 = 15.00
### ✅ Phép nhân: 20 * 4 = 80.00
### ✅ Phép trừ: 100 - 25 = 75.00
### ✅ Phép chia: 50 / 8 = 6.25
### ✅ Xử lý lỗi: 10 / 0 → "Không thể chia cho 0!"

## Kiến thức đã học

### 1. Request Mapping
```kotlin
@GetMapping("/calculator")   // GET request - hiển thị form
@PostMapping("/calculate")   // POST request - xử lý form
```

### 2. Request Parameters
```kotlin
@RequestParam("a") a: Double          // Nhận parameter 'a'
@RequestParam("b") b: Double          // Nhận parameter 'b'
@RequestParam("operation") operation: String  // Nhận parameter 'operation'
```

### 3. Model Attributes
```kotlin
model.addAttribute("result", result)   // Gửi dữ liệu về view
model.addAttribute("error", "...")     // Gửi thông báo lỗi
```

### 4. Thymeleaf Expressions
```html
th:value="${a}"              <!-- Hiển thị giá trị -->
th:selected="${operation == '+'}"  <!-- Set selected option -->
th:if="${error}"             <!-- Conditional rendering -->
th:text="${result}"          <!-- Display text -->
```

## Luồng xử lý (Flow)

1. **User** → Truy cập `/calculator` (GET)
2. **Controller** → Trả về view `calculator.html` (form trống)
3. **User** → Nhập dữ liệu và submit form (POST `/calculate`)
4. **Controller** → Nhận parameters qua `@RequestParam`
5. **Controller** → Thực hiện tính toán
6. **Controller** → Add result/error vào Model
7. **Controller** → Trả về view `calculator.html` (hiển thị kết quả)
8. **User** → Xem kết quả trên màn hình

## Xử lý lỗi

### Chia cho 0
```kotlin
if (b == 0.0) {
    model.addAttribute("error", "Không thể chia cho 0!")
    return "calculator"
}
```

### Phép tính không hợp lệ
```kotlin
else -> {
    model.addAttribute("error", "Phép tính không hợp lệ!")
    return "calculator"
}
```

## Tính năng đặc biệt

### 🎨 Giao diện đẹp
- Gradient background (purple)
- Card design với shadow
- Smooth animations
- Responsive design

### 💡 UX/UI
- Giữ giá trị đã nhập sau khi submit
- Hiển thị phép tính đầy đủ: "10 + 5 = 15.00"
- Error box với animation shake
- Result box với animation slide-in

### 🔢 Tính năng
- Hỗ trợ số thập phân
- Format kết quả: 2 chữ số thập phân
- Log kết quả ra console
- Xử lý lỗi gracefully

## Screenshot

### Form Calculator
```
┌─────────────────────────────────┐
│   🧮 Máy Tính Calculator       │
├─────────────────────────────────┤
│ Số thứ nhất (a): [    10    ]  │
│ Chọn phép tính:  [  + Cộng  ▼] │
│ Số thứ hai (b):  [     5    ]  │
│                                 │
│      [🔢 Tính Toán]             │
├─────────────────────────────────┤
│      ✨ Kết Quả                 │
│        10 + 5                   │
│        = 15.00                  │
└─────────────────────────────────┘
```

## Dependencies

```gradle
implementation 'org.springframework.boot:spring-boot-starter-webmvc'
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
implementation 'org.jetbrains.kotlin:kotlin-reflect'
```

## Tips

### Thêm operation mới
Muốn thêm phép tính mới (ví dụ: %, ^), chỉ cần:
1. Thêm option trong HTML select
2. Thêm case trong when expression của Controller

### Thay đổi giao diện
Tất cả CSS nằm trong thẻ `<style>` của file HTML, dễ dàng customize.

### Debug
Mỗi lần tính toán, kết quả được in ra console:
```
Tính toán: 10.0 + 5.0 = 15.0
```

---

**Bài tập hoàn thành thành công! 🎉**

Đã demo được:
- ✅ Request Mapping (@GetMapping, @PostMapping)
- ✅ Request Parameters (@RequestParam)
- ✅ Form handling với HTML
- ✅ Thymeleaf template engine
- ✅ Model attributes
- ✅ Error handling
- ✅ User-friendly UI

