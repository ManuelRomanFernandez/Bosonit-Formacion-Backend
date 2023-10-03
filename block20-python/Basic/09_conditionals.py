### CONDICIONALES ###

my_condition = True

if my_condition:
    print("Se ejecuta la condicion")

my_condition = 1

if my_condition == 20:
    print("Se ejecuta la segunda condicion")
else:
    print("Se ejecuta el else de la segunda")

if my_condition >= 10 and my_condition <= 20:
    print("Se ejecuta la tercera condicion")
elif my_condition == 1:
    print("Es igual a 1")
else:
    print("Se ejecuta el else de la tercera")

print("La ejecucion continua")

my_string = "Mi cadena de texto"

if my_string:
    print("Mi cadena de texto no es vacia")

if not my_string == "Mi cadena de textoooo":
    print("Estas cadenas de texto coinciden")