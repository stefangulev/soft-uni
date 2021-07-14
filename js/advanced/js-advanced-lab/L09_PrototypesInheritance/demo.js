function personAndTeacher() {
    function Person(name, email) {
        this.name = name;
        this.email = email;
    }
    Person.prototype.getData = function() {
        return `I return ${this.name} ${this.email}`
    }



    function Teacher(name, email,subject) {
       //inheritance of properties
        Person.call(this, name, email);
        this.subject = subject;
    }
    Teacher.prototype = Object.create(Person.prototype);
    Object.defineProperty(Teacher.prototype, 'constructor', {
        value: Teacher,
        enumerable: false, // so that it does not appear in 'for in' loop
        writable: true });
    
let myPerson = new Person("Gosho", "Ginchev");
console.log(myPerson.getData());
    let myTeacher = new Teacher("Ivan", "@gmail", "Maths");
    console.log(myTeacher.getData());
    console.log(Teacher.prototype.constructor);



return {
    Person,
    Teacher
}
    
}
personAndTeacher();