function commands(arr) {
    let numArray = [];
    let number = 1;

    for(element of arr) {
        element === "add" ? numArray.push(number++) : numArray.pop(number++);
    }

    
    return numArray.length ===0 ? "Empty" : numArray.join('\n'); 
}

console.log(commands(['remove', 
'remove', 
'remove']
));