import time
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

globalButton = driver.find_element(By.ID, 'ViewReportingAndAnalysis_Global')
globalButton.click()
time.sleep(5)

InvoiceCreatetoInvoiceExportPayButton = driver.find_element(By.ID, 'InvoiceCreatetoInvoiceExport')
InvoiceCreatetoInvoiceExportPayButton.click()
time.sleep(5)

def IsDashboardDisplayed1():
    DashboardTitle = driver.find_elements(By.ID, 'lk-react-container')
    print("Invoice Create to Invoice Export Dashboard has been displayed properly")
    
IsDashboardDisplayed1()


globalButton = driver.find_element(By.ID, 'ViewReportingAndAnalysis_Global')
globalButton.click()
time.sleep(5)

InvoiceSubmittoInvoiceExportPayButton = driver.find_element(By.ID, 'InvoiceSubmittoInvoiceExport')
InvoiceSubmittoInvoiceExportPayButton.click()
time.sleep(5)

def IsDashboardDisplayed2():
    DashboardTitle = driver.find_elements(By.ID, 'lk-react-container')
    print("Invoice Submit to Invoice Export Dashboard has been displayed properly")
    
IsDashboardDisplayed2()

globalButton = driver.find_element(By.ID, 'ViewReportingAndAnalysis_Global')
globalButton.click()
time.sleep(5)

ReqSubmitButton = driver.find_element(By.ID, 'ReqSubmittoReqComplete')
ReqSubmitButton.click()
time.sleep(5)

def IsDashboardDisplayed3():
    DashboardTitle = driver.find_elements(By.ID, 'lk-react-container')
    print("Req Submit to Req Complete Dashboard has been displayed properly")
    
IsDashboardDisplayed3()














