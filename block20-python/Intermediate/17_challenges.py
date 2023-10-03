### RETOS ###

'''
Escribe un programa que muestre por consola (con un print) los
números de 1 a 100 (ambos incluidos y con un salto de línea entre
cada impresión), sustituyendo los siguientes:
- Múltiplos de 3 por la palabra "fizz".
- Múltiplos de 5 por la palabra "buzz".
- Múltiplos de 3 y de 5 a la vez por la palabra "fizzbuzz".
'''


def fizzbuzz():
    for i in range(1, 101):
        if i % 3 == 0 and i % 5 == 0:
            print("fizzbuzz")
        elif i % 5 == 0:
            print("buzz")
        elif i % 3 == 0:
            print("fizz")
        else:
            print(i)


# fizzbuzz()

'''
Escribe una función que reciba dos palabras (String) y retorne
verdadero o falso (Bool) según sean o no anagramas.
- Un Anagrama consiste en formar una palabra reordenando TODAS
  las letras de otra palabra inicial.
- NO hace falta comprobar que ambas palabras existan.
- Dos palabras exactamente iguales no son anagrama.
'''

word1 = "tgbhy"
word2 = "bghty"


def anagram(word1, word2):
    return word1.lower() != word2.lower() and len(set(word1)) == len(set(word1).union(set(word2)))


# print(anagram(word1, word2))


'''
Escribe un programa que imprima los 50 primeros números de la sucesión
de Fibonacci empezando en 0.
- La serie Fibonacci se compone por una sucesión de números en
  la que el siguiente siempre es la suma de los dos anteriores.
  0, 1, 1, 2, 3, 5, 8, 13...
'''

def fibonacci():

    prev = 0
    current = 1

    for i in range(50):
        print(prev)

        final = prev + current
        prev = current
        current = final


# fibonacci()


'''
Escribe un programa que se encargue de comprobar si un número es o no primo.
Hecho esto, imprime los números primos entre 1 y 100.
'''


def is_prime(num):
    for i in range(num):
        if i != 0 and i != 1 and i != num and num % i == 0:
            return False
    return True


def print_primo():
    for i in range(1, 101):
        if is_prime(i):
            print(f"{i} es primo")
        else:
            print(f"{i} no es primo")


# print_primo()


'''
Crea un programa que invierta el orden de una cadena de texto
sin usar funciones propias del lenguaje que lo hagan de forma automática.
- Si le pasamos "Hola mundo" nos retornaría "odnum aloH"
'''


def manual_string_reverse(text):
    reverse_text = ""
    text_list = list(text)

    for i in range(len(text)):
        reverse_text += text_list[len(text) - i - 1]

    print(reverse_text)


manual_string_reverse("Hola mundo")