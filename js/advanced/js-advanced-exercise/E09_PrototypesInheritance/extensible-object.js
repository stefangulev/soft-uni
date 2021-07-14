function solve() {
    const myObj = {
        extend: function(template) {
            Object.entries(template).forEach(([key, value]) => {
                if (typeof value ==='function') {
                    Object.getPrototypeOf(myObj)[key] = value;
                } else {
                    myObj[key] = value;
                }

            })
        }
    }
    return myObj;
    
}

