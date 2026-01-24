# Bài Tập 7: Validation với Spring

## Mục tiêu
Đảm bảo dữ liệu đầu vào sạch và đúng định dạng sử dụng Bean Validation.

## Các thay đổi đã thực hiện

### 1. Thêm Dependency Validation

**File: `build.gradle`**
```groovy
implementation 'org.springframework.boot:spring-boot-starter-validation'
```

**File: `pom.xml`**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### 2. Bổ sung Annotation Validation vào Model

**File: `Employee.kt`**

✅ **@NotBlank** - Tên không được để trống
```kotlin
@field:NotBlank(message = "Tên không được để trống")
@field:Size(min = 5, message = "Tên phải có tối thiểu 5 ký tự")
var name: String = ""
```

✅ **@Min** - Lương tối thiểu là 100
```kotlin
@field:Min(value = 100, message = "Lương tối thiểu là 100")
var salary: Double = 0.0
```

✅ **@Email** - Email phải đúng định dạng
```kotlin
@field:Email(message = "Email phải đúng định dạng")
var email: String = ""
```

**Lưu ý:** Sử dụng `@field:` prefix trong Kotlin để áp dụng annotation vào field thay vì property.

### 3. Cập nhật Controller với @Valid và BindingResult

**File: `AddEmployeeController.kt`**

**Import cần thiết:**
```kotlin
import jakarta.validation.Valid
import org.springframework.validation.BindingResult
```

**Xử lý Validation:**
```kotlin
@PostMapping("/add")
fun submitForm(
    @Valid @ModelAttribute("employee") employee: Employee,
    bindingResult: BindingResult,
    model: Model
): String {
    // Kiểm tra lỗi validation
    if (bindingResult.hasErrors()) {
        // Nếu có lỗi, quay lại form với thông báo lỗi
        return "add-employee"
    }
    
    // Nếu không có lỗi, chuyển sang trang kết quả
    model.addAttribute("employee", employee)
    return "result"
}
```

**Các thành phần:**
- `@Valid` - Kích hoạt validation cho object Employee
- `BindingResult` - Chứa kết quả validation và danh sách lỗi
- `bindingResult.hasErrors()` - Kiểm tra có lỗi hay không

### 4. Hiển thị Lỗi trên View

**File: `add-employee.html`**

**Style cho lỗi:**
```css
.error {
    color: red;
    font-size: 0.9em;
    margin-top: 5px;
    display: block;
}

input.error-input {
    border-color: red;
}
```

**Hiển thị lỗi cho từng trường:**

**Tên:**
```html
<input type="text" 
       th:field="*{name}"
       th:classappend="${#fields.hasErrors('name')} ? 'error-input' : ''">
<span class="error" th:if="${#fields.hasErrors('name')}" th:errors="*{name}"></span>
```

**Lương:**
```html
<input type="number" 
       th:field="*{salary}"
       th:classappend="${#fields.hasErrors('salary')} ? 'error-input' : ''">
<span class="error" th:if="${#fields.hasErrors('salary')}" th:errors="*{salary}"></span>
```

**Email:**
```html
<input type="email" 
       th:field="*{email}"
       th:classappend="${#fields.hasErrors('email')} ? 'error-input' : ''">
<span class="error" th:if="${#fields.hasErrors('email')}" th:errors="*{email}"></span>
```

**Các thành phần Thymeleaf:**
- `th:classappend` - Thêm class 'error-input' khi có lỗi
- `#fields.hasErrors('fieldName')` - Kiểm tra field có lỗi không
- `th:errors="*{fieldName}"` - Hiển thị thông báo lỗi

## Cách Test Validation

### 1. Truy cập Form
```
http://localhost:8080/employee/add
```

### 2. Test Case 1: Tên quá ngắn
- **Input:** Tên = "ABC" (< 5 ký tự)
- **Expected:** Hiển thị lỗi màu đỏ "Tên phải có tối thiểu 5 ký tự"

### 3. Test Case 2: Tên để trống
- **Input:** Tên = ""
- **Expected:** Hiển thị lỗi màu đỏ "Tên không được để trống"

### 4. Test Case 3: Lương quá thấp
- **Input:** Lương = 50 (< 100)
- **Expected:** Hiển thị lỗi màu đỏ "Lương tối thiểu là 100"

### 5. Test Case 4: Email sai format
- **Input:** Email = "abc123"
- **Expected:** Hiển thị lỗi màu đỏ "Email phải đúng định dạng"

### 6. Test Case 5: Dữ liệu hợp lệ
- **Input:** 
  - Tên = "Nguyen Van A"
  - Lương = 1000
  - Email = "nguyenvana@example.com"
- **Expected:** Chuyển sang trang result.html thành công

## Các Annotation Validation Phổ Biến

| Annotation | Mô tả | Ví dụ |
|------------|-------|-------|
| `@NotNull` | Không được null | `@field:NotNull` |
| `@NotBlank` | Không được null, empty hoặc chỉ có space | `@field:NotBlank` |
| `@NotEmpty` | Không được null hoặc empty | `@field:NotEmpty` |
| `@Size(min, max)` | Độ dài String hoặc size Collection | `@field:Size(min=5, max=100)` |
| `@Min(value)` | Giá trị tối thiểu | `@field:Min(100)` |
| `@Max(value)` | Giá trị tối đa | `@field:Max(1000)` |
| `@Email` | Đúng format email | `@field:Email` |
| `@Pattern(regex)` | Khớp với regex | `@field:Pattern(regexp="^[0-9]+$")` |
| `@Positive` | Số dương | `@field:Positive` |
| `@PositiveOrZero` | Số dương hoặc 0 | `@field:PositiveOrZero` |

## Lợi ích của Validation

✅ **Bảo mật:** Ngăn chặn dữ liệu độc hại
✅ **Chất lượng dữ liệu:** Đảm bảo dữ liệu đúng format
✅ **Trải nghiệm người dùng:** Hiển thị lỗi rõ ràng
✅ **Giảm lỗi:** Phát hiện lỗi sớm trước khi lưu database
✅ **Tái sử dụng:** Validation logic tập trung ở model

## Kết luận

Đã hoàn thành Bài Tập 7 với các tính năng:
- ✅ Thêm validation annotations vào Employee model
- ✅ Sử dụng @Valid và BindingResult trong Controller
- ✅ Hiển thị lỗi màu đỏ trên View
- ✅ Validation cho Tên (NotBlank, Size min=5)
- ✅ Validation cho Lương (Min=100)
- ✅ Validation cho Email (Email format)

Ứng dụng đã sẵn sàng để test validation!
