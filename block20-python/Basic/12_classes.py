### CLASES ###

class MyEmptyPerson:
    pass


print(MyEmptyPerson)


class MyPerson:
    def __init__(self, name, surname, alias="Sin alias"):
        self.name = name
        self.surname = surname
        self.alias = alias
        self.__fullname = f"{name} {surname} ({alias})" # Propiedad privada

    def walk(self):
        print(f"{self.name} {self.alias} esta caminando")

    def get_fullname(self):
        return self.__fullname


my_person = MyPerson("Manuel", "Roman")
print(f"{my_person.name} {my_person.surname}")
my_person.walk()


my_other_person = MyPerson("Manuel", "Roman", "Lupe")
print(my_other_person.name)
my_other_person.walk()
my_other_person.name = "Antonio"
print(my_other_person.name)


my_other_person.name = 888
print(my_other_person.name)


print(my_other_person.get_fullname())