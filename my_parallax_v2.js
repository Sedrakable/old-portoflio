var offsetY = 0;
const offsetMultiplier = 0.4;
const background =document.getElementById('background');
const height = background.offsetHeight;

// var itemsAndSpeed = [[document.getElementById('background'),0.5]];
    window.addEventListener("scroll",function(e){
        offsetY = window.pageYOffset;
        scrollSpeed();
    },false);
        
  
    function scrollSpeed(){
        var x = offsetMultiplier * offsetY;
        console.log("height: " + height + "x: " +x);
        var currentHeight = height  - x;
        background.style.transform = "translateY(" + x + "px)";     
        background.style.height = currentHeight +"px";
    }
    
