import { sendHttp } from "./sendHttp.js";

let formsElementsForDelete = [];

const buttonDelete = document.getElementById("delete");
const peoplesForms = document.querySelectorAll(".element > form");
const deleteCheckboxes = document.querySelectorAll("#chechboxForDelete");
const categoryes = document.querySelectorAll('.sort')
const fields_for_search = document.querySelectorAll('.search')
const input_search = document.getElementById("input_search");

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

fields_for_search.forEach(input => {
    input.addEventListener("click", function()
    {
        const current_input = this;
        let name = current_input.getAttribute("name");
        input_search.setAttribute("name", name);
        input_search.setAttribute("placeholder", `Поиск по '${name}' `)

        fields_for_search.forEach(input => {
            if(input != current_input)
            {
                input.checked = false;
            }
        });
    });
})