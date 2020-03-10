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
