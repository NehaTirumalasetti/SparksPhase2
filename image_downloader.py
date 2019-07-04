from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
import json
from urllib.request import *

path = 'C:/Users/Neha/PycharmProjects/sparksel/img'
# path for folder in which images are stored
search_text = "dogs"
headers = {}
headers['User-Agent'] = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36"
extensions = {"jpg", "jpeg", "png", "gif"}

def init_driver():

    # initialises driver window in this case firefox

    driver = webdriver.Firefox()
    driver.maximize_window()
    driver.wait = WebDriverWait(driver, 5)
    return driver

def get_img_links(driver,url):

    # gets list of images from the website being scraped

    driver.get(url)
    imges = driver.find_elements_by_xpath('//div[contains(@class,"rg_meta")]')
    print('Total images '+str(len(imges)))
    print()
    return imges

def download_images(imgs):

    # each images urls are taken from image list and downloaded

    img_count = 1
    for img in imgs:
        if img_count > 10:
            break
        else:
            img_url = json.loads(img.get_attribute('innerHTML'))["ou"]
            img_type = json.loads(img.get_attribute('innerHTML'))["ity"]

            print('Downloading image '+str(img_count))
            try:
                if img_type not in extensions:
                    img_type = 'jpg'
                req = Request(img_url, headers= headers)
                raw_img = urlopen(req).read()
                f = open(path+"/"+search_text+str(img_count)+"."+img_type, "wb")
                f.write(raw_img)
                f.close()
                img_count += 1
            except Exception as e:
                print(str(e))
            finally:
                print()


if __name__ == '__main__':

    print("Which images would you like to download?")
    search_text = input()
    url = "https://www.google.co.in/search?q="+search_text+"&source=lnms&tbm=isch"

    driver = init_driver()
    img = get_img_links(driver, url)
    download_images(img)
    driver.close()
