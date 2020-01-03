class SessionController {

    constructor(){
       this._session = new Session();
       this._vetor = [];
    }

    addSession(nome, email, id, key){
        this._session.nomeUsuario = nome;
        this._session.emailUsuario = email;
        this._session.idUsuario = id;
       localStorage.setItem(key, JSON.stringify(this._session));
    }

    addSessionPedido(preco, idLivro, quantidade, key){

        this._session.preco = preco;
        this._session.idLivro = idLivro;
        this._session.quantidade = quantidade;
        localStorage.setItem(key, JSON.stringify(this._session));
    }

    getSession(key){
        return JSON.parse(localStorage.getItem(key));
    }

    clearSession(key){
        localStorage.removeItem(key);
    }
}