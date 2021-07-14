function solve(numberOfStepsToSchool, footPrintsInMeter, walkingSpeedInKm) {
const distance = numberOfStepsToSchool * footPrintsInMeter;
const speedPerSec = walkingSpeedInKm * 1000/3600;
const breakCount = Math.floor(distance / 500) * 60;
let time = distance/speedPerSec + breakCount;
let hours = Math.floor(time / 3600).toFixed(0).padStart(2, "0");
let minutes = Math.floor((time - hours * 3600) / 60).toFixed(0).padStart(2, "0");
let seconds = Math.round(time - hours * 3600 - minutes * 60).toFixed(0).padStart(2, "0");

return `${hours}:${minutes}:${seconds}`;
}
//should've used padStart(digitNum, firstDigit)

console.log(solve(2564, 0.70, 5.5));