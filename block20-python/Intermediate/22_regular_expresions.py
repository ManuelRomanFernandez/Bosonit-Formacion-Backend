### EXPRESIONES REGULARES ###

import re

my_string = "Esta es la lección número 7: Lección llamada - Expresiones Regulares"
my_other_string = "Esta no es la lección número 6: Manejo de Ficheros"

match = re.match("Esta es la lección", my_string, re.I)
print(match)
start, end = match.span()
print(start, end)
print(my_string[start:end])

# match2 = re.match("Esta es la lección", my_other_string, re.I)
match2 = re.match("Esta no es la lección", my_other_string, re.I)
if match2 is not None:
    print(match2)
    start2, end2 = match2.span()
    print(my_other_string[start2:end2])

print(re.match("Expresiones Regulares", my_string))

print("-------")
# search

search = re.search("lección", my_string, re.I)
print(search)
start, end = search.span()
print(my_string[start:end])

print("-------")
# findall

findall = re.findall("lección", my_string)
print(findall)

print("-------")
# split

print(re.split(":", my_string))

print("-------")
# sub

print(re.sub("Expresiones", "expresiones", my_string))
print(re.sub("lección", "LECCIÓN", my_string))
print(re.sub("lección|Lección", "LECCIÓN", my_string))
print(re.sub("[l|L]ección", "LECCIÓN", my_string))
print(re.sub("Expresiones Regulares", "RegEx", my_string))

print("----------------")
print("----Patterns----")
print("----------------")
# Patterns


pattern = r'[l|L]ección'
print(re.findall(pattern, my_string))

pattern = r'[lL]ección|Expresiones'
print(re.findall(pattern, my_string))
print(re.search(pattern, my_string))

pattern = r'[0-9]'
print(re.findall(pattern, my_string))
print(re.search(pattern, my_string))

pattern = r'\d'
print(re.findall(pattern, my_string))
print(re.search(pattern, my_string))

pattern = r'\D'
print(re.findall(pattern, my_string))
print(re.search(pattern, my_string))

pattern = r'[l].*'
print(re.findall(pattern, my_string))
print(re.search(pattern, my_string))

email = "mouredev@mouredev.com"
pattern = r'^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$'
print(re.findall(pattern, email))
print(re.match(pattern, email))
print(re.search(pattern, email))


email = "mouredev@9"
pattern = r'^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$'
print(re.findall(pattern, email))
print(re.match(pattern, email))
print(re.search(pattern, email))