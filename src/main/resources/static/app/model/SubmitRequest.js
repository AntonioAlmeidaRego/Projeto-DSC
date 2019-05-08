class SubmitRequest {

    constructor(method, url){
        this._method = method;
        this._url = url;
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


    setMethod(method){
        this._method = method;
    }

    setUrl(url){
        this._url = url;
    }

}