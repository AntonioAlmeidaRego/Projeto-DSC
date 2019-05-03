class SubmitRequest {

    constructor(method, url, Parameters){
        this._method = method;
        this._url = url;
        this._parameters = Parameters;
    }
    
   /* constructor(method, url){
        this._method = method;
        this._url = url;
    }*/

    getMethod(){
        return this._method;
    }

    getUrl(){
        return this._url;
    }

    getParameters(){
        return this._parameters;
    }

    setMethod(method){
        this._method = method;
    }

    setUrl(url){
        this._url = url;
    }

    setParameters(parameters){
        this._parameters = parameters;
    }
}