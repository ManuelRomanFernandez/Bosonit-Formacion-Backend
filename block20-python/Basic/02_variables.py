# Variables

my_string_variable = "My String Variable"
print(my_string_variable)

my_int_variable = 5
print(my_int_variable)

my_int_to_str_variable = str(my_int_variable)

my_bool_variable = False
print(my_bool_variable)

# Concatenación de variables en un print
print(type(print(my_string_variable, my_int_to_str_variable, my_bool_variable)))
print("Este es el valor de:", my_bool_variable)

# Funciones del sistema
print(len(my_string_variable))

# Variables en una sola linea
name, surname, alias, age = "Manuel", "Roman", "Ramon", 27
print("Me llamo:", name, surname, ". Y mi edad es:", age, ". Y mi alias es", alias)

# Inputs
first_name = input("What is your name: ")
age = input('What is your age: ')

print(first_name)
print(age)

# Cambiando tipos

first_name = 123
age = "Lupe"

print(first_name)
print(age)

# Indicamos el tipo (solo informativo)
address: str = "Mi direccion"
address = 32
print(address)