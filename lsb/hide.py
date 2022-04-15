from PIL import Image


def mod(x, y):
    return x % y


def bin_ord(flag):
    string = ""
    with open(flag) as f:
        txt = f.read()
        for i in range(len(txt)):
            string = string + bin(ord(txt[i])).replace('0b', '').zfill(8)
    return string


def hide(pic, flag, new_pic):
    count = 0
    im = Image.open(pic)
    width = im.size[0]
    height = im.size[1]
    string = bin_ord(flag)
    for h in range(height):
        for w in range(width):
            pixel = im.getpixel((w, h))
            x = pixel[0]
            y = pixel[1]
            z = pixel[2]
            if count == len(string):
                break
            x = x - mod(x, 2) + int(string[count])
            im.putpixel((w, h), (x, y, z))
            count = count + 1
            if count == len(string):
                break
            y = y - mod(y, 2) + int(string[count])
            im.putpixel((w, h), (x, y, z))
            count = count + 1
            if count == len(string):
                break
            z = z - mod(z, 2) + int(string[count])
            im.putpixel((w, h), (x, y, z))
            count = count + 1
    im.save(new_pic)


pic = r"./lsb_data/test.png"

flag = r"./lsb_data/flag.txt"

new_pic = r"./lsb_data/new.png"

hide(pic, flag, new_pic)
