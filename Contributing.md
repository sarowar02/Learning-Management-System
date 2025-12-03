# Contributing to Learning Management System (LMS)

Thank you for your interest in contributing to our **Learning Management System** project! This academic Spring Boot project welcomes improvements, bug fixes, and new features from the community.

## 🏗️ Project Structure

Our repository has **3 role-specific branches** for organized development:

| Branch | Purpose | Features |
|--------|---------|----------|
| `admin` | Admin panel & user management | User verification, dashboard  |
| `student` | Student interface | Course joining, materials view  |
| `teacher` | Teacher dashboard | Classroom creation, routine, content upload  |
| `main` | Production ready code | Complete integrated system |

## How to Contribute

1. **Fork the repository**:
   ```
   https://github.com/sarowar02/Learning-Management-System
   ```

2. **Clone your fork**:
   ```bash
   git clone https://github.com/YOUR_USERNAME/Learning-Management-System.git
   cd Learning-Management-System
   ```

3. **Create a feature branch** from the appropriate role branch:
   ```bash
   # For admin features
   git checkout -b admin/your-feature-name origin/admin
   
   # For student features  
   git checkout -b student/your-feature-name origin/student
   
   # For teacher features
   git checkout -b teacher/your-feature-name origin/teacher
   ```

4. **Make your changes** and test locally:
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Commit with clear messages**:
   ```bash
   git add .
   git commit -m "feat(admin): add user bulk import feature"
   git push origin admin/your-feature-name
   ```

6. **Open a Pull Request** to the corresponding branch:
   - Target: `admin` branch (for admin features)
   - Target: `student` branch (for student features)
   - Target: `teacher` branch (for teacher features)

## 💡 Contribution Ideas

- **Admin branch**: User management enhancements, analytics dashboard
- **Student branch**: Better course UI, mobile responsiveness, progress tracking
- **Teacher branch**: Advanced content upload, grading system, attendance
- **Cross-branch**: Security improvements, API optimizations, testing

