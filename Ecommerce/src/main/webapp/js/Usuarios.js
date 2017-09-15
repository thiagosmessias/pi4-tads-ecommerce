/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function loadUser(id) {
    if (/[0-9]/g.test(id)) {
        return jQuery.get('/ServletUsuario', {
            id: id
        });
    }
    return null;
}

function listUsers() {
    return jQuery.get('/ServletUsuario');
}

function filForm(obj) {
    
}

function filDetails(obj) {
    
}

function createRow(obj) {
    var tr = document.createElement('tr');
    var cId = document.createElement('td');
    cId.append(obj.id);
    tr.appendChild(cId);
    var cNome = document.createElement('td');
    cNome.append(obj.nome);
    tr.appendChild(cNome);
    var cSobrenome = document.createElement('td');
    cSobrenome.append(obj.sobrenome);
    tr.appendChild(cSobrenome);
    var cEmail = document.createElement('td');
    cEmail.append(obj.email);
    tr.appendChild(cEmail);
    var cTelefone = document.createElement('td');
    cTelefone.append(obj.telefone);
    tr.appendChild(cTelefone);
    var cPerfil = document.createElement('td');
    cPerfil.append(obj.perfil);
    tr.appendChild(cPerfil);
    var cSenha = document.createElement('td');
    cSenha.append(obj.senha);
    tr.appendChild(cSenha);
    var cCpf = document.createElement('td');
    cCpf.append(obj.cpf);
    tr.appendChild(cCpf);
    var cAtivo = document.createElement('td');
    cAtivo.append(obj.ativo);
    tr.appendChild(cAtivo);

    return tr;
}

function putDataOnTable(vet) {
    if (Array.isArray(vet)) {
        vet.forEach(function(data) {
           jQuery('.table').append(createRow(data));
        });
    }
}