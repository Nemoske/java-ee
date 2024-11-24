/**
* Confirmar a exclusao de um contato
*
* @author Luan de Freitas Miranda
* @param idContatos
*/
function confirmar(idContatos){
	let resposta = confirm("Confirmar a exclusão deste contato ?")
	if(resposta === true){
		window.location.href = "delete?idContatos=" + idContatos
	}
} 