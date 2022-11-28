var dashModel = require("../models/dashModel");

var sessoes = [];

function testar(req, res) {
    console.log("ENTRAMOS NA dashController");
    res.json("ESTAMOS FUNCIONANDO!");
}

function listarCaminhao(req, res){
    dashModel.listarCaminhao()
    .then(function (resultado){
        if(resultado.length > 0){
            res.status(200).json(resultado);
        }else {
            res.status(204).send("Nenhum resultado encontrado")
        }
    }).catch(
        function (erro){
            console.log(erro);{
                console.log(erro);
                console.log("Houve um erro ao tentar realizar a consulta Erro:", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            }
        }
    );
}

module.exports = {
    listarCaminhao,
    testar

}