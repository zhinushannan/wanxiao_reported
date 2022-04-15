import datetime

now = datetime.datetime.today()

print(f"{str(now.month).zfill(2)}{str(now.day).zfill(2)}")
