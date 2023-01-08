const BackendService = {
    backend: window.location.origin.indexOf('localhost')>-1 ? 'http://localhost:8081' : window.location.origin,
    get: (command,then)=> {
        let comm = command
        axios.get(BackendService.backend+comm,{
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
            }
        }).then(r => {
            then(r.data)
        });
    },
    post: (command,data,then)=> {
        let comm = command
        axios.post(BackendService.backend+comm,data == undefined ? "" : data,{
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
            }
        }).then(r => {
            then(r.data)
        });
    }
}