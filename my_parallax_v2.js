var offsetY = 0;
const offsetMultiplier = 0.4;
const background =document.getElementById('background');
const height = background.clientHeight;
const foreground = document.getElementById('foreground');
// var itemsAndSpeed = [[document.getElementById('background'),0.5]];
    window.addEventListener("scroll",function(e){
        offsetY = window.pageYOffset;
        scrollSpeed();
    },false);
        
  
    function scrollSpeed(){
        var x = offsetMultiplier * offsetY;
        var currentHeight = height  - x;
        background.style.transform = "translateY(" + x + "px)";     
        background.style.height = currentHeight +"px";
    }
    
