const Files = document.querySelectorAll('.document_buttons');





Files.forEach((item) => {
    console.log(item);
    item.addEventListener("mouseout", () => {
        var x= item.querySelector("#file_logo");
        console.log(x);

    });
  
    item.addEventListener("mouseover", () => {
        console.log(item);
    });
  });