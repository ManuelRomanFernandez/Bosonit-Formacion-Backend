### Error Types ###

# SyntaxError
# print "Hola mundo"
print("Hola mundo")


# NameError
# print(name)
name = "name"
print(name)

# IndexError
my_list = ["Python", "Swift", "Kotlin", "Dart", "JavaScript"]
# print(my_list[10])
print(my_list[4])

# ModuleNotFoundError
# import maths
import math

# AttributeError
# print(math.PI)
print(math.pi)


# KeyError
my_dict = {"Nombre": "Manuel", "Apellido": "Roman", "Edad": 27, 1: "Python"}
# print(my_dict["Error"])
print(my_dict["Edad"])


# TypeError
# print(my_list["Cosa"])
print(my_list[0])
print(my_list[False])


# ImportError
# from math import PI
from math import pi
print(pi)


# ValueError
# my_int = int("10 años")
my_int = int("10")
print(my_int)


# ZeroDivisionError
# print(4/0)
print(4/2)


