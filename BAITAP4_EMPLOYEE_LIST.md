# Bài Tập 4: Sử dụng JSTL/Thymeleaf để hiển thị danh sách

## ✅ Đã hoàn thành

### 1. Model Employee
- File: `src/main/kotlin/tam/dev/baitapclassroom/model/Employee.kt`
- Đã có sẵn với các thuộc tính: id, name, salary

### 2. Controller
- File: `src/main/kotlin/tam/dev/baitapclassroom/controller/EmployeeController.kt`
- **Đã cập nhật**: 
  - Endpoint: `/employee/list`
  - Tạo danh sách 5 nhân viên với lương theo đô la ($)
  - Lương từ $750 - $1500 để demo điều kiện

### 3. View Template
- File: `src/main/resources/templates/list-employees.html`
- **Tính năng đã thực hiện**:

#### a) th:each (tương tự c:forEach)
```html
<tr th:each="employee : ${employees}">
```
- Duyệt qua tất cả nhân viên trong danh sách

#### b) th:classappend với điều kiện (tương tự c:if)
```html
<tr th:classappend="${employee.salary > 1000} ? 'high-salary' : ''">
```
- Tô đỏ (CSS class 'high-salary') nếu lương > $1000

#### c) th:if và th:unless (tương tự c:choose)
```html
<span th:if="${employee.salary >= 1000}" class="badge badge-excellent">Giỏi</span>
<span th:unless="${employee.salary >= 1000}" class="badge badge-good">Khá</span>
```
- Hiển thị "Giỏi" nếu lương ≥ $1000
- Hiển thị "Khá" nếu lương < $1000

## 🎨 Thiết kế giao diện

- ✅ Bảng HTML với 4 cột: Mã NV, Tên, Lương, Xếp loại
- ✅ Dòng có lương > $1000 được tô màu đỏ
- ✅ Badge xếp loại với màu sắc khác nhau (Giỏi = xanh lá, Khá = xanh dương)
- ✅ Giao diện đẹp mắt với gradient và hiệu ứng hover
- ✅ Chú thích giải thích ý nghĩa màu sắc

## 🚀 Cách chạy

1. Khởi động ứng dụng:
```bash
./gradlew bootRun
```

2. Mở trình duyệt và truy cập:
```
http://localhost:8080/employee/list
```

## 📊 Dữ liệu demo

| ID | Tên | Lương | Xếp loại | Tô đỏ |
|----|-----|-------|----------|-------|
| 1 | Nguyen Van A | $850 | Khá | Không |
| 2 | Tran Thi B | $1200 | Giỏi | ✅ Có |
| 3 | Le Van C | $950 | Khá | Không |
| 4 | Pham Thi D | $1500 | Giỏi | ✅ Có |
| 5 | Hoang Van E | $750 | Khá | Không |

## 🔍 So sánh JSTL vs Thymeleaf

| JSTL (JSP) | Thymeleaf (Spring Boot) |
|------------|-------------------------|
| `<c:forEach>` | `th:each` |
| `<c:if>` | `th:if` / `th:classappend` |
| `<c:choose>` | `th:if` + `th:unless` |

**Lưu ý**: Dự án này sử dụng Spring Boot với Thymeleaf thay vì JSP, nhưng các tính năng tương đương hoàn toàn.

