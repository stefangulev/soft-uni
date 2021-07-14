class List {
    constructor() {
        this.list = [];
        this.size = 0;
    }
    add(el) {
        this.list.push(el);
        this.size +=1;
        this.list.sort((l,r) => l - r);
    }
    remove(index) {
        if (this.size <= index || index < 0) {
            throw new Error;
        }
        this.list.splice(index, 1);
        this.size -=1;
        this.list.sort((l,r) => l - r);
    }
    get(index) {
        if (this.size <= index || index < 0) {
            throw new Error;
        }
        return this.list[index];
    }
}
let list = new List();
list.add(5);
list.add(6);
list.add(7);
console.log(list.get(1));
list.remove(1);
console.log(list.get(1));

