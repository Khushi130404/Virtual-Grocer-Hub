# Virtual Grocer Hub

Virtual Grocer Hub is an Android application developed in Java using Android Studio. The project integrates Firebase for real-time data storage and uses the user's local storage for certain features. The application consists of two primary parts: Admin and User.

## 🔧 Project Setup

- Language: Java

- IDE: Android Studio

- Backend: Firebase Realtime Database

- Local Storage: User device storage

## 📋 Project Structure

The application is divided into two main modules:
### 1. Admin Module
### 2. User Module

## 🛠️ Admin Module

The Admin module allows the administrator to manage the grocery inventory efficiently. The key features include :

1. View a list of groceries with details such as :
  - Name
  - Image
  - Quantity
  - Price
  - Unit
    
2. Add new grocery items to the list.

3. Update existing grocery item details.

4. Delete grocery items from the list.

5. Change Admin Credentials :
  - The Admin can change the username and password.
  - For added security, a two-step verification process is implemented using SMS-based OTP (One-Time Password).


## 👤 User Module

The User module allows shoppers to browse, purchase, and manage grocery items easily. The key features include:

1. Browse and Purchase Groceries :
  - Users can select any quantity of grocery items and add them to their cart.

2. Bill Display :
  - A dynamic bill is displayed based on the selected items.
  - Users can remove items from the bill before finalizing the purchase.

3. Address Management :
  - The app retrieves the user's current location using GPS.
  - Users can view their address in satellite view.
  - Users can manually enter their address if preferred.

4. Payment Options :
  - Multiple payment methods are supported, including:
  - Google Pay (GPay)
  - Net Banking
  - Cash on Delivery (COD)
  - PhonePe
  - Credit/Debit Cards

## 🚀 How to Run the Project

1. Clone the repository to your local machine.

2. Open the project in Android Studio.

3. Connect your Firebase account to the project.

4. Build and run the application on an Android emulator or a physical device.



