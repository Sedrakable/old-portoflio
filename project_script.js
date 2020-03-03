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
    popupTitle.appendChild(cloneTitle);
    popupDescription.appendChild(cloneDescription);   
    openPopup(popup);
}

function Close(){
        // const popup = button.closest('.popup')
        const popup = document.getElementById('popup')
        // console.log(popup)
        const popupBackground1 = popup.childNodes[1].childNodes[1];
        const popupBackground = popup.childNodes[1].childNodes[1].childNodes[1];

        const popupTitle1 = popup.childNodes[3].childNodes[1].childNodes[1];
        const popupTitle = popup.childNodes[3].childNodes[1].childNodes[1].childNodes[1];

        const popupDescription1 = popup.childNodes[3].childNodes[3];
        const popupDescription = popup.childNodes[3].childNodes[3].childNodes[1];

        

        closePopup(popup)
        setTimeout(function() {

        popupBackground1.removeChild(popupBackground)     
        popupTitle1.removeChild(popupTitle)
        popupDescription1.removeChild(popupDescription)

        }, 500)
}

function Replace(button){

    const popup = document.getElementById('popup')

    const popupBackgroundContainer = popup.childNodes[1].childNodes[1];
    const popupBackground = popup.childNodes[1].childNodes[1].childNodes[1];
    const background = button.childNodes[1].childNodes[1];
    const cloneBackground = background.cloneNode(true);

    const popupTitleContainer = popup.childNodes[3].childNodes[1].childNodes[1];
    const popupTitle = popup.childNodes[3].childNodes[1].childNodes[1].childNodes[1];
    const title = button.childNodes[5].childNodes[0];
    const cloneTitle = title.cloneNode(true);

    const popupDescriptionContainer = popup.childNodes[3].childNodes[3];
    const popupDescription = popup.childNodes[3].childNodes[3].childNodes[1];
    const description = button.childNodes[7].childNodes[1];
    const cloneDescription = description.cloneNode(true);

    popupBackgroundContainer.replaceChild(cloneBackground, popupBackground);
    popupTitleContainer.replaceChild(cloneTitle, popupTitle);
    popupDescriptionContainer.replaceChild(cloneDescription, popupDescription);
    tryer()
}


openPopupButton.forEach(button => {
    button.addEventListener('click', () => {  
        Open(button)    
        currentCategory = button;
        tryer()
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
    
}   function leftClick(){
        const leftCategory = currentCategory.previousSibling.previousSibling; 
        currentCategory = leftCategory;     
        Replace(leftCategory)
    }

    function rightClick(){
        const rightCategory = currentCategory.nextSibling.nextSibling;    
        currentCategory = rightCategory;  
        Replace(rightCategory)
    }

    leftButton.addEventListener('click', () => {  
        leftClick()   
    })

    rightButton.addEventListener('click', () => {  
        rightClick()       
    })

    function tryer(){
        const rightCategory = currentCategory.nextSibling.nextSibling;
        

        if(rightCategory == null)
        {
            rightButton.classList.remove('active')
        }
        else{
            rightButton.classList.add('active')
        }
        

        const leftCategory = currentCategory.previousSibling.previousSibling;

        if(leftCategory == null)
        {
            leftButton.classList.remove('active')
        }
        else{
            leftButton.classList.add('active')
        }
        
    }

