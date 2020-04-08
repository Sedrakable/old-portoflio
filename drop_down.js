const button = document.getElementById('bottom_arrow')

  const drop = document.getElementById('drop_down')
  var bool = true;
  button.addEventListener('click', () => { 
    
    if(bool){
      drop.classList.add('show_drop')
    }else{
      drop.classList.remove('show_drop')
    }
    bool ^= true; 
  })

  

  document.addEventListener('click', function(e) {
    e = e || window.event;
    var target = e.target 
    console.log(target)
    console.log(button.childNodes[1])
    if(target !== drop && target !== button && target !== button.childNodes[1] && !bool){
      drop.classList.remove('show_drop')
      bool ^= true; 
    }
}, false);
    
  

    
