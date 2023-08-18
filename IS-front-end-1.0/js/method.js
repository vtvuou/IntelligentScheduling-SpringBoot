function cla(area) {
    var d = new Date()
    area.innerHTML = `
    ${d.getFullYear()} 年
    ${d.getMonth()+1} 月
    ${d.getDate()} 日
    `
}

function init(perId, perName, selShop) {

    $.get(`http://localhost:8080/study/enterServlet/${"1"}/${"a"}/${"a"}`,
        function(data) {
            perId.innerHTML = data.ID
            perName.innerHTML = data.name
            pass(data, selShop)
        }
    ) 
}

function pass(data, selShop) {
    $.get (
        `http://localhost:8080/study/initServlet/${data.ID}`,
        function(resp) {
            console.log(resp)
            console.log(typeof resp)
            Shop(resp, selShop)
        }
    )
}
function Shop(need, selShop) {
    var op = document.createElement("option")
    op.value = need.ID
    op.innerHTML = need.shopName + "排班表"
    selShop.appendChild(op)
}

