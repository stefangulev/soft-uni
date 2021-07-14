function evenPosition(arr) {
   let evenArr = [];
   
    for(let index = 0; index < arr.length; index+=2) {
        evenArr.push(arr[index]);
    }
    

    return evenArr.join(" ");
}

console.log(evenPosition(['20', '30', '40', '50', '60']));