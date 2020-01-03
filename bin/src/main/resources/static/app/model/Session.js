class Session {


    get nomeUsuario(){
        return this._nomeUsuario;
    }

    set nomeUsuario(usuario){
        this._nomeUsuario = usuario;
    }

    get emailUsuario(){
        return this._emailUsuario;
    }

    set emailUsuario(email){
        this._emailUsuario = email;
    }

    get idUsuario(){
        return this._idUsuario;
    }

    set idUsuario(id){
        this._idUsuario = id;
    }

    get preco(){
        return this._preco;
    }

    set preco(preco){
        this._preco = preco;
    }

    get idLivro(){
        return this._idLivro;
    }

    set idLivro(idLivro){
        this._idLivro = idLivro;
    }

    get quantidade(){
        return this._quantidade;
    }

    set quantidade(quantidade){
        this._quantidade = quantidade;
    }
}