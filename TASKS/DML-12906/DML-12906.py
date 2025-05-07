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

# def IsFirstDashboardDisplayed():
#     FirstDashboardTitle = driver.find_elements(By.CSS_SELECTOR, 'h2[data-testid="dashboard-tile-title"]')
#     print(FirstDashboardTitle)
#     if(FirstDashboardTitle[0].get_attribute("innerHTML")):
#         print("User visited the first Global Dashboard - Invoice Create to Invoice Export, successfully displayed")
#     else:
#         print("User has NOT VISITED the first Global Dashboard - Invoice Create to Invoice Export, successfully displayed")
# IsFirstDashboardDisplayed()


#The REQ - SPEND REPORT
# exploreButton = driver.find_element(By.ID, 'ViewReportingAndAnalysis_Explore')
# exploreButton.click()
# time.sleep(5)
# reqSpendButton = driver.find_element(By.ID, 'Req-Spend')
# reqSpendButton.click()
# time.sleep(5)

# valueKPIButton = driver.find_element(By.PARTIAL_LINK_TEXT, 'KPI')
# valueKPIButton.click()
# time.sleep(10)

#The REQ - SPEND REPORT 2

exploreButton = driver.find_element(By.ID, 'ViewReportingAndAnalysis_Explore')
exploreButton.click()
time.sleep(5)
reqSpendButton = driver.find_element(By.ID, 'Req-Spend')
reqSpendButton.click()
time.sleep(5)

valueKPIButton = driver.find_element(By.ID, '076eff94-3b51-47ea-8c71-e47c5eea9ea7-label')
valueKPIButton.click()
time.sleep(10)





# #The PO - SPEND REPORT
# exploreButton = driver.find_element(By.ID, 'ViewReportingAndAnalysis_Explore')
# exploreButton.click()
# time.sleep(5)
# poSpendButton = driver.find_element(By.ID, 'PO-Spend')
# poSpendButton.click()
# time.sleep(5)

# valueKPIButton = driver.find_element(By.ID, '18415966-b7d5-4b00-b949-d813bd69f597-label')
# valueKPIButton.click()
# time.sleep(10)


# #The INVOICE - SPEND REPORT
# globalButton.click()
# time.sleep(5)
# ReqSubmittoReqCompleteButton = driver.find_element(By.ID, 'ReqSubmittoReqComplete')
# ReqSubmittoReqCompleteButton.click()
# time.sleep(10)













