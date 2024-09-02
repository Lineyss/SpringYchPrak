document.querySelectorAll(".content > div").forEach(div =>
    div.addEventListener("click", function(){
        let url = this.getAttribute("url");
        location.href = url;
    }));