# This is a sample Python script.

from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By

# Definisanje opcija
chrome_options = Options()
chrome_options.add_argument("--start-maximized")

# Instanciranje WebDriver objekta sa opcijama
driver = webdriver.Chrome(executable_path="Drivers/chromedriver.exe", options=chrome_options)

# Otvaranje stranice
driver.get("link ka sajtu")

# Pronalaženje i klik na dugme
submit = driver.find_element(By.CSS_SELECTOR, "button[type='submit']")
submit.click()
























