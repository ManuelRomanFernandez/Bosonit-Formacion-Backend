### Fechas ###

import datetime

def print_date(date):
    print(date.year)
    print(date.month)
    print(date.day)
    print(date.hour)
    print(date.minute)
    print(date.second)
    print(date.timestamp())

now = datetime.datetime.now()

print_date(now)

year_2024 = datetime.datetime(2024, 1, 1)

print_date(year_2024)

current_time = datetime.time(10, 10, 10)

print(current_time)

current_date = datetime.date.today()

print(current_date)

current_date = datetime.date(current_date.year, current_date.month + 1, current_date.day)

print(current_date)

diff = year_2024 - now
print(diff)

init_timedelta = datetime.timedelta(200, 100, 100, weeks=10)
end_timedelta = datetime.timedelta(300, 100, 100, weeks=13)
print(end_timedelta + init_timedelta)
print(end_timedelta - init_timedelta)
