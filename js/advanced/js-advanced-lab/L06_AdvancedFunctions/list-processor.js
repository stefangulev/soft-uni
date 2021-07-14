function solve(arr) {
    let collection = [];

    // for (let element of arr) {
    //     let tokens = element.split(" ");
    //     if (tokens.length >= 2) {
    //        let funct =  processor()[tokens[0]];
    //        funct(tokens[1]);
    //     } else {
    //         let funct = processor()["print"];
    //         funct();
    //     }
        
    // }

    function processor() {
        return {
            add: (str) => {
                collection.push(str)
            },
            remove: (str) => {
                while(collection.indexOf(str) != -1) {
                    let index = collection.indexOf(str);
                    collection.splice(index, 1);
                }
              
            },
            print: () => {
                console.log(collection.join(","));
            }
        }
    }
    let func = processor();
    input = arr.map(x=> x.split(" ")).forEach(([command,value]) => func[command](value));


}
solve(['add peter', 'add george', 'add peter', 'remove peter','print']);