const Items = document.querySelectorAll(".item_wrapper");
const popup = document.getElementById("popup");

Items.forEach((item) => {
  item.addEventListener("mouseout", () => {
    console.log(item);
    item.childNodes[3].classList.remove("img_darken");
    item.childNodes[3].classList.add("img_ligthen");
    item.childNodes[5].classList.remove("reveal_text");
    item.childNodes[5].classList.add("hide_text");
    item.childNodes[9].classList.remove("reveal_dot");
    item.childNodes[9].classList.add("hide_dot");
  });

  item.addEventListener("mouseover", () => {
    item.childNodes[3].classList.remove("img_ligthen");
    item.childNodes[3].classList.add("img_darken");
    item.childNodes[5].classList.remove("hide_text");
    item.childNodes[5].classList.add("reveal_text");
    item.childNodes[9].classList.remove("hide_dot");
    item.childNodes[9].classList.add("reveal_dot");
  });
});

const openPopupButton = document.querySelectorAll(".item_wrapper");
const closePopupButton = document.querySelectorAll(".close_button");
const overlay = document.getElementById("overlay");
const leftButton = document.getElementById("left_arrow");
const rightButton = document.getElementById("right_arrow");
const leftFleshButton = document.getElementById("left_flesh");
const rightFleshButton = document.getElementById("right_flesh");

var currentCategory;
var currentBackground;
var num = 1;

function Open(button) {
  const background = button.childNodes[1].childNodes[1];
  const cloneBackground = background.cloneNode(true);
  const popupBackground = popup.childNodes[1].childNodes[1];

  const title = button.childNodes[5].childNodes[0];
  const cloneTitle = title.cloneNode(true);
  const popupTitle = popup.childNodes[3].childNodes[1].childNodes[1];

  const description = button.childNodes[7].childNodes[1];
  const cloneDescription = description.cloneNode(true);
  const popupDescription = popup.childNodes[3].childNodes[3];

  const dots = button.childNodes[9];
  const cloneDots = dots.cloneNode(true);
  const popupDots = popup.childNodes[1].childNodes[7];
  cloneDots.classList.remove("reveal_dot");
  opacityAdder(cloneDots);

  popupBackground.appendChild(cloneBackground);
  popupTitle.appendChild(cloneTitle);
  popupDescription.appendChild(cloneDescription);
  popupDots.appendChild(cloneDots);
  openPopup(popup);
}

function opacityAdder(dots) {
  const x = dots.childElementCount;
  for (let i = 1; i <= x; i++) {
    dots.childNodes[i].classList.remove("high_opacity");
    dots.childNodes[i].classList.add("low_opacity");
  }
}

function Close() {
  // const popup = button.closest('.popup')
  const popup = document.getElementById("popup");
  // console.log(popup)
  const popupBackground1 = popup.childNodes[1].childNodes[1];
  const popupBackground = popup.childNodes[1].childNodes[1].childNodes[1];

  const popupTitle1 = popup.childNodes[3].childNodes[1].childNodes[1];
  const popupTitle =
    popup.childNodes[3].childNodes[1].childNodes[1].childNodes[1];

  const popupDescription1 = popup.childNodes[3].childNodes[3];
  const popupDescription = popup.childNodes[3].childNodes[3].childNodes[1];

  const popupDots1 = popup.childNodes[1].childNodes[7];
  const popupDots = popup.childNodes[1].childNodes[7].childNodes[1];

  closePopup(popup);
  setTimeout(function () {
    popupBackground1.removeChild(popupBackground);
    popupTitle1.removeChild(popupTitle);
    popupDescription1.removeChild(popupDescription);
    popupDots1.removeChild(popupDots);
  }, 500);
}

function Replace(button) {
  const popup = document.getElementById("popup");

  const popupBackgroundContainer = popup.childNodes[1].childNodes[1];
  const popupBackground = popup.childNodes[1].childNodes[1].childNodes[1];
  const background = button.childNodes[1].childNodes[1];
  const cloneBackground = background.cloneNode(true);

  const popupTitleContainer = popup.childNodes[3].childNodes[1].childNodes[1];
  const popupTitle =
    popup.childNodes[3].childNodes[1].childNodes[1].childNodes[1];
  const title = button.childNodes[5].childNodes[0];
  const cloneTitle = title.cloneNode(true);

  const popupDescriptionContainer = popup.childNodes[3].childNodes[3];
  const popupDescription = popup.childNodes[3].childNodes[3].childNodes[1];
  const description = button.childNodes[7].childNodes[1];
  const cloneDescription = description.cloneNode(true);

  const popupDotsContainer = popup.childNodes[1].childNodes[7];
  const popupDots = popup.childNodes[1].childNodes[7].childNodes[1];
  const dots = button.childNodes[9];
  const cloneDots = dots.cloneNode(true);
  cloneDots.classList.remove("hide_dot");
  opacityAdder(cloneDots);

  popupBackgroundContainer.replaceChild(cloneBackground, popupBackground);
  popupTitleContainer.replaceChild(cloneTitle, popupTitle);
  popupDescriptionContainer.replaceChild(cloneDescription, popupDescription);
  popupDotsContainer.replaceChild(cloneDots, popupDots);
  tryer();

  currentBackground = button.childNodes[1].childNodes[1];
  fleshTryer();
  documentListeners();
}

function innerReplace(button) {
  const popup = document.getElementById("popup");

  const popupBackgroundContainer = popup.childNodes[1].childNodes[1];
  const popupBackground = popup.childNodes[1].childNodes[1].childNodes[1];
  const background = button;
  const cloneBackground = background.cloneNode(true);

  popupBackgroundContainer.replaceChild(cloneBackground, popupBackground);
}

openPopupButton.forEach((button) => {
  button.addEventListener("click", () => {
    Open(button);
    currentCategory = button;
    currentBackground = currentCategory.childNodes[1].childNodes[1];
    tryer();
    fleshTryer();
    dotOpacity();
  });
});

closePopupButton.forEach((button) => {
  button.addEventListener("click", () => {
    Close();
    num = 1;
  });
});

function openPopup(popup) {
  if (popup == null) return;
  popup.classList.add("active");
  overlay.classList.add("active");
  leftButton.classList.add("active");
  rightButton.classList.add("active");
  documentListeners();
}

function closePopup(popup) {
  if (popup == null) return;
  popup.classList.remove("active");
  overlay.classList.remove("active");
  leftButton.classList.remove("active");
  rightButton.classList.remove("active");
}

function leftClick() {
  const leftCategory = currentCategory.previousSibling.previousSibling;
  currentCategory = leftCategory;
  Replace(leftCategory);
  num = 1;
  dotOpacity();
}

function rightClick() {
  const rightCategory = currentCategory.nextSibling.nextSibling;
  currentCategory = rightCategory;
  Replace(rightCategory);
  num = 1;
  dotOpacity();
}

leftButton.addEventListener("click", () => {
  leftClick();
});

rightButton.addEventListener("click", () => {
  rightClick();
});

function leftFleshClick() {
  const leftBackground = currentBackground.previousSibling.previousSibling;
  currentBackground = leftBackground;
  innerReplace(leftBackground);
  fleshTryer();
  num--;
  dotOpacity();
}

function rightFleshClick() {
  const rightBackground = currentBackground.nextSibling.nextSibling;
  currentBackground = rightBackground;
  innerReplace(rightBackground);
  fleshTryer();
  num++;
  dotOpacity();
}

leftFleshButton.addEventListener("click", () => {
  leftFleshClick();
});

rightFleshButton.addEventListener("click", () => {
  rightFleshClick();
});

function tryer() {
  const rightCategory = currentCategory.nextSibling.nextSibling;

  if (rightCategory == null) {
    rightButton.classList.remove("active");
  } else {
    rightButton.classList.add("active");
  }

  const leftCategory = currentCategory.previousSibling.previousSibling;

  if (leftCategory == null) {
    leftButton.classList.remove("active");
  } else {
    leftButton.classList.add("active");
  }
}

function fleshTryer() {
  const rightBackground = currentBackground.nextSibling.nextSibling;

  if (rightBackground == null) {
    rightFleshButton.classList.add("blocked");
  } else {
    rightFleshButton.classList.remove("blocked");
  }

  const leftBackground = currentBackground.previousSibling.previousSibling;

  if (leftBackground == null) {
    leftFleshButton.classList.add("blocked");
  } else {
    leftFleshButton.classList.remove("blocked");
  }
}

function dotChecker() {
  //need to go throgh all picitures and find out the right number of pitures
  const item_grid = document.getElementById("item_grid");
  const dot = document.getElementById("dots_holder").childNodes[1];

  const x = item_grid.childElementCount;

  for (let i = 1; i <= x; i++) {
    const n = 2 * i - 1;
    const dotContainer = item_grid.childNodes[n].childNodes[9];
    const y = item_grid.childNodes[n].childNodes[1].childElementCount;
    const dotClones = [dot.cloneNode(true)];

    for (let i = 0; i < y; i++) {
      dotContainer.appendChild(dotClones[i]);
      dotClones.push(dot.cloneNode(true));
    }
  }
}

function dotOpacity() {
  const popup = document.getElementById("popup");
  const popupDot =
    popup.childNodes[1].childNodes[7].childNodes[1].childNodes[num];
  opacityAdder(popup.childNodes[1].childNodes[7].childNodes[1]);
  popupDot.classList.remove("low_opacity");
  popupDot.classList.add("high_opacity");
}

window.onload = function () {
  dotChecker();
};

function documentListeners() {
  const Files = document.querySelectorAll(".file");

  const SourceButtons = document.querySelectorAll(".source_arrow");
  const docContainer = document
    .querySelector(".popup_text")
    .querySelector(".download_container");
  var sourceToggle = false;

  Files.forEach((item) => {
    var x = item.querySelector(".file_logo").querySelector("#file_logo");
    var y = item.querySelector(".file_logo").querySelector(".document_buttons");

    item.addEventListener("mouseout", () => {
      x.classList.remove("grey");
      y.classList.remove("visable");

      // item.querySelector("logo_background")
    });

    item.addEventListener("mouseover", () => {
      x.classList.add("grey");
      y.classList.add("visable");
    });
  });

  SourceButtons.forEach((item) => {
    item.addEventListener("click", () => {
      if (!sourceToggle) {
        console.log(docContainer);
        item.classList.add("rotate");
        docContainer.classList.add("visable_container");
      } else if (sourceToggle) {
        console.log(docContainer);
        item.classList.remove("rotate");
        docContainer.classList.remove("visable_container");
      }

      sourceToggle = !sourceToggle;

      // item.querySelector("logo_background")
    });

    item.addEventListener("mouseout", () => {
      item.classList.remove("grey");
    });

    item.addEventListener("mouseover", () => {
      item.classList.add("grey");
    });
  });
}
