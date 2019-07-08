from selenium import webdriver
import time
import requests


def __init__driver(url):
    driver = webdriver.Firefox()
    driver.maximize_window()
    time.sleep(5)
    driver.get(url)
    return driver


def get_links(driver):

    time.sleep(5)
    links = driver.find_elements_by_tag_name('a')
    print("Total number of links: "+str(len(links)))
    for link in links:
        r = requests.head(link.get_attribute('href'))
        if r.status_code < 400:
            print("Link "+str(link.get_attribute('href'))+" is okay\nStatus code = "+str(r.status_code))
        else:
            print("Link "+str(link.get_attribute('href'))+" is broken\nStatus code = "+str(r.status_code))
    driver.close()
    return


def main():
    url = "http://www.newtours.demoaut.com/"
    driver = __init__driver(url)
    get_links(driver)
    return


if __name__ == '__main__':
    main()
