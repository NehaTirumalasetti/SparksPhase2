from selenium import webdriver
import time

def init_driver():
    driver = webdriver.Firefox()
    driver.maximize_window()
    return driver

def login(driver):

    email = driver.find_element_by_xpath('//*[@id="identifierId"]')
    email.send_keys('testeryellow13@gmail.com')
    button = driver.find_element_by_xpath('/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div/span')
    button.click()
    time.sleep(20)
    passwd = driver.find_element_by_xpath("//input[@type='password']")
    passwd.send_keys('lookatthestars')
    button = driver.find_element_by_xpath('/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div/span')
    button.click()
    time.sleep(20)
    return

def compose(driver, ema, sub, body):


    comp = driver.find_element_by_xpath('/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[1]/div/div')
    comp.click()
    time.sleep(5)

    to = driver.find_element_by_xpath('//*[@id=":a3"]')
    to.send_keys(ema)
    time.sleep(5)

    su = driver.find_element_by_xpath('//*[@id=":9l"]')
    su.send_keys(sub)
    time.sleep(5)

    bo = driver.find_element_by_xpath('//*[@id=":aq"]')
    bo.send_keys(body)
    time.sleep(5)

    se = driver.find_element_by_xpath('//*[@id=":9b"]')
    se.click()

    return


if __name__ == '__main__':


    #ema = input("Enter recipients full email id")
    #sub = input("Enter subject of mail")
    #body = input("Enter body of mail")
    ema = 'ntirumalasetti@gmail.com'
    sub = 'Test Mail'
    body = 'Look at the stars\nLook how they shine for you\nAnd all the things that you do'
    driver = init_driver()
    driver.get('https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin')
    login(driver)
    compose(driver, ema, sub, body)
