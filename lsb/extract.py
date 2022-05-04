from PIL import Image


def mod(x, y):
    return x % y


def extract(pic, lenth, hide):
    binary = ""
    string = ""
    count = 0
    im = Image.open(pic)
    width = im.size[0]
    height = im.size[1]
    for h in range(height):
        for w in range(width):
            pixel = im.getpixel((w, h))
            x = pixel[0]
            y = pixel[1]
            z = pixel[2]
            if count == lenth:
                break
            binary = binary + str(mod(x, 2))
            count = count + 1
            if count == lenth:
                break
            binary = binary + str(mod(y, 2))
            count = count + 1
            if count == lenth:
                break
            binary = binary + str(mod(z, 2))
            count = count + 1
    with open(hide, "w", encoding="UTF-8") as f:
        for i in range(0, len(binary), 8):
            string = string + chr(int(binary[i:i + 8], 2))
        f.write(string)


pic = r"/home/zhinushannan/CODE/wanxiao_reported/lsb_data/new.png"

lenth = 200

hide = r"/home/zhinushannan/CODE/wanxiao_reported/lsb_data/hide.txt"

extract(pic, lenth, hide)
