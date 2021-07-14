class Company {
    constructor() {
        this.department = [];
    }

    addEmployee(username, salary, position, department) {
        if (!username || !salary || !position || !department || salary <0) {
            throw new Error("Invalid input!");
        }
        let employee = {
            'name': username,
            'salary': Number(salary),
            'position': position,
        }

        if (!this.department[department]) {
            this.department[department] = [];
        }
        this.department[department].push(employee);
        return `New employee is hired. Name: ${username}. Position: ${position}`;
    }
    bestDepartment() {
        let bestDep = "";
        let bestSal = 0;

        Object.entries(this.department).forEach(([key, value]) => {
            let totalSal = 0;
            let workersCount = 0;
            for(let el of value) {
               totalSal += el.salary
               workersCount++;
            }
            let currentAvg = totalSal / workersCount;
        if(bestSal < currentAvg) {
            bestSal = currentAvg;
            bestDep = key;
        }
    });

   return `Best Department is: ${bestDep}\nAverage salary: ${bestSal.toFixed(2)}\n${this.department[bestDep].sort((l,r) => r.salary - l.salary ===0 ? l.name.localeCompare(r.name):r.salary - l.salary)
    .map(o => `${o.name} ${o.salary} ${o.position}`).join('\n')}`;
    
        }
    }
    let c = new Company();
c.addEmployee("Stanimir", 2000, "engineer", "Construction");
c.addEmployee("Pesho", 1500, "electrical engineer", "Construction");
c.addEmployee("Slavi", 500, "dyer", "Construction");
c.addEmployee("Stan", 2000, "architect", "Construction");
c.addEmployee("Stanimir", 1200, "digital marketing manager", "Marketing");
c.addEmployee("Pesho", 1000, "graphical designer", "Marketing");
c.addEmployee("Gosho", 1350, "HR", "Human resources");
console.log(c.bestDepartment());

