init();

function init() {
    let options = document.querySelectorAll('option');
    for (let option of options) {
        console.log(option);
        option.addEventListener("click", filter);
    }
}

async function filter(e) {
    console.log(e.target);
}