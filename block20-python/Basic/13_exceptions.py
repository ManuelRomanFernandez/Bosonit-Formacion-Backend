### EXCEPCIONES ###

try:
    print(5 + "5")
    # print(5 + 5)
except TypeError as error:
    print("<<< TypeError >>>: " + str(error))
except ValueError:
    print("<<< ValueError >>>")
else:
    print("Todo bien")
finally:
    print("Sacabo")
