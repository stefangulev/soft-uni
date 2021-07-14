function solve(input) {
    function processor(value) {
        const result = [];
        return {
            add: (value) => result.push(value),
            remove: (value) => {
                while (result.includes(value)) {
                    let index = result.indexOf(value);
                    result.splice(index, 1);
                }
            },
            print: () => console.log(result.join(','))
        }
    }
 
    const listProcessor = processor();
    input.map(x => x.split(' '))
        .forEach(([command, value]) => listProcessor[command](value));
}