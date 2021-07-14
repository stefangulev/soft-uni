function personAndTeacher() {
class Person {
    constructor(name, email) {
        this.name = name;
        this.email = email;
    }
}

class Teacher extends Person {
    constructor(name, email ,subject) {
        super(name,email)
        this.subject = subject;
    }
}

const myTeacher = new Teacher("Ivan", "Ivanov", "sport");
console.log(myTeacher);
console.log(myTeacher.name);

return {
    Person,
    Teacher
}
}
personAndTeacher();