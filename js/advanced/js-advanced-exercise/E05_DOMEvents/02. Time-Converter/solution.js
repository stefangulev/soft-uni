function attachEventsListeners() {
    let daysInput = document.getElementById("days");
    let hoursInput = document.getElementById("hours");
    let minutesInput = document.getElementById("minutes");
    let secondsInput = document.getElementById("seconds");
    

    let buttons = Array.from(document.querySelectorAll('input[type=button]'));
    console.log(buttons);

    buttons.forEach(x => x.addEventListener("click", onClick));

   

    function onClick(event) {
        let input;
        if (event.target.id === "daysBtn") {
            input = daysInput.value;
            hoursInput.value = input * 24;
            minutesInput.value = input * 1440;
            secondsInput.value = input *86400;
        } else if (event.target.id === "hoursBtn") {
            input = hoursInput.value;
            daysInput.value = input / 24;
            minutesInput.value = input * 60;
            secondsInput.value = input * 3600;
        } else if (event.target.id === "minutesBtn") {
            input = minutesInput.value;
            daysInput.value = input /1440;
            hoursInput.value = input / 60;
            secondsInput.value = input * 60;
        } else if (event.target.id === "secondsBtn") {
            input = secondsInput.value;
            daysInput.value = input / 86400;
            hoursInput.value = input / 3600;
            minutesInput.value = input / 60;
        }
    }

}