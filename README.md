# 💳 Employee Payroll Management System 💼

An online enterprise application designed for entering and managing employee payroll information, including employee details, designation, salary, and payment date. This system is built using the **MVC (Model-View-Controller)** architecture to ensure a clean separation of concerns.

## 🚀 Project Objective
The goal is to create a streamlined process for HR/Administrators to:
* Add payroll records with automated ID generation.
* Perform business validations such as Name length and Duplicate checks.
* View specific records or list all payroll history stored in the database.

---

## 📸 Project Screenshots

### **1. Navigation Menu**
<img width="1919" height="472" alt="image" src="https://github.com/user-attachments/assets/bcb4a464-a71b-47a5-88bb-1545231c30f1" />

### **2. Add Payroll Record**
<img width="1919" height="769" alt="image" src="https://github.com/user-attachments/assets/e90981b2-7324-4863-b9b2-123a8e4580e5" />
<img width="1919" height="360" alt="image" src="https://github.com/user-attachments/assets/63a4722c-7747-471a-b79c-98373ad1109f" />

### **3. View All Records**
<img width="1919" height="539" alt="image" src="https://github.com/user-attachments/assets/5b662426-2366-4096-969e-4f8eef2d228b" />
<img width="1919" height="646" alt="image" src="https://github.com/user-attachments/assets/1e50eddc-adcc-46d2-b790-dfa67d66842e" />

### **4. ORACLE Records**
<img width="1701" height="451" alt="image" src="https://github.com/user-attachments/assets/27870968-bc2c-4fe4-9ebe-9c921d712c6a" />
<img width="1695" height="453" alt="image" src="https://github.com/user-attachments/assets/06e5d66f-ae37-4486-8cc7-d74266e1554c" />

---

## 🛠️ Tech Stack
* **Frontend:** HTML5, JSP (Java Server Pages)
* **Backend:** Java Servlets (Controller)
* **Logic Layer:** Service Classes (Administrator)
* **Database:** Oracle DB (JDBC)
* **Server:** Apache Tomcat

---

## 📂 Project Architecture
The project follows a modular package structure as specified in the design:

* **`com.wipro.payroll.bean`**: Contains `PayrollBean` for data encapsulation.
* **`com.wipro.payroll.dao`**: Performs real JDBC operations like inserting and fetching records.
* **`com.wipro.payroll.service`**: The `Administrator` class that handles input from Servlets and performs validations.
* **`com.wipro.payroll.servlets`**: `MainServlet` acting as the Controller to handle user operations.
* **`com.wipro.payroll.util`**: Database connectivity utilities and custom exception handling.

---

## 💾 Database Design
The application utilizes the `PAYROLL_TB` table and `PAYROLL_SEQ` sequence.

### **Table Schema (`PAYROLL_TB`)**
| Column | Datatype | Description |
| :--- | :--- | :--- |
| **RECORDID** | VARCHAR2(12 BYTE) | Primary Key (YYYYMMDD + Name + Seq) |
| **EMPLOYEENAME** | VARCHAR2(40 BYTE) | Not Null |
| **DESIGNATION** | VARCHAR2(30 BYTE) | Not Null |
| **PAYMENT_DATE** | DATE | Not Null |
| **SALARY** | NUMBER | Salary amount |
| **DEPARTMENT** | VARCHAR2(30) | Department name |
| **REMARKS** | VARCHAR2(100) | Additional notes |

### **ID Generation Logic**
The `RECORDID` is generated automatically before storage using the format:
`YYYYMMDD` + `First two uppercase letters of Name` + `Two-digit sequence number`.
*Example: 20240215EM11*

---

## 🧪 Validations & Rules
The `Administrator` class implements the following logic:
1. **Null Check:** Throws `InvalidInputException` if input is null.
2. **Name Length:** Returns "INVALID EMPLOYEE NAME" if length is < 2.
3. **Duplicate Check:** Returns "ALREADY EXISTS" if the record is already in the database.
4. **Operation Tags:** Uses hidden tags (`newRecord`, `viewRecord`, `viewAllRecords`) to identify user actions.

---

## 🚦 How to Run
1. Ensure the Oracle Database table and sequence are created.
2. Update database credentials in `DBUtil.java`.
3. Deploy the project folder on a Tomcat server.
4. Launch `menu.html` in your browser.

---

## 👤 Author
**RASMI RESHA A**

---
