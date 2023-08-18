
function it(tb, week) {
    $.get(`http://localhost:8080/study/enterServlet/${"1"}/${"a"}/${"a"}`,
    function(resp) {
        weekInit(tb, week,resp)
    }
) 
}

function weekInit(tb, week, data) {
    $.get(
        `http://localhost:8080/study/arrangeServiceServlet/${data.ID}/${week}`,
        function(resp) {
            handler(tb, resp)
        }
    )
}


function sol(startTime,endTime) {
    return ((`${parseInt(startTime/60)} : ${startTime%60}` + (startTime%60===0 ? '0' : ''))+` - `+
        (`${parseInt(endTime/60)} : ${endTime%60}` + (endTime%60===0 ? '0' : ''))
    )
}

function handler(tb, need) {
    var size = need.data.length
    var len = need.data[0].length
    let dataArr = []
    
    for(let i =0;i<size;i++) {
        let arrD = need.data[i].map(item => {
            if(item.id === "pass")
                return ``
            return `<div>${item.id}</div>
            <div>${item.name}</div>
            <div>${item.job}</div>
            <div>${item.task}</div>
            <div>${sol(item.startTime,item.endTime)}</div>`
        })
        dataArr.push(arrD) 
    }
    for(let i =0;i<len;i++) {
        var trr = document.createElement("tr")
        for(let j = 0;j<size;j++) {
            let tdd = document.createElement("td")
            tdd.innerHTML = dataArr[j][i]
            trr.appendChild(tdd)
        }
        tb.appendChild(trr)
    }
}


function searchWeek(tb, value, res, week){
     $.get(
        `http://localhost:8080/study/enterServlet/${"1"}/${"a"}/${"a"}`,
        function(resp) {
            getSearchCon(tb, value, res, week, resp)
        }
    )
}
function getSearchCon(tb, value, res, week, data) {
    $.get (
        `http://localhost:8080/study/searchArrangeServlet/${value}/${res}/${data.ID}/${week}`,
        function(resp) {
            handler(tb, resp)
        }
    )
}
