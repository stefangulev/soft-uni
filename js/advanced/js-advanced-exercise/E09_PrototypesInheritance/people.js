function solve() {
    class Employee {
        constructor(name, age) {
            this.name = name;
            this.age = age;
            this._salary = 0;
            this.tasks = [];
        }
        
        get salary() {
            return this._salary
        }
        set salary(salary) {
            this._salary = salary;
        }
        work() {
            let currentAction = this.tasks.shift();
            console.log(currentAction)
            this.tasks.push(currentAction);
        }
        collectSalary() {
            console.log(`${this.name} received ${this.salary} this month.`);
        }
    };

    class Junior extends Employee {
        constructor(name,age) {
            if (new.target === Employee) {
                throw new Error("Cannot instantiate directly.");
            }
            super(name,age);
            this.tasks.push(`${this.name} is working on a simple task.`);
        } 
    }
    class Senior extends Employee {
        constructor(name,age) {
            if (new.target === Employee) {
                throw new Error("Cannot instantiate directly.");
            }
            super(name,age);
            this.tasks.push(`${this.name} is working on a complicated task.`);
            this.tasks.push(`${this.name} is taking time off work.`);
            this.tasks.push(`${this.name} is supervising junior workers.`);
        }
    }
    class Manager extends Employee {
        constructor(name, age) {
            if (new.target === Employee) {
                throw new Error("Cannot instantiate directly.");
            }
            super(name, age);
            this._dividend = 0;
            this.tasks.push(`${this.name} scheduled a meeting.`);
            this.tasks.push(`${this.name} is preparing a quarterly report.`);
        }
        get dividend() {
            return this._dividend;
        }
        set dividend(dividend) {
            this._dividend = dividend; 
        }
        collectSalary() {
            console.log(`${this.name} received ${this.salary + this.dividend} this month.`);
        }


    }

    return {Employee,Junior, Senior, Manager};
}