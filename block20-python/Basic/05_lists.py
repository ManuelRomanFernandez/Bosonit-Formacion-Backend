### Lists ###

my_list = list()
my_other_list = []

print(len(my_list))

my_list = [35, 24, 62, 52, 30, 30, 17]

print(my_list)
print(len(my_list))

my_other_list = [27, 1.90, "Manuel", "Roman"]

print(type(my_other_list))
print(type(my_list))

print(my_other_list[0])
print(my_other_list[1])
print(my_other_list[-1])
print(my_other_list[-2])
# print(my_other_list[4]) IndexError
# print(my_other_list[-5]) IndexError

# count() retorna numero de ocurrencias
print(my_list.count(30))
print(my_other_list.count("Manuel"))

age, height, name, surname = my_other_list
print(name)

name, height, age, surname = my_other_list[2], my_other_list[1], my_other_list[0], my_other_list[3]
print(age)

# Concatenar listas
print(my_list + my_other_list)

# Las listas son mutables
'''
my_list = "Hola Python"
print(my_list)
print(type(my_list))
'''

# Operaciones con listas
my_other_list.append("Rouman")
print(my_other_list)

my_other_list.insert(1, "Black")
print(my_other_list)

my_other_list.remove("Black")
print(my_other_list)

my_pop_element = my_other_list.pop()
print(my_pop_element)
print(my_other_list)

del my_other_list[0]
print(my_other_list)

my_new_list = my_other_list.copy()

my_other_list.clear()
print(my_other_list)
print(my_new_list)

my_new_list.reverse()
print(my_new_list)

print(my_list)
my_list.sort()
print(my_list)