### DICT ###

my_dict = dict()
my_other_dic = {}

print(type(my_dict))
print(type(my_other_dic))

my_dict ={
    "Nombre": "Manuel",
    "Apellido": "Roman",
    "Edad": 35,
    1: "Python"
}

print(my_dict)

my_other_dic = {
    "Nombre": "Manuel",
    "Apellido": "Roman",
    "Edad": 35,
    "Lenguajes": {"Python", "Swithf", "Kotlin"},
    1: 1.9
}

print(my_other_dic)

print(len(my_dict))
print(len(my_other_dic))

print(my_dict["Nombre"])

my_dict["Nombre"] = "Lupe"

print(my_dict["Nombre"])

print(my_dict[1])

# Añadir un nuevos elemento
my_dict["Calle"] = "Callesita"
print(my_dict)

# Eliminar un elemento
del my_dict["Calle"]
print(my_dict)

print("Manuel" in my_other_dic)
print("Manuela" in my_other_dic)
print("Nombre" in my_other_dic)
print("Manuel" == my_other_dic["Nombre"])

print(my_other_dic.items())
print(my_other_dic.keys())
print(my_other_dic.values())

my_list = ["Nombre", 1, "Piso"]

my_new_dict = dict.fromkeys(my_list)
print(my_new_dict)

my_new_dict = dict.fromkeys(my_dict, "Texto Prueba")
print(my_new_dict)