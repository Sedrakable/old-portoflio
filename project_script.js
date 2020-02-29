const Items = document.querySelectorAll('.item_wrapper');

        Items.forEach(item => {
                
                item.addEventListener('mouseout', () => {
                    item.childNodes[1].classList.remove('img_darken');
                    item.childNodes[1].classList.add('img_ligthen');  
                    item.childNodes[3].classList.remove('reveal_text');
                    item.childNodes[3].classList.add('hide_text');      
                })

                item.addEventListener('mouseover', () => {
                    item.childNodes[1].classList.remove('img_ligthen');
                    item.childNodes[1].classList.add('img_darken');
                    item.childNodes[3].classList.remove('hide_text');
                    item.childNodes[3].classList.add('reveal_text');

                })
        })


const openPopupButton = document.querySelectorAll('.item_wrapper')
const closePopupButton = document.querySelectorAll('.close_button')
const overlay = document.getElementById('overlay')

openPopupButton.forEach(button => {
    button.addEventListener('click', () => {  
        
        const popup = document.getElementById('popup');
        
        const background = button.childNodes[1].childNodes[1];
        const cloneBackground = background.cloneNode(true);
        const popupBackground = popup.childNodes[1].childNodes[1];
        
        const title = button.childNodes[3].childNodes[0];
        const cloneTitle = title.cloneNode(true);
        const popupTitle = popup.childNodes[3].childNodes[1].childNodes[1];

        console.log(button.childNodes[5].childNodes)
        const description = button.childNodes[5].childNodes[1];
        const cloneDescription = description.cloneNode(true);
        const popupDescription = popup.childNodes[3].childNodes[3];

        popupBackground.appendChild(cloneBackground);
        popupDescription.appendChild(cloneDescription);      
        popupTitle.appendChild(cloneTitle);
        openPopup(popup);

    })
})

closePopupButton.forEach(button => {
    button.addEventListener('click', () => {
        
        const popup = button.closest('.popup')

        const popupBackground1 = popup.childNodes[1].childNodes[1];
        const popupBackground = popup.childNodes[1].childNodes[1].childNodes[1];

        const popupDescription1 = popup.childNodes[3].childNodes[3];
        const popupDescription = popup.childNodes[3].childNodes[3].childNodes[1];

        const popupTitle1 = popup.childNodes[3].childNodes[1].childNodes[1];
        const popupTitle = popup.childNodes[3].childNodes[1].childNodes[1].childNodes[1];

        closePopup(popup)
        popupBackground1.removeChild(popupBackground)
        popupDescription1.removeChild(popupDescription)
        popupTitle1.removeChild(popupTitle)
    })
})

function openPopup(popup) { 
    if (popup == null) return
    popup.classList.add('active')
    overlay.classList.add('active')
}

function closePopup(popup) {
    if (popup == null) return
    popup.classList.remove('active')
    overlay.classList.remove('active')
}
