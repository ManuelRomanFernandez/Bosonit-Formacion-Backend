### SETS ###

my_set = set()
my_other_set = {} # Inicialmente es un diccionario

print(type(my_set))
print(type(my_other_set))

my_other_set = {"Manuel", "Roman", 27}
print(type(my_other_set))

print(len(my_other_set))

my_other_set.add("Magala")

print(my_other_set) # No es una estructura ordenada

my_other_set.add("Magala")

print(my_other_set) # No admite repetidos

print("Magala" in my_other_set)
print("Malaga" in my_other_set)

my_other_set.remove("Magala")
print(my_other_set)

my_other_set.clear()
print(my_other_set)
print(len(my_other_set))

my_other_set = {"Manuel", "Roman", 27}
del my_other_set
# print(my_other_set) NameError: name 'my_other_set' is not defined

my_other_set = {"Manuel", "Roman", 27}

# Es arriesgado hacer esta conversión porque el orden es siempre aleatorio
my_set = {"Manuel", "Roman", 27}
my_list = list(my_set)
print(my_list[0])

my_set = {"Kotlin", "Swift", "Python"}

my_new_set = my_set.union(my_other_set)
print(my_new_set.union({"Javascript", "Java"}))

print(my_new_set.difference(my_set))