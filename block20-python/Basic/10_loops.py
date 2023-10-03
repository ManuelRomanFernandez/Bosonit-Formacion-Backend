### BUCLES ###

# while

my_condition = 0

while my_condition < 10:
    print(my_condition)
    my_condition += 2
else: # Es opcional
    print("Mi condicion es mayor o igual que 10")

print("La ejecución continua")

while my_condition < 20:
    my_condition += 1
    if my_condition == 15:
        print("Se detiene la ejecucion")
        break
    print("Mi condicion es menor que 20")

# for

my_list = [35, 24, 62, 52, 30, 30, 17]

for element in my_list:
    print(element)

my_tuple = (35, 24, 62, 52, 30, 30, 17)

for element in my_tuple:
    print(element)

my_set = {"Manuel", "Roman", 27}

for element in my_set:
    print(element)

my_dict ={
    "Nombre": "Manuel",
    "Apellido": "Roman",
    "Edad": 35,
    1: "Python"
}

for element in my_dict:
    print(element)
    if element == "Edad":
        break

for element in list(my_dict.values()):
    print(element)
else:
    print("El bucle ha finalizado")