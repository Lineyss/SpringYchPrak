export function sendHttp(form)
{
    return new Promise((resolve) =>{
        let action = form.getAttribute('action');
        let method = form.getAttribute('method');
    
        console.log(form);
        console.log(action + " " + method);
    
        if(method == "POST" || method == "GET")
        {
            form.submit();
            resolve();
        }
        else if(method == "DELETE" || method == "PUT")
        {
            const url = location.origin + action;
            const formContent = new FormData(form);
            console.log(formContent);
            fetch(url, {
                method: method,
                body: formContent
            }).then(responce => {
                console.log(responce);
                resolve();
            })
        }
    })
}