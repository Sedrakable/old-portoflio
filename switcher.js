
var myStringArray = ["&lt;|","&lt;&nbsp;","&lt;C|","&lt;C&nbsp;","&lt;Co|","&lt;Co&nbsp;","&lt;Cod|","&lt;Cod&nbsp;","&lt;Code|","&lt;Code&nbsp;","&lt;Code|&gt;","&lt;Code&nbsp;&gt;","&lt;Code&gt;|"];
myStringArray.push("&lt;|","&lt;&nbsp;","&lt;D|","&lt;D&nbsp;","&lt;Dr|","&lt;Dr&nbsp;","&lt;Dra|","&lt;Dra&nbsp;","&lt;Draw|","&lt;Draw&nbsp;","&lt;Draw|&gt;","&lt;Draw&nbsp;&gt;","&lt;Draw&gt;|");
myStringArray.push("&lt;|","&lt;&nbsp;","&lt;P|","&lt;P&nbsp;","&lt;Pa|","&lt;Pa&nbsp;","&lt;Pai|","&lt;Pai&nbsp;","&lt;Pain|","&lt;Pain&nbsp;","&lt;Paint|","&lt;Paint&nbsp;","&lt;Paint|&gt;","&lt;Paint&nbsp;&gt;","&lt;Paint&gt;|");

var i =0;
// const

function switchee(){
    
  const word = document.getElementById('word')

  word.innerHTML = myStringArray[i];
  i++
  if(i == myStringArray.length ){
    i = 0
  }
  
}

window.setInterval(function(){
    switchee()
    
  }, 200);
