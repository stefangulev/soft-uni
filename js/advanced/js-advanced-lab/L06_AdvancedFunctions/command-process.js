function solution() {
    let str = "";
    return {
        append: (input) => {
            str += input;
        },
        removeStart: (num) => {
            str = str.substring(num);
        },
        removeEnd: (num) => {
            str = str.slice(0,-num);
        },
        print: () => console.log(str),
    }
}
let firstZeroTest = solution();
firstZeroTest.append('hello');
firstZeroTest.append('again');
firstZeroTest.removeStart(3);
firstZeroTest.removeEnd(4);
firstZeroTest.print();
