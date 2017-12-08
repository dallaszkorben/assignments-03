# Assignments
Mobile test and REST test assignments
## Mobile test
As I have no specification I do not know what is the expected behaviour of the different functions.
I used my common sense which is sometimes probably different what the customer wants.
I took the functions of the buttons reflect on the TEXT on them.
- ADD -> x+1
- SUBTRACT -> x-1
- SQUARE ROOT  -> √(x)
- DIVIDED by 2 ->  x/2
- MULTIPLY by 2  -> x*2
- POWER by 2 -> x^2

where x means the value in the result field.

In my test cases I focused on the simplest functionalities and some edge cases, for example square root on a negative number.
As I was not sure if it is OK to report issues on the https://github.com/MEZISH/wonky-android-calc/issues page, I collect them under the **Issues** section
### Automation

- Testing the Calculator mobile app, I chose **Robot Framework**, so it should be installed.
- The **AppiumLibrary** is installed for Robot Framework
- The **Appium** application should be installed and run before you run the Robot Framework
- The **android-tools-adb** and **android-tools-fastboot** is installed
- The **USB debagging** is enabled on the Android phone
- Some Android specific variable should be set in the Robot Framework code in the **Constants.tsv** file:

    - ${devicename}
    - ${platformversion}
  
For all test cases the ADD and SUBTRACT functions are the prerequisit as the base value will be generated using these functions.
* **Test-01 Positive Square Number Square Root**

  I test here a simple square root function on a square number.
  That means that I'm expecting the result of **√(x)** which is a positive integer.
  
* **Test-02 Positive Even Integer Divide**

  I test here a simple divide function on a positiv even integer.
  That means that I'm expecting the result of **x/2** which is a positive integer.
  
* **Test-03 Positive Integer Myltiply by 2**

  I test here a simple multiply function on a positive Integer.
  That means that I'm expecting the result of **x*2** which is a positive even integer.
  
* **Test-04 Positive Integer Power by 2**

  I test here a simple Power by 2 function on an integer.
  That means that I'm expecting the result of **x^2** which is a positive integer.
  
  _(This test case fails because it calculates n(n+1) instead of n*n)_
  
* **Test-05 Negative Square Number Square Root**

  I test here the square root function on a negative number.
  I'm expecting some error message which says that it is not possible to calculate this value.
  In this case I'm expecting the "NaN" result as a message (as I do not have specification, it could be anything else)
  
  _(This test case fails because the code does not handle the negative numbers, and the app closes)_

### Manual tests
   Here are two extra manual test cases:
   
* **Chain Divide Operations**
  1. **Start** the application
  2. Set the **value** to **1** by clicking on the ADD button
  3. **Check** if get "**1**" in the result
  4. **Click** on the **DEVIDE by 2** button **two** times
  5. **Check** if you get "**0.25**" in the result
  6. **Click** on the **MULTIPLY by 2** button **two** times
  7.  Check the result
  
  **Expected result:** should get "**1**"


* **Test Handling Big Numbers**
  1. **Start** the application
  2. Set the **value** to **10** by clicking on the **ADD** button **ten times**
  3. Click on the **POWER by 2** button **four times**
  
   **Expected result:** should get en error message saying that the expecting value is too big.

  
### Issues
* **Issue - 1**
  1. **Start** the application
  2. provide a **negativ number** clicking on the **SUBSTRACT** button **some times**
  3. Click on the **SQUARE ROOT** button
  
   **Expected result:** should get en error message saying that the operation is not valid on a negativ number
   **Actual result:** the app **closes**
   **Note:** The code should handle the  exceptions
   
* **Issue - 2**
  1. **Start** the application
  2. Set the **value** to **3** by clicking on the **ADD** button **3 times**
  3. Click on the **MULTIPLY by 2** button
  4. Check the value
   
   **Expected result:** **9**
   **Actual result:** **12**
   **Note:** Wrong implementation. It calculates n*(n+1) instead of n*n

* **Issue - 3**
  1. **Start** the application
  2. Set the **value** to **1** by clicking on the ADD button
  3. **Check** if get "**1**" in the result
  4. **Click** on the **DEVIDE by 2** button **two** times
  5. **Check** if you get "**0.25**" in the result
  6. **Click** on the **MULTIPLY by 2** button **two** times
  7.  Check the result
  
  **Expected result:** should get "**1**"
   **Actual result:** **1.00**
   **Note:** The program should handle the decmal values in consistent manner

* **Issue - 4**
  1. **Start** the application
  2. Set the **value** to **10** by clicking on the **ADD** button **ten times**
  3. Click on the **POWER by 2** button **four times**
  
   **Expected result:** should get en error message saying that the expecting value is too big.
   **Actual result:** The program feeezes.
       
    
  


## REST test
