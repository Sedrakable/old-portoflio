const arrowButton = document.getElementById("bottom_arrow");
const homeButton = document.getElementById("home_button");
const aboutButton = document.getElementById("about_button");
const projectButton = document.getElementById("project_button");
const url = window.location.pathname;
window.onload = function () {
  console.log(url);
  setActive();
};

function setActive() {
  switch (url) {
    case "/index.html":
      homeButton.classList.add("active_button_wrapper");
      break;
    case "/about.html":
      aboutButton.classList.add("active_button_wrapper");
      break;
    case "/projects.html":
      projectButton.classList.add("active_button_wrapper");
      break;
    case "/origami.html":
      projectButton.classList.add("active_button_wrapper");
      break;
    default:
      projectButton.classList.add("active_button_wrapper");
  }
}

const drop = document.getElementById("drop_down");
var bool = true;
arrowButton.addEventListener("click", () => {
  if (bool) {
    drop.classList.add("show_drop");
  } else {
    drop.classList.remove("show_drop");
  }
  bool ^= true;
});

// document.addEventListener(
//   "click",
//   function (e) {
//     e = e || window.event;
//     var target = e.target;
//     console.log(target);
//     console.log(arrowButton.childNodes[1]);
//     if (
//       target !== drop &&
//       target !== arrowButton &&
//       target !== arrowButton.childNodes[1] &&
//       !bool
//     ) {
//       drop.classList.remove("show_drop");
//       bool ^= true;
//     }
//   },
//   false
// );
