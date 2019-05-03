class Parameters {

    constructor(){
        this._parameters = [];
    }

    addParameter(parameter){
        this._parameters.push(parameter);
    }

    removeParameter(){
        this._parameters.pop();
    }

    getParameters(){
        return this._parameters;
    }

    getParamenterById(parameter){
        let parameters = this._parameters;
        for(let i = 0;i<parameters.length-1;i++){
            if(parameter.id == parameters[i].id){
                return parameters[i].id;
            }
        }
        return null;
    }

    setParameters(parameters){
        this._parameters = parameters;
    }
}