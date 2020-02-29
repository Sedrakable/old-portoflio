const openPopupButton = document.querySelectorAll('[data-open-button]')
const closePopupButton = document.querySelectorAll('[data-close-button]')
const overlay = document.getElementsByClassName('overlay')

openPopupButton.forEach(button => {
    button.addEventListener('click', () => {
        const popup = document.querySelector(button.dataset.popupTarget)
        openPopup(Popup);
    })
})

closePopupButton.forEach(button => {
    button.addEventListener('click', () => {
        const popup = button.closest('.popup')
        closePopup(Popup);
    })
})

function openPopup(Popup) {
    if (Popup == null) return
    popup.classList.add('active')
    overlay.classList.add('active')
}

function closePopup(Popup) {
    if (Popup == null) return
    popup.classList.remove('active')
    overlay.classList.remove('active')
}