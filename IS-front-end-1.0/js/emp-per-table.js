function empPerInit(val1,box) {

    var data = null
    $.get(`http://localhost:8080/study/enterServlet/${"1"}/${"a"}/${"a"}`,
        function(resp) {
            searchEmp(resp,val1,box)
        }
    ) 
}

function searchEmp(data, val1, box) {
    val1 += "1"
    $.get(
        `http://localhost:8080/study/empPreServlet/${data.ID}/${val1}`,
        function(resp) {
            draw(resp, box)
        }
    )
}

function sol(startTime,endTime) {
    if(startTime === "-1")
        return ''
    startTime = parseInt(startTime)
    endTime = parseInt(endTime) 
    return ((`${parseInt(startTime/60)} : ${startTime%60}` + (startTime%60===0 ? '0' : ''))+` - `+
        (`${parseInt(endTime/60)} : ${endTime%60}` + (endTime%60===0 ? '0' : ''))
    )
}
function draw(need, box) {
    var dt = need.data.map(item => {
        return `<td>${item.id}</td><td>${item.dayPre}</td>
        <td>${sol(item.startTime, item.endTime)}</td><td>${item.groupPre}</td>
        <td>${item.shop}</td>`
    })
    for(let i=0;i<dt.length;i++) {
        var trr = document.createElement("tr")
        trr.innerHTML = dt[i]
        box.appendChild(trr)
    }
}