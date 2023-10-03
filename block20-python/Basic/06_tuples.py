### TUPLES ###

my_tuple = tuple()
my_other_tuple = (35, 24, 62, 52, 30, 30, 17)

my_tuple = (27, 1.90, "Manuel", "Roman")
print(my_tuple)
print(type(my_tuple))

print(my_tuple[0])
print(my_tuple[-1])

print((my_tuple.count(27)))
print(my_tuple.index("Roman"))

# Los valores de las tuplas son inmutables
# my_tuple[1] = 2.00

my_sum_tuple = my_tuple + my_other_tuple
print(my_sum_tuple)

print(my_sum_tuple[2:6])

my_list = list(my_tuple)
print(type(my_list))

my_list[3] = "Rouman"
my_list.insert(1, "Black")
print(tuple(my_list))

# No se puede
del my_tuple
# print(my_tuple) NameError: name "my_tuple" is not defined