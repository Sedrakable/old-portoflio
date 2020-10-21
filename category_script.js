const Items = document.querySelectorAll('.category');

// Items.forEach(item => {
        
//         item.addEventListener('mouseout', () => {
//             console.log(item.childNodes);
//             item.childNodes[1].classList.add('img_shrink');
//             item.childNodes[1].classList.remove('img_widen');
//             item.childNodes[5].classList.remove('text_right');
//             item.childNodes[5].classList.add('text_left');
//         })

//         item.addEventListener('mouseover', () => {
//             console.log(item.childNodes);
//             item.childNodes[1].classList.remove('img_shrink');
//             item.childNodes[1].classList.add('img_widen');
//             item.childNodes[5].classList.remove('text_left');
//             item.childNodes[5].classList.add('text_right');
//         })
// })

Items.forEach(item => {
        
    item.addEventListener('mouseout', () => {
        item.childNodes[1].classList.add('img_shrink');
        item.childNodes[1].classList.remove('img_widen');
        item.childNodes[3].classList.add('img_full');
        item.childNodes[3].classList.remove('img_transparent');
        
        // item.querySelector("logo_background")
    })

    item.addEventListener('mouseover', () => {
        item.childNodes[1].classList.remove('img_shrink');
        item.childNodes[1].classList.add('img_widen');
        item.childNodes[3].classList.remove('img_full');
        item.childNodes[3].classList.add('img_transparent');
        
    })
})
