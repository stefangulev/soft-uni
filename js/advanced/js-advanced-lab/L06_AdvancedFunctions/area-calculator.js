function solve(area, vol, str) {
    let data = JSON.parse(str);

    let result = [];
    for(let element of data) {
        let objArea = Math.abs(area.call(element));
        let objVolume = Math.abs(vol.call(element));
        result.push({
            area: objArea,
            volume: objVolume
        })
    }
    return result;
 
 }

 function area() {
     return Math.abs(this.x * this.y);
 }
 function vol () {
    return Math.abs(this.x * this.y * this.z);
 }

 console.log(solve(area, vol, ('[{"x":"1","y":"2","z":"10"},{"x":"7","y":"7","z":"10"},{"x":"5","y":"2","z":"10"}]')));