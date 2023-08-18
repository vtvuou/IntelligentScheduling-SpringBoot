function Fixed(infor){
    var f = true
    window.onscroll = function() {
        var scrollTop = document.documentElement.scrollTop 
            || document.body.scrollTop
        if(scrollTop>240 && f) {
            f = false
            infor.classList.add("site-fix")
        }
        if(scrollTop<240 && !f) {
            f=true
            infor.classList.remove("site-fix")
        }
    }
}