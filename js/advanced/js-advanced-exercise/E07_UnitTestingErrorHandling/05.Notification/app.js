function notify(message) {
  const notifyDiv = document.getElementById("notification");
  notifyDiv.style.display = "block";
  notifyDiv.textContent = message;
  notifyDiv.addEventListener("click", hide);

  function hide(ev) {
    console.log(ev);
    notifyDiv.style.display = "none";
  }

}