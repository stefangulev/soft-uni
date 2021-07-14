function sort(arr) {
    arr.sort((l,r)=> {
        let result = l.length - r.length;
        if (result === 0) {
            result = l.localeCompare(r);
        }
        return result;
    })

    return arr.join('\n');
}
console.log(sort(['test', 
'Deny', 
'omen', 
'Default']
));