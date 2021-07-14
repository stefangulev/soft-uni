function generateReport() {
    let tableHeaders = Array.from(document.getElementsByTagName("input"));
    let checkedArrIndexes = [];
    let count = -1;
    tableHeaders.forEach(x => {
        count++;
        if(tableHeaders[count].checked) {
            checkedArrIndexes.push(count);
        }
    });
    let info = [];
    let employeeRows = document.querySelectorAll("tbody tr")
    for (let row of employeeRows) {
        let obj = {};
        let columns = Array.from(row.children);
        for (let index of checkedArrIndexes) {
            obj[tableHeaders[index].name] = columns[index].textContent;
        }
        info.push(obj);
    }

    let textArea = document.getElementById("output");
    textArea.textContent += JSON.stringify(info);
    
    
}