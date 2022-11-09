init();

function init() {
    const buttons = document.querySelectorAll('.category');
    buttons.forEach(button => button.addEventListener("click", async function (e) {
        e.preventDefault();
        let categoryId = e.currentTarget.getAttribute('category-id');
        let products = await apiGet(`/api/category?categoryId=${categoryId}`);
        removeContent();
        renderProduct(products);
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
        outerDiv.innerHTML = `<div class="card"  data-id=${product.id}>
                    <img class=""   src="/static/img/product_${product.id}.jpg" alt=""/>
                    <div class="card-header">
                        <h4 class="card-title" >${product.name}</h4>
                        <p class="card-text" >${product.description}</p>
                    </div>
                    <div class="card-body">
                        <div class="card-text">
                            <p class="lead" >${product.defaultPrice} ${product.defaultCurrency}</p>
                        </div>
                        <div class="card-text">
                            <a class="btn btn-success add-to-cart">Add to cart</a>
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