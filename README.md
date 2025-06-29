# JavaFX Login Application

This is a simple and clean JavaFX-based login application created using FXML. It's designed to simulate a basic login system with features like input validation, show/hide password, forgot password popup, and a clock that updates in real-time.

---

## 📂 Project Structure

```
JavaFX_LoginApp/
├── src/
│   └── application/
│       ├── Main.java
│       ├── LoginController.java
│       └── DashboardController.java (optional)
├── resources/
│   ├── login.fxml
│   └── dashboard.fxml
```

---

## ✅ Requirements

Before running the project, make sure you have the following:

* **Java JDK 17**
* **JavaFX SDK 17 or above**
* **IntelliJ IDEA** (Community Edition is fine)

Download JavaFX from: [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)

---

## ⚙️ Steps to Set Up the Project

### 1. Open the Project

* Open IntelliJ IDEA
* Go to `File > Open` and choose the `JavaFX_LoginApp` folder

### 2. Set Up JavaFX SDK

* Press `Ctrl+Alt+Shift+S` to open Project Structure
* Go to Libraries > Add Java > Select the `lib` folder inside JavaFX SDK

### 3. VM Options (Very Important)

Go to `Run > Edit Configurations...` and in the **VM options** field, paste this:

```
--module-path "D:\Your\Path\To\javafx-sdk-17\lib" --add-modules javafx.controls,javafx.fxml
```

Make sure to change the path based on where you saved JavaFX SDK.

### 4. Resources Setup

* Right-click on the `resources` folder
* Select **Mark Directory as > Resources Root**

### 5. Run the App

* Right-click on `Main.java` > Run
* You should see the login window appear

---

## 🧾 What Each File Does

### Main.java

The entry point of the application. It loads the login screen and shows the JavaFX stage. Nothing too complex here, just the basic window setup.

### LoginController.java

All the logic behind the login form lives here:

* Enables login button only when email and password are valid
* Tracks login attempts and disables button after 5 tries
* Forgot password link shows a popup message
* Live time updates every second
* Password field can toggle visibility

### login.fxml

The front-end layout for the login screen.
Includes:

* Text fields for email and password
* A checkbox to show password
* A 'Forgot Password?' link
* A label to display the current time

### dashboard.fxml

This is the screen that opens after a successful login. Right now it just displays a welcome message but can be expanded later.

---

## 💡 Why This Project Is Useful

This small project is a great foundation for any beginner looking to understand JavaFX with FXML.
It teaches the basics:

* How to connect FXML with a controller
* How to validate user input
* How to navigate between screens
* How to use simple JavaFX features like threads and popups

You can easily add database support later or even style it using CSS.

---

## 🔚 Final Tip

If something doesn’t work, double-check:

* The Java version (must be 17)
* The JavaFX library path
* The VM options are correctly set

Once everything is in place, this project will run smoothly and give you a solid start with JavaFX. Enjoy building and improving it further!
