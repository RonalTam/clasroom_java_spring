# Bài Tập 6: Tái Cấu Trúc với Thymeleaf Layout

## Mục tiêu
Tái cấu trúc mã nguồn cũ sang công nghệ mới Thymeleaf và áp dụng bố cục chung (layout).

## Các thay đổi đã thực hiện

### 1. Cấu hình Thymeleaf View Resolver
✅ **File:** `src/main/resources/application.properties`

Đã thêm cấu hình Thymeleaf:
```properties
# Thymeleaf Configuration (View Resolver)
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.cache=false
```

### 2. Thêm Thymeleaf Layout Dialect
✅ **File:** `pom.xml`

Đã thêm dependency:
```xml
<!-- Thymeleaf Layout Dialect for Layout Management -->
<dependency>
    <groupId>nz.net.ultraq.thymeleaf</groupId>
    <artifactId>thymeleaf-layout-dialect</artifactId>
</dependency>
```

### 3. Tạo Layout Chung (_layout.html)
✅ **File:** `src/main/resources/templates/_layout.html`

**Cấu trúc Layout:**
- **Header (Đầu trang):**
  - Logo và tiêu đề ứng dụng
  - Menu điều hướng với các link:
    - 🏠 Trang Chủ
    - 👋 Greeting
    - 🧮 Calculator
    - 👤 Nhân Viên
    - 📋 Danh Sách NV
    - ➕ Thêm NV

- **Footer (Chân trang - Bản quyền):**
  - Thông tin dự án
  - Links tham khảo (Spring Framework, Thymeleaf, Spring Guides)
  - Copyright © 2024-2026

**Fragments định nghĩa:**
- `th:fragment="head"` - Phần head với styles chung
- `th:fragment="header"` - Header/Menu
- `th:fragment="footer"` - Footer/Bản quyền

### 4. Viết Lại Trang Danh Sách Nhân Viên (Từ Bài 4)
✅ **File:** `src/main/resources/templates/list-employees.html`

**Thay đổi từ JSP sang Thymeleaf:**

| JSP (Cũ) | Thymeleaf (Mới) |
|----------|-----------------|
| `<c:forEach>` | `th:each` |
| `<c:if>` | `th:if` / `th:unless` |
| `<c:choose>/<c:when>/<c:otherwise>` | `th:if` / `th:unless` |
| Không có layout | Sử dụng `th:replace` để include layout |

**Các tính năng Thymeleaf:**
- ✅ `th:each="employee : ${employees}"` - Duyệt danh sách nhân viên
- ✅ `th:classappend="${employee.salary > 1000} ? 'high-salary' : ''"` - Tô đỏ dòng khi lương > $1000
- ✅ `th:if="${employee.salary >= 1000}"` - Hiển thị "Giỏi" nếu lương ≥ $1000
- ✅ `th:unless="${employee.salary >= 1000}"` - Hiển thị "Khá" nếu lương < $1000
- ✅ `th:replace="_layout :: header"` - Include header từ layout
- ✅ `th:replace="_layout :: footer"` - Include footer từ layout

### 5. Viết Lại Trang Thêm Nhân Viên (Từ Bài 5)
✅ **File:** `src/main/resources/templates/add-employee.html`

**Thay đổi từ JSP Form Tags sang Thymeleaf:**

| JSP Form Tags (Cũ) | Thymeleaf (Mới) |
|---------------------|-----------------|
| `<form:form>` | `<form th:object="${employee}">` |
| `<form:input path="name">` | `<input th:field="*{name}">` |
| `<form:radiobutton>` | `<input type="radio" th:field="*{gender}">` |
| `<form:select>` | `<select th:field="*{department}">` |
| `<form:checkboxes>` | `<input type="checkbox" th:field="*{skills}">` |
| `<form:options>` | `<option th:each>` |

**Các tính năng Thymeleaf:**
- ✅ `th:object="${employee}"` - Bind form với đối tượng Employee
- ✅ `th:field="*{name}"` - Bind input với thuộc tính name
- ✅ `th:field="*{gender}"` - Bind radio buttons với thuộc tính gender
- ✅ `th:field="*{department}"` - Bind select với thuộc tính department
- ✅ `th:each="dept : ${departmentList}"` - Duyệt Map để tạo options
- ✅ `th:field="*{skills}"` - Bind checkboxes với list skills
- ✅ `th:replace="_layout :: header"` - Include header từ layout
- ✅ `th:replace="_layout :: footer"` - Include footer từ layout

## Cách sử dụng Layout với Thymeleaf

### Phương pháp 1: Sử dụng Fragments (Đã áp dụng)
```html
<!-- Trong _layout.html -->
<header th:fragment="header">
  <!-- Nội dung header -->
</header>

<!-- Trong trang con -->
<th:block th:replace="_layout :: header"></th:block>
```

### Phương pháp 2: Sử dụng Layout Dialect (Tùy chọn)
```html
<!-- Trong trang con -->
<html xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="~{_layout}">
```

## Kiểm tra ứng dụng

### Các URL để test:
1. **Danh sách nhân viên (Bài 4):** http://localhost:8080/employee/list
   - Xem danh sách với layout mới
   - Kiểm tra th:each, th:if, th:unless
   - Kiểm tra tô đỏ dòng lương > $1000

2. **Thêm nhân viên (Bài 5):** http://localhost:8080/employee/add
   - Xem form với layout mới
   - Kiểm tra th:object, th:field
   - Kiểm tra radio buttons, select, checkboxes

### Chạy ứng dụng:
```bash
# Sử dụng Gradle
./gradlew bootRun

# Hoặc sử dụng Maven
mvn spring-boot:run
```

## So sánh JSP vs Thymeleaf

### Ưu điểm của Thymeleaf:
1. ✅ **Natural Templates** - Có thể xem HTML trực tiếp trên browser mà không cần server
2. ✅ **Không cần thư viện JSTL** - Tích hợp sẵn với Spring Boot
3. ✅ **Tích hợp tốt với Spring MVC** - th:object, th:field tự động bind dữ liệu
4. ✅ **Layout linh hoạt** - Sử dụng fragments hoặc layout dialect
5. ✅ **Syntax đơn giản hơn** - th:each, th:if, th:text dễ đọc

### Nhược điểm của JSP:
1. ❌ Cần compile trước khi chạy
2. ❌ Không thể xem HTML tĩnh
3. ❌ Cần thư viện JSTL bổ sung
4. ❌ Khó khăn trong việc tái sử dụng layout

## Kết luận

Đã hoàn thành tái cấu trúc mã nguồn từ JSP sang Thymeleaf với các điểm chính:
- ✅ Cấu hình Thymeleaf View Resolver
- ✅ Tạo layout chung với header và footer
- ✅ Viết lại danh sách nhân viên sử dụng th:each, th:if, th:unless
- ✅ Viết lại form thêm nhân viên sử dụng th:object, th:field
- ✅ Áp dụng layout cho cả 2 trang bằng th:replace

Dự án đã sẵn sàng để chạy và test!
