class Hex {
    constructor(v) {
        this._value = v;
    }

    valueOf() {
        return this._value;
    }
    toString() {
        return '0x' + this._value.toString(16).toUpperCase();
    }
    plus(input) {
        let val = typeof input === "object" ? input.valueOf() : Number(input);
        return new Hex(this.valueOf() + val);
    }
    minus(input) {
        let val = typeof input === "object" ? input.valueOf() : Number(input);
        return new Hex(this.valueOf() - val);
    }
    parse(str) {
        return parseInt(str, 16);
    }
    

}
let FF = new Hex(255);
console.log(FF.toString());
FF.valueOf() + 1 == 256;
let a = new Hex(10);
let b = new Hex(5);
console.log(a.plus(b).toString());
console.log(a.plus(b).toString()==='0xF');



