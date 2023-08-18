
function ST(arrWeek) {
    for(var i=0;i<arrWeek.length;i++) {
        arrWeek[i].dataset.index = i
        arrWeek[i].onclick = headlr 
    }
    var val = 0
    function headlr() {
        var index = this.dataset.index
        for(var i=0;i<arrWeek.length;i++) {
            arrWeek[i].classList.remove("activeWT")
        }
        arrWeek[index].classList.add("activeWT")
        val = index
    }
    console.log(val)
    return val
}


var arrWeekOfmonth = []
const dayT = 86400000
function initDate(divTimes, thDds, res) {
    var initTime = new Date()
    var initWeek = initTime.getDay()
    var mint = initTime.getTime()
    
    if(initWeek == 0)
      initWeek = 7
    var stime = mint - dayT * (initWeek - 1)
    var etime = mint + dayT * (7 - initWeek)
    let obj = {"s": stime, "e": etime}
    arrWeekOfmonth.push(obj)
    
    for(let i = 0 ; i < 3 ; ++i) {
      stime = etime + dayT
      etime = etime + dayT * 7
      let o = {"s": stime, "e": etime}
      arrWeekOfmonth.push(o)
    }
    if(divTimes === "pass")
        return 
    // 初始化第几周的选择
    for(let i = 0 ; i < arrWeekOfmonth.length ; i++) {
      let startDay = new Date(arrWeekOfmonth[i]["s"])
      let endDay = new Date(arrWeekOfmonth[i]["e"])
      let smT = startDay.getMonth() + 1
      let sdT = startDay.getDate()
      let emt = endDay.getMonth() + 1
      let edt = endDay.getDate()
      let st = `${smT}月${sdT}日-${emt}月${edt}日`
      divTimes[i].innerHTML = st
    }
    dateSelect(thDds, res)
}

function dateSelect(thDds, res) {
    ob = arrWeekOfmonth[res]["s"]
    for(let i = 0 ; i < thDds.length ; i++) {
        temp = new Date(ob + dayT * i)
        let smt = temp.getMonth() + 1
        let sdt = temp.getDate()
        let st = `${smt} 月 ${sdt} 日`
        thDds[i].innerHTML = st
    }
}



function showNewTime(week, day, NTime) {
    let t = arrWeekOfmonth[week]["s"] + day * dayT
    let tep = new Date(t)
    let year = tep.getFullYear()
    let month = tep.getMonth() + 1
    let d = tep.getDate()
    NTime.innerHTML = `${year}年${month}月${d}日`
}



function temp(arr) {
    for(let i = 0;i<10;i++) {
        arr.push(i*i)
    }
}