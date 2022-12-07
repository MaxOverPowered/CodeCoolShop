init();
let valueInCart = document.querySelectorAll(".fa");

function init() {
    let buttons = document.getElementsByClassName("add-to-cart");
    const categoryButton = document.querySelectorAll('.category');
    const supplierButton = document.querySelectorAll('.supplier');
    addItem(buttons);
    categoryButton.forEach(button => button.addEventListener("click", async function (e) {
        e.preventDefault();
        let categoryId = e.currentTarget.getAttribute('category-id');
        let products = await apiGet(`/api/category?categoryId=${categoryId}`);
        removeContent();
        renderProduct(products);
        addItem(buttons);
    }))
    supplierButton.forEach(button => button.addEventListener("click", async function (e) {
        e.preventDefault();
        let supplierId = e.currentTarget.getAttribute('supplier-id');
        let products = await apiGet(`/api/supplier?supplierId=${supplierId}`);
        removeContent();
        renderProduct(products);
        addItem(buttons);
    }))
}

function removeContent() {
    const contentDivs = document.querySelectorAll("#products div");
    contentDivs.forEach(contentDiv => contentDiv.remove());
}

function renderProduct(products) {
    const contentDiv = document.querySelector("#products");
    for (let product of products) {
        let outerDiv = document.createElement('div');
        outerDiv.classList.add("col");
        outerDiv.classList.add("col-sm-12");
        outerDiv.classList.add("col-md-6");
        outerDiv.classList.add("col-lg-4");
        outerDiv.innerHTML = `<div class="card d-flex align-items-stretch"  data-id=${product.id}>
                    <img class="card-img-top cardimage"   src="/static/img/${product.image}.jpg" alt="Card Image"/>
                    <div class="card-body d-flex flex-column">
                        <h4 class="text-uppercase border-bottom mb-4" >${product.name}</h4>
                        <p class="card-text" >${product.description}</p>
                            <p class="mt-auto align-self-start lead" >${product.defaultPrice} ${product.defaultCurrency}</p>
                            <a class="btn btn-danger mt-auto align-self-start add-to-cart" href="#" data-id="${product.id}"><b>Add to cart</b></a>
                    </div>
                  </div>`;
        contentDiv.appendChild(outerDiv);
    }
}

async function apiGet(url) {
    let response = await fetch(url, {
        method: "GET"
    })
    if (response.ok) {
        return await response.json();
    }
}

function increaseValueInShoppingCart() {
    let value = parseInt(valueInCart[0].getAttribute("value"));
    value += 1;
    valueInCart[0].setAttribute("value", value.toString());
}

function modifyQuantity() {
    let quantityModifier = document.getElementById("quantity");
    quantityModifier.addEventListener("click")
}


function addItem(buttons) {
    for (let button of buttons) {
        button.addEventListener("click", async function (event) {
            increaseValueInShoppingCart();
            let id = event.currentTarget.dataset.id;
            let payload = {'id': id};
            await apiPost("/api/add", payload);
        });
    }
}

async function apiPost(url, payload) {
    const response = await fetch(url, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(payload)
    })
    return await response.json();
}