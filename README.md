# 🛒 Market With Data Persistence (Java)

A simple **Market System** made in **Java**, designed to simulate a basic market workflow while using **file storage** to persist data.

This project saves information in files, meaning the data is not lost when the program is closed.

---

## 📋 Description

This project is a market application developed in Java.  
It allows you to manage market data and store it using file persistence, so the information remains saved even after the program stops running.

It is a great project to practice:

- Object-Oriented Programming (OOP)
- File handling in Java
- Data persistence without databases

---

## ✨ Features

- 🛍️ Market simulation system
- 💾 Data persistence using files
- 📂 Automatically saves and loads stored information
- 🧾 Basic product management
- 🔄 Data remains available after restarting the program

---

## 🛠️ Technologies Used

- Java (JDK 8+ recommended)
- File I/O (`File`, `FileWriter`, `BufferedReader`, etc.)
- Object-Oriented Programming (OOP)

---

## 📁 Project Structure

Market system logic → file writing/reading → saved data restored when program starts.

---

## ▶️ How to Run

1. Make sure you have **Java installed**:

bash

    java --version
  
2. Clone the repository:

bash
  
    git clone https://github.com/your_username/market_with_data_persistence_java
  
    cd market_with_data_persistence_java
  
3. Compile the project:
   
bash

    javac Main.java
  
4. Run the program:

bash
  
    java Main

---

## 💾 Data Persistence

All market data is stored in files, allowing the system to reload saved information automatically the next time the program runs.

This simulates how a real system works, but without using databases.
