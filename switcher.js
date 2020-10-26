var caregoryWords = [
  "Web Developer",
  "Game Developer",
  "Digital Artist",
  "Origami Artist",
  "Painter",
  "Calisthenics Athlete",
];
var myStringArray = [];
var switchSpeed = 180;

function stringCreator() {
  for (let n = 0; n < caregoryWords.length; n++) {
    var currentWord = "";
    myStringArray.push("&nbsp");
    myStringArray.push("|");
    myStringArray.push("&lt;" + "&nbsp");
    myStringArray.push("&lt;" + "|");

    for (let m = 0; m < caregoryWords[n].length; m++) {
      currentWord = currentWord + caregoryWords[n].charAt(m);

      myStringArray.push("&lt;" + currentWord + "|");

      myStringArray.push("&lt;" + currentWord + "&nbsp");
    }
    myStringArray.push("&lt;" + currentWord + "&gt;" + "&nbsp");
    myStringArray.push("&lt;" + currentWord + "&gt;" + "|");
    myStringArray.push("&lt;" + currentWord + "&gt;" + "&nbsp");
  }
  console.log(myStringArray);
}

var i = 0;

function switchee() {
  const word = document.getElementById("word");

  word.innerHTML = myStringArray[i];
  i++;
  if (i == myStringArray.length) {
    i = 0;
  }
}

window.setInterval(function () {
  switchee();
}, switchSpeed);

if (window.attachEvent) {
  window.attachEvent("onload", stringCreator());
} else if (window.addEventListener) {
  window.addEventListener("load", stringCreator(), false);
} else {
  document.addEventListener("load", stringCreator(), false);
}

// window.onload = function() {
//   console.log('we loaded');
//   ;
// };
