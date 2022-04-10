remove_list = [('236628', '徐志龙'), ('236628', '陈烨')]

remove_dict = {}
for i in remove_list:
    if i[0] not in list(remove_dict.keys()):
        remove_dict[i[0]] = []
    remove_dict[i[0]].append(i[1])

print(remove_dict)
