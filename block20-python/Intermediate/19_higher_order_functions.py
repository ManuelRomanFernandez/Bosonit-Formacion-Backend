### FUNCIONES DE ALTO NIVEL ###

from functools import reduce

def sum_one(value):
    return value + 1


def sum_five(value):
    return value + 5


def sum_two_values_and_add_value(first_value, second_value, f):
    return f(first_value + second_value)


print(sum_two_values_and_add_value(5, 2, sum_one))
print(sum_two_values_and_add_value(5, 2, sum_five))


### CLOSURES ###


def sum_ten():
    def add(value):
        return value + 10

    return add


add_closure = sum_ten()
print(add_closure(5))
print(sum_ten()(6))

### BUILT-IN FUNCIONES DE ALTO NIVEL ###

numbers = [2, 5, 10, 21, 3, 30]


# Map

def multiply_two(number):
    return number * 2


print(list(map(multiply_two, numbers)))
print(list(map(lambda number: number * 2, numbers)))


# Filter

def even_numbers(number):
    return number % 2 == 0

def filter_greater_than_ten(number):
    return number > 10

print(list(filter(even_numbers, numbers)))
print(list(filter(lambda number: number % 2 == 0, numbers)))


print(list(filter(filter_greater_than_ten, numbers)))
print(list(filter(lambda number: number > 10, numbers)))


# Reduce

def reduce_sum_two_values(val1, val2):
    return val1 + val2

print(reduce(reduce_sum_two_values, numbers))
print(reduce(lambda num1, num2: num1 + num2, numbers))