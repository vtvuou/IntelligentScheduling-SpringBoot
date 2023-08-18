function empInit(val1,val2,val3,box) {

    $.get(`http://localhost:8080/study/enterServlet/${"1"}/${"a"}/${"a"}`,
        function(resp) {
            searchEmp(resp,val1,val2,val3,box)
        }
    ) 
}

function searchEmp(data,val1,val2,val3,box) {
    val1 += "1"
    val2 += "1"
    val3 += "1"
    $.get(
        `http://localhost:8080/study/empTableServlet/${data.ID}/${val1}/${val2}/${val3}`,
        function(resp) {
            draw(resp,box)
        }
    )
}
function draw(need, box) {
    var dt = need.data.map(item => {
        return `<td>${item.id}</td><td>${item.name}</td>
        <td>${item.email}</td><td>${item.job}</td>
        <td>${item.shop}</td>`
    })
    for(let i=0;i<dt.length;i++) {
        var trr = document.createElement("tr")
        trr.innerHTML = dt[i]
        box.appendChild(trr)
    }
}