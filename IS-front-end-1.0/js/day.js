
function sol(time) {
    return (`${parseInt(time/60)} : ${time%60}` + (time%60===0 ? '0' : ''))
}

function dayInit(tb, week, day, trIndex) {
    $.get(
        `http://localhost:8080/study/enterServlet/${"1"}/${"a"}/${"a"}`,
        function(resp) {
            it(tb, week, day, resp, trIndex)
        }
    )
}

var needData = {}

function it(tb, week, day, data, trIndex) {

    $.get (
        `http://localhost:8080/study/daySelectServlet/${data.ID}/${week}/${day}`,
        function(resp) {
            needData = resp
            handler(tb, trIndex)
        }
    )
}

function handler(tb, trIndex) {
    for(let i = 0 ; i < needData.data.length ; i++) {
        trIndex.push(needData.data[i].ine)
    }
    var res = []
    res = needData.data.map(item => {
        if(item.id === "pass")
            return `continue`
        return `<td>${item.id}</td>
        <td>${item.name}</td>
        <td>${item.job}</td>
        <td>${item.task}</td>
        <td>${sol(item.startTime)}</td>
        <td>${sol(item.endTime)}</td>` 
    })
    for(let i = 0;i<res.length;i++) {
        if(res[i] === `continue`)
            continue
        var trr = document.createElement("tr")
        trr.innerHTML = res[i]
        tb.appendChild(trr)
    }
}



function search(tb, value, res, week, day, trIndex){
     $.get(
        `http://localhost:8080/study/enterServlet/${"1"}/${"a"}/${"a"}`,
        function(resp) {
            getSearchCon(tb, value, res, week, day, resp, trIndex)
        }
    )
}
function getSearchCon(tb, value, res, week, day, data, trIndex) {
    $.get (
        `http://localhost:8080/study/searchArrangeServlet/${value}/${res}/${data.ID}/${week}`,
        function(ans) {
            day -= 1
            needData = {"data": ans.data[day]}
            handler(tb, trIndex)
        }
    )
}
