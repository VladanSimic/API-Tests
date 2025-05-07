import time
from xml.dom.minidom import Document
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.keys import Keys
import requests


chrome_options = Options()
chrome_options.add_argument("--start-maximized")

chrome_driver_path = "C:/Users/vlasimic/Downloads/chromedriver.exe"
driver = webdriver.Chrome(options=chrome_options)

driver.get("https://ji-test-app.va.jaggaer.com/apps/Router/Login?OrgName=SQSupport")
actions = ActionChains(driver)

username = driver.find_element(By.CSS_SELECTOR, "input[id='Username']")
username.send_keys("vsimic")

password = driver.find_element(By.CSS_SELECTOR, "input[id='Password']")
password.send_keys("Welcome2Jaggaer5!")

sign_in_button = driver.find_element(By.XPATH, "//button[contains(text(), 'SIGN IN')]")
sign_in_button.click()

SwitchOrg = driver.find_element(By.XPATH, "//a[contains(text(), 'Switch Between Orgs')]")
SwitchOrg.click()

SwitchOrg2 = driver.find_element(By.ID, "SwitchOrg_CustomersAutocomplete_switchOrg_SearchInput_Input")
SwitchOrg2.send_keys("20008205")
time.sleep(3)

SwitchOrg2.send_keys(Keys.ENTER)
time.sleep(6)

reportingIcon = driver.find_element(By.CSS_SELECTOR, 'a[aria-label="Reporting"]')
actions.move_to_element(reportingIcon).perform()
time.sleep(5)


lookerOption = driver.find_element(By.CSS_SELECTOR, 'a[aria-label="Business Analytics Dashboards"]')
lookerOption.click()

time.sleep(5)

exploreButton = driver.find_element(By.ID, 'ViewReportingAndAnalysis_Explore')
exploreButton.click()
time.sleep(5)

ContractButton = driver.find_element(By.ID, 'Contract')
ContractButton.click()
time.sleep(5)

FindButton = Document.querySelectorAll("#listbox-input-exploreSearch")[0]

#driver.find_element(By.ID, 'exploreSearch')
time.sleep(5)
FindButton.click()
time.sleep(5)
FindButton.send_keys('status')
time.sleep(5)



# FindButton = driver.find_element(By.ID, 'listbox-input-exploreSearch')
# FindButton.click()
# FindButton.send_keys('status')

time.sleep(5)





# def IsDashboardDisplayed():
#     DashboardTitle = driver.find_elements(By.ID, 'lk-react-container')
#     print("Dashboard has been displayed properly")
    
# IsDashboardDisplayed()


# //a[@id='Contract']
















