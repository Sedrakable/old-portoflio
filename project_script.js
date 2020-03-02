const Items = document.querySelectorAll('.item_wrapper');

        Items.forEach(item => {
                
                item.addEventListener('mouseout', () => {
                    item.childNodes[3].classList.remove('img_darken');
                    item.childNodes[3].classList.add('img_ligthen');  
                    item.childNodes[5].classList.remove('reveal_text');
                    item.childNodes[5].classList.add('hide_text');      
                })

                item.addEventListener('mouseover', () => {
                    item.childNodes[3].classList.remove('img_ligthen');
                    item.childNodes[3].classList.add('img_darken');
                    item.childNodes[5].classList.remove('hide_text');
                    item.childNodes[5].classList.add('reveal_text');

                })
        })


const openPopupButton = document.querySelectorAll('.item_wrapper')
const closePopupButton = document.querySelectorAll('.close_button')
const overlay = document.getElementById('overlay')
const leftButton = document.getElementById('left_arrow')
const rightButton = document.getElementById('right_arrow')
var currentCategory;


function Open(button){
    const popup = document.getElementById('popup');
        
    const background = button.childNodes[1].childNodes[1];
    const cloneBackground = background.cloneNode(true);
    const popupBackground = popup.childNodes[1].childNodes[1];
    
    const title = button.childNodes[5].childNodes[0];
    const cloneTitle = title.cloneNode(true);
    const popupTitle = popup.childNodes[3].childNodes[1].childNodes[1];

    const description = button.childNodes[7].childNodes[1];
    const cloneDescription = description.cloneNode(true);
    const popupDescription = popup.childNodes[3].childNodes[3];

    popupBackground.appendChild(cloneBackground);
    popupDescription.appendChild(cloneDescription);      
    popupTitle.appendChild(cloneTitle);
    openPopup(popup);
}

function Close(){
        // const popup = button.closest('.popup')
        const popup = document.getElementById('popup')
        // console.log(popup)
        const popupBackground1 = popup.childNodes[1].childNodes[1];
        const popupBackground = popup.childNodes[1].childNodes[1].childNodes[1];

        const popupDescription1 = popup.childNodes[3].childNodes[3];
        const popupDescription = popup.childNodes[3].childNodes[3].childNodes[1];

        const popupTitle1 = popup.childNodes[3].childNodes[1].childNodes[1];
        const popupTitle = popup.childNodes[3].childNodes[1].childNodes[1].childNodes[1];

        closePopup(popup)
        setTimeout(function() {

        popupBackground1.removeChild(popupBackground)
        popupDescription1.removeChild(popupDescription)
        popupTitle1.removeChild(popupTitle)

        }, 500)
}


openPopupButton.forEach(button => {
    button.addEventListener('click', () => {  
        Open(button)    
        currentCategory = button;
    })
})


closePopupButton.forEach(button => {
    button.addEventListener('click', () => {
        Close()
        
    })
})

function openPopup(popup) { 
    if (popup == null) return
    popup.classList.add('active')
    overlay.classList.add('active')
    leftButton.classList.add('active')
    rightButton.classList.add('active')
}

function closePopup(popup) {
    if (popup == null) return
    popup.classList.remove('active')
    overlay.classList.remove('active')
    leftButton.classList.remove('active')
    rightButton.classList.remove('active')
    
}


    leftButton.addEventListener('click', () => {  

        const leftCategory = currentCategory.previousSibling.previousSibling;
        // if(leftCategory.classList == currentCategory.classList )
        // {
        //     leftCategory = currentCategory.parentNode.lastChild; 
        currentCategory = leftCategory;
        console.log(leftCategory)
        // }
        
            Close();
            
        
        Open(leftCategory);
        // leftCategory.Click();
        
     
    })

    rightButton.addEventListener('click', () => {  

        const rightCategory = currentCategory.nextSibling.nextSibling;
        // if(rightCategory.classList == currentCategory.classList )
        // {
        //     rightCategory = currentCategory.parentNode.lastChild; 
        currentCategory = rightCategory;
        console.log(rightCategory)
        // }
        
            Close();
            
        
        Open(rightCategory);
        // leftCategory.Click();
        
     
    })

