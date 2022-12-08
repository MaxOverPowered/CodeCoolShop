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
        outerDiv.innerHTML = `<br><div style="min-height: 200px; min-width: 200px" id="sss" class="card d-flex align-items-stretch"  data-id=${product.id}>
                    <img class="cardimage"   src="/static/img/${product.image}.jpg" alt=""/>
                    <div style="min-height: 200px; min-width: 200px" class="card-header">
                        <h4 class="card-title" >${product.name}</h4>
                        <p class="card-text" >${product.description}</p>
                    </div>
                     <div class="card-body">
                        <div class="card-text">
                            <p class="lead" >${product.defaultPrice} ${product.defaultCurrency}</p>
                        </div>
                        <div class="card-text">
                            <a class="btn btn-danger add-to-cart" data-id="${product.id}"><b>Add to cart</b></a>
                        </div>
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

function decreaseValueInShoppingCart() {
    let value = parseInt(valueInCart[0].getAttribute("value"));
    value -= 1;
    valueInCart[0].setAttribute("value", value.toString());
}

function loadCheckoutPage() {
    let container = document.getElementById("index-container");
    valueInCart.addEventListener("click", async function (event) {
        container.innerHTML = "";
        // let products = ???
        container.innerHTML = `
            <h1 class="">Checkout</h1>
            <table>
                <thead>
                <tr id="checkout-header">
                    <th class="child1">Image</th>
                    <th class="child1">Product</th>
                    <th class="child1">Unitprice</th>
                    <th class="child1">Quantity</th>
                    <th class="child1">Sum</th>
                </tr>
                </thead>
                <tbody>
                ${tbody}
               
                </tbody>
            </table>
                <div id="total-price">
                    <strong class="total-child">Total: </strong>
                    <strong class="total-child" th:text="${total} + ' USD'"></strong>
                </div>
                <a href="payment"><button class="btn btn-danger">Payment</button></a>

        `
        let tbody = "";
        for (let product of products) {
            let tablerow = `
                <tr id="parent">
                    <td><img src="http://placehold.it/400x250/000/fff" src='/static/img/${product.getProduct().picture}.jpg' alt="" /></td>
                    <td class="child">${product.getProduct().name}</td>
                    <td class="child">${product.getProduct().defaultPrice}  ${product.getProduct().defaultCurrency}</td>
                    <td><input name="quant" class="quant" data-productid="${product.getProduct().id}" value="${product.getQuantity()}" type="number"></td>
                    <td class="child">${product.getSumPricePerProduct()} ${product.getProduct().defaultCurrency}</td>
                </tr>
            `
            tbody += tablerow;
        }
    })
}

function addItem(buttons) {
    for (let button of buttons) {
        button.addEventListener("click", async function (event) {
            increaseValueInShoppingCart();
            let id = event.currentTarget.dataset.id;
            let payload = {'id': id};
            await apiPost("/api/cartitem" ,payload);
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

