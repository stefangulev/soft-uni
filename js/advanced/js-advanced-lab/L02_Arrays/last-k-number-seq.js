function numberSeq(n, k) {
    let arr = [1];

    for(i = 1; i < n; i++) {
        let sum = 0;
        for(j = i-k; j < i; j++) {
            if (arr[j] != undefined) {
                sum+=arr[j];
            }
            
        }
        arr.push(sum);
    }
    return arr;
}

console.log(numberSeq(8 , 2));