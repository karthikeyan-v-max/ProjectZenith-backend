🌟 Zenith – Project Management System
Zenith is a powerful Project Management System built using Java Spring Boot, designed to streamline collaboration, task tracking, and progress monitoring for teams of all sizes. With integrated email invitations and subscription plans, Zenith is a complete solution for modern project management.

🚀 Features
User Authentication & Authorization – Secure login with role-based access (Admin, Manager, Member)

Project Creation & Management – Easily create, update, and delete projects

Task Assignment & Tracking – Assign tasks to team members and track progress

Deadline & Status Monitoring – Keep projects on schedule with clear status indicators

Commenting System – Communicate and collaborate directly within project tasks

Search & Filter – Quickly find projects or tasks based on keywords and filters

Email Invitations – Project managers can send email invites to members for joining the team using JavaMail SMTP

Subscription Plans – Users start with a free plan and can upgrade to premium plans via Razorpay Payment Gateway

Responsive UI – Optimized for desktop and mobile usage

🛠 Tech Stack
Backend:

Java 17+
Spring Boot
Spring Security
Spring Data JPA
Hibernate

Database:

MySQL

Email Service:

JavaMail API (SMTP)

Payment Gateway:

Razorpay Integration

Build Tool:

Maven

📦 Installation
Clone the repository

bash
Copy
Edit
git clone https://github.com/your-username/zenith.git
cd ProjectZenith-backend
Configure Database

Update application.properties with your database credentials

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Configure Email Service (JavaMail SMTP)

properties
Copy
Edit
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
Configure Razorpay API Keys

properties
Copy
Edit
razorpay.key_id=your_key_id
razorpay.key_secret=your_key_secret
Build & Run the Application

arduino
Copy
Edit
http://localhost:8080
