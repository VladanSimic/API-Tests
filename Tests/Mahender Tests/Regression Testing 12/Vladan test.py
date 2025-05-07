# import time
#
# from selenium import webdriver
# from selenium.webdriver.chrome.options import Options
# from selenium.webdriver.common.by import By
#
# chrome_options = Options()
# chrome_options.add_argument("--start-maximized")
#
# driver = webdriver.Chrome(options=chrome_options)
#
# driver.get("https://ji-test-app.va.jaggaer.com/apps/Router/Login?OrgName=SQSupport&URL=%2Fapps%2FRouter%2FHome%3Ftmstmp%3D1691073250970&SessionTimeout=true")
#
# sign_in_button = driver.find_element(By.XPATH, "//button[contains(text(), 'SIGN IN')]")
# sign_in_button.click()
# time.sleep(5)





import time
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By

chrome_options = Options()
chrome_options.add_argument("--start-maximized")

driver = webdriver.Chrome(options=chrome_options)

driver.get("https://ji-test-app.va.jaggaer.com/apps/Router/Login?OrgName=SQSupport&URL=%2Fapps%2FRouter%2FHome%3Ftmstmp%3D1691073250970&SessionTimeout=true")

sign_in_button = driver.find_element(By.XPATH, "//button[contains(text(), 'SIGN IN')]")
sign_in_button.click()
time.sleep(5)




#
# password = driver.find_element(By.NAME, "password")
# password.send_keys("secret_sauce")
# submit = driver.find_element(By.ID, "login-button")
# submit.click()
# sauce_labs_backpack = driver.find_element(By.LINK_TEXT, "Sauce Labs Backpack")
# sauce_labs_backpack.click()
# add_to_cart = driver.find_element(By.NAME, "add-to-cart-sauce-labs-backpack")
# add_to_cart.click()
#
# cart_button = driver.find_element(By.XPATH, "//a[@class='shopping_cart_link']")
# cart_button.click()
# checkout = driver.find_element(By.NAME, "checkout")
# checkout.click()
#
# first_name = driver.find_element(By.ID, "first-name")
# first_name.send_keys("Vladan")
# last_name = driver.find_element(By.ID, "last-name")
# last_name.send_keys("Simic")
# zip_code = driver.find_element(By.NAME, "postalCode")
# zip_code.send_keys("11000")
#
# con_button = driver.find_element(By.ID, "continue")
# con_button.click()
#
# finish_button = driver.find_element(By.ID, "finish")
# finish_button.click()
# time.sleep(3)
#
# message_text = driver.find_element(By.XPATH, "//h2[normalize-space()='Thank you for your order!']")
# checked_symbol = driver.find_element(By.CLASS_NAME, "pony_express")
#
#
# def isuserfinishedshopping1():
#     if message_text.is_displayed():
#         print("User has finished his shopping successfully - Assertion 1")
#     else:
#         print("User has not finished his shopping successfully - not succeeded to Assertion 1")
#
#
# def isuserfinishedshopping2():
#     if checked_symbol.is_displayed():
#         print("Additionally, it was confirmed again that user has finished the shopping - Assertion 2")
#     else:
#         print("However, it was not confirmed again that user has finished the shopping - not succeeded to Assertion 2")
#
#
# isuserfinishedshopping1()
# isuserfinishedshopping2()
