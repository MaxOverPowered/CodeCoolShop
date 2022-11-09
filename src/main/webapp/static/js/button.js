let buttons = document.getElementsByClassName("add-to-cart");
let valueInCart = document.querySelectorAll(".fa")

function increaseValueInShoppingCart() {
    let value = parseInt(valueInCart[0].getAttribute("value"));
    value += 1;
    valueInCart[0].setAttribute("value", value.toString());
}

function decreaseValueInShoppingCart() {
    let value = parseInt(valueInCart[0].getAttribute("value"));
    value -= 1;
    valueInCart[0].setAttribute("value", value.toString());
}

function addItem(buttons) {
    for (let button of buttons) {
        button.addEventListener("click", async function (event) {
            increaseValueInShoppingCart();
            let id = event.currentTarget.dataset.id;
            await apiGet(`/api/add?id=${id}`)
        });
    }
}

async function apiGet(url) {
    let response = await fetch(url)
    return response.json()
}

async function apiPost(url, payload) {
    const response = await fetch(url, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(payload)
    })
    return await response.json();
}
function init() {
    addItem(buttons);
}
init();