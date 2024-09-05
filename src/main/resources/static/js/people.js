import { sendHttp } from "./sendHttp.js";

let formsElementsForDelete = [];

const buttonDelete = document.getElementById("delete");
const peoplesForms = document.querySelectorAll(".element > form");
const deleteCheckboxes = document.querySelectorAll("#chechboxForDelete");
const categoryes = document.querySelectorAll('.search')

peoplesForms.forEach(element => {
    element.addEventListener("submit", function(event) {
        event.preventDefault();
        sendHttp(this).then(()=>{
            location.reload(true);
        })
    });
});

deleteCheckboxes.forEach(element => {
    element.addEventListener("click", function() {
        let form = this.parentElement;

        if(this.checked)
        {
            formsElementsForDelete.push(form);
        }
        else
        {
            let index = formsElementsForDelete.indexOf(form);
            if(index > -1) formsElementsForDelete.splice(index, 1);
        }

        console.log(formsElementsForDelete);
    });
})

buttonDelete.addEventListener("click", ()=>{
    const promises = formsElementsForDelete.map(form => {
        form.setAttribute("method", "DELETE");
        return sendHttp(form);
    });

    Promise.all(promises).then(() => {
        location.reload(true);
    })
});

categoryes.forEach(input => {
    input.addEventListener("click", function(){
        let name = this.getAttribute("name");
        let p_text = this.nextElementSibling.textContent;

        const params = new URLSearchParams();

        let isEnable = this.checked;

        if(isEnable)
        {
            if (!params.has(name)) {
                params.append(name, p_text);
            }
            if (!params.has(name)) {
                params.append(name, p_text);
            }
        }

        const baseUrl = location.href.split('?')[0];
        const finalUrl = `${baseUrl}?${params.toString()}`; 

        location.href = finalUrl;
    });
});