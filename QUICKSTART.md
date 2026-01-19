# ⚡ Quick Start Guide - Calculator Application

## 🚀 Chạy Ngay (3 Bước)

### Bước 1: Build
```bash
.\gradlew.bat clean build
```

### Bước 2: Run
```bash
.\gradlew.bat bootRun
```

### Bước 3: Open Browser
```
http://localhost:8080/calculator
```

---

## 🎯 Các Endpoint Có Sẵn

| URL | Method | Mô tả |
|-----|--------|-------|
| `/calculator` | GET | Hiển thị form calculator |
| `/calculate` | POST | Xử lý phép tính |
| `/` | GET | Hello Spring MVC (Bài 1) |
| `/english` | GET | English greeting (Bài 1) |
| `/vietnamese` | GET | Vietnamese greeting (Bài 1) |
| `/all` | GET | All greetings (Bài 1) |

---

## 📝 Quick Test

### Test trong Browser
1. Mở: `http://localhost:8080/calculator`
2. Nhập: a=10, operation=+, b=5
3. Kết quả: 15.00 ✅

### Test bằng PowerShell
```powershell
# Phép cộng
$body = @{a='10'; b='5'; operation='+'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body

# Phép nhân
$body = @{a='20'; b='4'; operation='*'}
Invoke-WebRequest -Uri http://localhost:8080/calculate -Method POST -Body $body
```

---

## 📁 File Structure (Important)

```
src/main/
├── kotlin/tam/dev/baitapclassroom/
│   ├── controller/
│   │   ├── CalculatorController.kt    ⭐ Main Controller
│   │   ├── HomeController.kt
│   │   └── GreetingController.kt
│   └── service/
│       ├── GreetingService.kt
│       ├── EnglishGreetingService.kt
│       └── VietnameseGreetingService.kt
└── resources/
    └── templates/
        └── calculator.html             ⭐ Main View
```

---

## 🔧 Dependencies (Already Added)

- ✅ Spring Boot Web MVC
- ✅ Thymeleaf
- ✅ Kotlin
- ✅ DevTools

---

## 💡 Common Commands

```bash
# Build
.\gradlew.bat build

# Run
.\gradlew.bat bootRun

# Clean build
.\gradlew.bat clean build

# Stop (if port busy)
Get-Process | Where-Object {$_.ProcessName -like '*java*'} | Stop-Process -Force
```

---

## 📚 Documentation

- Full Guide: `BAITAP2_CALCULATOR.md`
- Summary: `SUMMARY_CALCULATOR.md`
- All Exercises: `TONGHOP.md`
- Bài 1 IoC/DI: `README.md`

---

## ✅ Features

- ✅ Addition (+)
- ✅ Subtraction (-)
- ✅ Multiplication (*)
- ✅ Division (/)
- ✅ Error handling (divide by zero)
- ✅ Beautiful UI with animations
- ✅ Form value preservation

---

## 🎨 UI Preview

```
🧮 Máy Tính Calculator
┌──────────────────────┐
│ a:  [  10  ]        │
│ op: [ + ▼ ]         │
│ b:  [   5  ]        │
│                      │
│  [🔢 Tính Toán]     │
├──────────────────────┤
│   ✨ Kết Quả        │
│     10 + 5          │
│    = 15.00          │
└──────────────────────┘
```

---

## 🐛 Troubleshooting

### Port 8080 already in use?
```powershell
Get-Process | Where-Object {$_.ProcessName -like '*java*'} | Stop-Process -Force
```

### Build failed?
```bash
.\gradlew.bat clean build --refresh-dependencies
```

### Template not found?
Check: `src/main/resources/templates/calculator.html` exists

---

**That's it! Happy Calculating! 🎉**

