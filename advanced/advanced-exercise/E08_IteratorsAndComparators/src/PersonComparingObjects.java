public class PersonComparingObjects implements Comparable<PersonComparingObjects> {
    String name;
    int age;
    String town;

    public PersonComparingObjects(String name, int age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return this.age;
    }

    public String getTown() {
        return this.town;
    }

    @Override
    public int compareTo(PersonComparingObjects other) {
        if (this.getName().compareTo(other.getName()) != 0) {
            return this.getName().compareTo(other.getName());
        } else {
            if (this.getAge() != other.getAge()) {
                return Integer.compare(this.getAge(), other.getAge());
            } else {
                if (this.getTown().compareTo(other.getTown()) != 0) {
                    return this.getTown().compareTo(other.getTown());
                } else {
                    return 0;
                }
            }
        }
    }
}
