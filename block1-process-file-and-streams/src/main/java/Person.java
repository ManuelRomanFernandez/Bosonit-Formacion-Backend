public class Person{
    private String name;
    private String town;
    private int age;

    public Person(String[] input) {
        this.name = input[0];

        this.town = input[1];
        if(input[1].isEmpty()){
            this.town = "unknown";
        }

        if(input[2].isEmpty()){
            this.age = 0;
        } else{
            this.age = Integer.parseInt(input[2]);
        }
    }

    public String getName(){
        return this.name;
    }

    public String getTown(){
        return this.town;
    }

    public int getAge(){
        return this.age;
    }

    @Override
    public String toString(){
        String output = "Name: " + this.name + ". Town: " + this.town + ". Age:  " + this.age;
        if(this.age == 0){
            output = "Name: " + this.name + ". Town: " + this.town + ". Age: unknown";
        }
        return output;
    }
}
