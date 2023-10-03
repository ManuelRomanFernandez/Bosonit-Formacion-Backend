### MANEJO DE FICHEROS ###

import os

# .txt file
print("### .txt ###")


txt_file = open("./my_file.txt", "w+")

txt_file.write("Mi nombre es Manuel\nMi apellido es Roman\n27 anos\nPython")
txt_file.write("\nMe gusta Kotlin")

txt_file.seek(0, 0)
print(txt_file.read())
print("-----")


txt_file.seek(0, 0)
for line in txt_file.readlines():
    print(line)

txt_file.close()

with open("./my_file.txt", "a") as my_other_file:
    my_other_file.write("\nY Swift")


# os.remove("./my_file.txt")


# .json file
print("### .json ###")

import json


json_file = open("./my_file.json", "w+")


json_test = {
    "name": "Manuel",
    "surname": "Roman",
    "age": 27,
    "language": ["Python", "Kotlin", "Swift"]
}

json.dump(json_test, json_file, indent=2)

json_file.close()

json_file = open("./my_file.json", "r+")

print(json_file.read())
json_file.seek(0, 0)
print("---")


for line in json_file.readlines():
    print(line)

json_file.close()

json_dic = json.load(open("./my_file.json"))

print(type(json_dic))
print(json_dic["name"])


# .csv file
print("### .csv ###")

import csv


csv_file = open("./my_file.csv", "w", newline="")

csv_writer = csv.writer(csv_file, delimiter=";")

csv_writer.writerow(['name', 'area', 'country_code2', 'country_code3'])
csv_writer.writerow(['Afghanistan', 652090, 'AF', 'AFG'])

csv_file.close()

with open("./my_file.csv", "r+",) as csv_fichero:
    csv_reader = csv.reader(csv_fichero, delimiter=';')
    for row in csv_reader:
        print(row)

print("---")

with open("./my_file.csv", "r+",) as csv_fichero:
    for line in csv_fichero.readlines():
        print(line)



# .xls file
# import xlrd # Debe instalarse el modulo


# .xml file
import xml