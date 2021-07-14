function cookNumbers(number, command1, command2, command3, command4, command5) {
    let currentNum = Number(number);
    let commands = [command1, command2, command3,command4,command5];
    let result = [];

    for(let element of commands) {
        switch(element) {
            case "chop": currentNum /= 2; break;
            case "dice": currentNum = Math.sqrt(currentNum); break;
            case "spice": currentNum += 1; break;
            case "bake": currentNum *= 3; break;
            case "fillet": currentNum -= currentNum * 0.2; break;
        }
        result.push(currentNum);
        
    }
    return result.join('\n');
}

console.log(cookNumbers('9', 'dice', 'spice', 'chop', 'bake', 'fillet'))