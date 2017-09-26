/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getApiUrl() {
    var path = '/Ecommerce';
    var protocol = document.location.protocol + '//';
    var domain = document.location.host;
    return protocol + domain + path;
}

function loadUser(id) {
    if (/[0-9]/g.test(id)) {
        return jQuery.getJSON(getApiUrl() + '/ServletUsuario', {
            id: id
        });
    }
    return null;
}

function listUsers(callback) {
    if (typeof callback == "function")
    return jQuery.getJSON(getApiUrl() + '/ServletUsuario', callback);
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
            if (jQuery('.table tr').exists()) {
                jQuery('.table tr:last-child').after(createRow(data));
            } else {
                jQuery('.table').append(createRow(data));
            }
        });
    }
}

function initialize() {
    if(document.location.pathname.indexOf('/edit') >= 0) {
        if (document.location.pathname.indexOf('id') >= 0) {
            // Load user
        } else if (document.location.pathname.indexOf('/new') >= 0) {
            // Create user
        } else {
            // Show error
        }
    } else if (document.location.pathname.indexOf('id') >= 0) {
        // Show user details
    } else {
        // Show list
        listUsers(function(data) {
            console.log(data);
           if (Array.isArray(data) && data.length > 0) {
               putDataOnTable(data);
           }
        }).fail(function() {
            // Show error of load
        });
    }
}
// Initialize front
window.addEventListener('load', initialize);