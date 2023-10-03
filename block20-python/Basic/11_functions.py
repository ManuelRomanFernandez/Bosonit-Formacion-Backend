### FUNCIONES ###

def my_function():
    print("Esto es una funcion")


my_function()


def sum_two_values(val1, val2):
    print(val1 + val2)


sum_two_values(2, 3)
sum_two_values("4", "1")
sum_two_values(1.4, 2.5)


def sum_two_values_with_return(val1, val2):
    return val1 + val2


my_result = sum_two_values_with_return(5, 6)
print(my_result)

my_result = sum_two_values(10, 5)
print(my_result)


def print_name(name, surname):
    print(f"{name} {surname}")


print_name(surname="Roman", name="Manuel")


def print_name_with_default (name, surname, alias="Sin alias"):
    print(f"{name} {surname} {alias}")


print_name_with_default("Manuel", "Roman", "Lupe")
print_name_with_default("Manuel", "Roman")


def print_texts(*texts):
    for text in texts:
        print(text)


print_texts("Hola", "Python", "Roman")


def print_upper_texts(*texts):
    print(type(texts))
    for text in texts:
        print(text.upper())


print_upper_texts("Hola", "Python", "Roman")